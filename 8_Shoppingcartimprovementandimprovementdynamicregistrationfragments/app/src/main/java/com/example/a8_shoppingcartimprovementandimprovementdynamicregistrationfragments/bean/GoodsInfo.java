package com.example.a8_shoppingcartimprovementandimprovementdynamicregistrationfragments.bean;

import com.example.a8_shoppingcartimprovementandimprovementdynamicregistrationfragments.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 声明一个银行卡商品的名称数组
    private static String[] mNameArray = {
            "花嫁卡", "王者荣耀卡1", "王者荣耀卡2", "王者荣耀卡3", "航海王卡1", "航海王卡2" , "航海王卡3", "Hello Kitty卡1", "Hello Kitty卡2"
    };
    // 声明一个银行卡商品的描述数组
    private static String[] mDescArray = {
            "共赴纯白之约 达标领大会员",
            "大招好了！达标抽王者荣耀惊喜福袋1个",
            "大招好了！达标抽王者荣耀惊喜福袋2个",
            "大招好了！达标抽王者荣耀惊喜福袋3个",
            "海贼王，我当定了！达标领定制马克杯1个",
            "海贼王，我当定了！达标领定制马克杯2个",
            "海贼王，我当定了！达标领定制马克杯3个",
            "轻奢风范达标领下午茶1杯",
            "轻奢风范达标领下午茶2杯"
    };
    // 声明一个银行卡商品的价格数组
    private static float[] mPriceArray = {10, 10, 15, 20, 10, 15, 20, 10, 15};
    // 声明一个银行卡商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.huajiacards, R.drawable.wangzherongyaocard1s, R.drawable.wangzherongyaocard2s,
            R.drawable.wangzherongyaocard3s, R.drawable.hanghaiwangcard1s, R.drawable.hanghaiwangcard2s,
            R.drawable.hanghaiwangcard3s, R.drawable.hellokittycard1s, R.drawable.hellokittycard2s
    };
    // 声明一个银行卡商品的大图数组
    private static int[] mPicArray = {
            R.drawable.huajiacard, R.drawable.wangzherongyaocard1, R.drawable.wangzherongyaocard2,
            R.drawable.wangzherongyaocard3, R.drawable.hanghaiwangcard1, R.drawable.hanghaiwangcard2,
            R.drawable.hanghaiwangcard3, R.drawable.hellokittycard1, R.drawable.hellokittycard2
    };

    // 获取默认的银行卡信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
