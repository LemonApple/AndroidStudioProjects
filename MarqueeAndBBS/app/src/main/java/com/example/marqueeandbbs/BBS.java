package com.example.marqueeandbbs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BBS extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private TextView textView;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_b_s);

        textView = findViewById(R.id.textView);
        textView.setOnClickListener(this);
        textView.setOnLongClickListener(this);
        textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(this);
        textView2.setOnLongClickListener(this);
        textView2.setMovementMethod(new ScrollingMovementMethod());
    }

    private String[] mChatStr = {"青花瓷", "七里香","花海","蒲公英的约定","东风破"};

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.textView || v.getId() == R.id.textView2){
            int random = (int)(Math.random()*10)%5;
            String newStr = String.format("%s\n%s %s", textView2.getText().toString(), date, mChatStr[random]);
            textView2.setText(newStr);
        }
    }

    @Override
    public boolean onLongClick(View v){
        if (v.getId() == R.id.textView || v.getId() == R.id.textView2){
            textView2.setText("");
        }
        return true;
    }
}