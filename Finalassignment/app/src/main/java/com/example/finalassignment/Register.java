package com.example.finalassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.finalassignment.database.UserDBHelper;
import com.example.finalassignment.util.DateUtil;
import com.example.finalassignment.util.ViewUtil;
import com.example.finalassignment.bean.UserInfo;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText et_name;
    private EditText et_phoneX;
    private EditText et_passwordX;
    private Button btn_register;
    private UserDBHelper mHelperX; // 声明一个用户数据库的帮助器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = findViewById(R.id.et_name);
        et_phoneX = findViewById(R.id.et_phoneX);
        et_passwordX = findViewById(R.id.et_passwordX);
        btn_register = findViewById(R.id.btn_register);

        // 给et_phoneX添加文本变更监听器
        et_phoneX.addTextChangedListener(new HideTextWatcher(et_phoneX));
        // 给et_passwordX添加文本变更监听器
        et_passwordX.addTextChangedListener(new HideTextWatcher(et_passwordX));

        btn_register.setOnClickListener(this);
    }

    // 定义编辑框的文本变化监听器
    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
        private CharSequence mStr;

        HideTextWatcher(EditText v) {
            super();
            mView = v;
            mMaxLength = ViewUtil.getMaxLength(v);
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mStr = s;
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            if (mStr == null || mStr.length() == 0)
                return;
            // 手机号码输入达到11位，或者密码/验证码输入达到8位，都关闭输入法软键盘
            if ((mStr.length() == 11 && mMaxLength == 11) ||
                    (mStr.length() == 8 && mMaxLength == 8)) {
                ViewUtil.hideOneInputMethod(Register.this, mView);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 获得用户数据库帮助器的一个实例
        mHelperX = UserDBHelper.getInstance(this, 2);
        // 恢复页面，则打开数据库连接
        mHelperX.openWriteLink();
    }

    protected void onPause() {
        super.onPause();
        // 暂停页面，则关闭数据库连接
        mHelperX.closeLink();
    }

    @Override
    public void onClick(View v) {
        String phone = et_phoneX.getText().toString();

        if (phone.length() < 11) { // 手机号码不足11位
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        //把手机号码和密码保存为数据库的用户表记录
        // 创建一个用户信息实体类
        UserInfo info = new UserInfo();
        info.name = et_name.getText().toString();
        info.phone = phone;
        info.pwd = et_passwordX.getText().toString();
        info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");
        // 往用户数据库添加登录成功的用户信息（包含手机号码、密码、登录时间）
        mHelperX.insert(info);

        //注册成功
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}