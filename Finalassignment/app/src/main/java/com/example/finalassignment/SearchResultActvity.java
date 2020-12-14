package com.example.finalassignment;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.GridView;
import android.widget.TextView;

import com.example.finalassignment.adapter.GoodsAdapter;
import com.example.finalassignment.bean.CartInfo;
import com.example.finalassignment.bean.GoodsInfo2;
import com.example.finalassignment.database.CartDBHelper;
import com.example.finalassignment.database.GoodsDBHelper;
import com.example.finalassignment.util.DateUtil;
import com.example.finalassignment.util.FileUtil;
import com.example.finalassignment.util.SharedUtil;

import java.util.ArrayList;


@SuppressLint("SetTextI18n")
public class SearchResultActvity extends AppCompatActivity implements GoodsAdapter.addCartListener {
    private static final String TAG = "SearchResultActvity";


    private TextView tv_count;
    private GridView gv_channel; // 声明一个网格视图对象
    private int mCount; // 购物车中的商品数量
    private GoodsDBHelper mGoodsHelper; // 声明一个商品数据库的帮助器对象
    private CartDBHelper mCartHelper; // 声明一个购物车数据库的帮助器对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        // 从布局文件中获取名叫tl_result的工具栏
        Toolbar tl_result = findViewById(R.id.tl_result);
        // 设置工具栏的背景
        tl_result.setBackgroundResource(R.color.blue_light);
        // 设置工具栏的标志图片
        tl_result.setLogo(R.drawable.ic_app);
        // 设置工具栏的标题文字
        tl_result.setTitle("搜索结果页");
        // 设置工具栏的导航图标
        tl_result.setNavigationIcon(R.drawable.ic_back);
        // 使用tl_result替换系统自带的ActionBar
        setSupportActionBar(tl_result);


        tv_count = findViewById(R.id.tv_count);
        // 从布局视图中获取名叫gv_channel的网格视图
        gv_channel = findViewById(R.id.gv_channel);

        // 执行搜索查询操作
        doSearchQuery(getIntent());
    }

    // 解析搜索请求页面传来的搜索信息，并据此执行搜索查询操作
    private void doSearchQuery(Intent intent) {
        if (intent != null) {
            // 如果是通过ACTION_SEARCH来调用，即为搜索框来源
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                // 获取额外的搜索数据
                Bundle bundle = intent.getBundleExtra(SearchManager.APP_DATA);
                String value = bundle.getString("hi");
                // 获取实际的搜索文本
                String queryString = intent.getStringExtra(SearchManager.QUERY);


                mGoodsHelper = GoodsDBHelper.getInstance(this, 1);
                mGoodsHelper.openReadLink();
                ArrayList<GoodsInfo2> mSearchArray = mGoodsHelper.query(String.format("name like %s%s%s", "'%", queryString, "%'"));
                if (mSearchArray.size()>0){
                    // 构建商场中商品网格的适配器对象
                    GoodsAdapter adapter = new GoodsAdapter(this, mSearchArray, this);
                    // 给gv_channel设置商品网格适配器
                    gv_channel.setAdapter(adapter);
                    // 给gv_channel设置网格项点击监听器
                    gv_channel.setOnItemClickListener(adapter);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 从menu_null.xml中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_null, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // 点击了工具栏左边的返回箭头
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 关闭商品数据库的数据库连接
        mGoodsHelper.closeLink();
        // 关闭购物车数据库的数据库连接
        mCartHelper.closeLink();
    }

    // 把指定编号的商品添加到购物车
    public void addToCart(long goods_id) {
        mCount++;
        tv_count.setText("" + mCount);
        // 把购物车中的商品数量写入共享参数
        SharedUtil.getIntance(this).writeShared("count", "" + mCount);
        // 根据商品编号查询购物车数据库中的商品记录
        CartInfo info = mCartHelper.queryByGoodsId(goods_id);
        if (info != null) { // 购物车已存在该商品记录
            info.count++; // 该商品的数量加一
            info.update_time = DateUtil.getNowDateTime("");
            // 更新购物车数据库中的商品记录信息
            mCartHelper.update(info);
        } else { // 购物车不存在该商品记录
            info = new CartInfo();
            info.goods_id = goods_id;
            info.count = 1;
            info.update_time = DateUtil.getNowDateTime("");
            // 往购物车数据库中添加一条新的商品记录
            mCartHelper.insert(info);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 获取共享参数保存的购物车中的商品数量
        mCount = Integer.parseInt(SharedUtil.getIntance(this).readShared("count", "0"));
        tv_count.setText("" + mCount);
        // 获取商品数据库的帮助器对象
        mGoodsHelper = GoodsDBHelper.getInstance(this, 1);
        // 打开商品数据库的读连接
        mGoodsHelper.openReadLink();
        // 获取购物车数据库的帮助器对象
        mCartHelper = CartDBHelper.getInstance(this, 1);
        // 打开购物车数据库的写连接
        mCartHelper.openWriteLink();

        // 获取共享参数保存的是否首次打开参数
        mFirst = SharedUtil.getIntance(this).readShared("first", "true");
        // 模拟从网络下载商品图片
        downloadGoods();
        // 把是否首次打开写入共享参数
        SharedUtil.getIntance(this).writeShared("first", "false");

        // 展示商品列表
        doSearchQuery(getIntent());
    }

    private String mFirst = "true"; // 是否首次打开

    //模拟网络数据，初始化数据库中的商品信息
    private void downloadGoods() {
        // 获取共享参数保存的是否首次打开参数
        mFirst = SharedUtil.getIntance(this).readShared("first", "true");
        // 获取当前App的私有存储路径
        String path = MainApplication.getInstance().getExternalFilesDir(
                Environment.DIRECTORY_DOWNLOADS).toString() + "/";
        if (mFirst.equals("true")) { // 如果是首次打开
            ArrayList<GoodsInfo2> goodsList = GoodsInfo2.getDefaultList();
            for (int i = 0; i < goodsList.size(); i++) {
                GoodsInfo2 info = goodsList.get(i);
                // 往商品数据库插入一条该商品的记录
                long rowid = mGoodsHelper.insert(info);
                info.rowid = rowid;
                // 往全局内存写入商品小图
                Bitmap thumb = BitmapFactory.decodeResource(getResources(), info.thumb);
                MainApplication.getInstance().mIconMap.put(rowid, thumb);
                String thumb_path = path + rowid + "_s.jpg";
                FileUtil.saveImage(thumb_path, thumb);
                info.thumb_path = thumb_path;
                // 往SD卡保存商品大图
                Bitmap pic = BitmapFactory.decodeResource(getResources(), info.pic);
                String pic_path = path + rowid + ".jpg";
                FileUtil.saveImage(pic_path, pic);
                pic.recycle();
                info.pic_path = pic_path;
                // 更新商品数据库中该商品记录的图片路径
                mGoodsHelper.update(info);
            }
        } else { // 不是首次打开
            // 查询商品数据库中所有商品记录
            ArrayList<GoodsInfo2> goodsArray = mGoodsHelper.query("1=1");
            for (int i = 0; i < goodsArray.size(); i++) {
                GoodsInfo2 info = goodsArray.get(i);
                // 从指定路径读取图片文件的位图数据
                Bitmap thumb = BitmapFactory.decodeFile(info.thumb_path);
                // 把该位图对象保存到应用实例的全局变量中
                MainApplication.getInstance().mIconMap.put(info.rowid, thumb);
            }
        }
        // 把是否首次打开写入共享参数
        SharedUtil.getIntance(this).writeShared("first", "false");
    }
}
