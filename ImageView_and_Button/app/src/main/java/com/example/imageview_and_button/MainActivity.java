package com.example.imageview_and_button;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_center = findViewById(R.id.btn_center);
        btn_center.setOnClickListener(new MyOnClickListener());

        Button btn_fitCenter = findViewById(R.id.btn_fitCenter);
        btn_fitCenter.setOnClickListener(new MyOnClickListener());

        Button btn_centerCrop = findViewById(R.id.btn_centerCrop);
        btn_centerCrop.setOnClickListener(new MyOnClickListener());

        Button btn_centerInside = findViewById(R.id.btn_centerInside);
        btn_centerInside.setOnClickListener(new MyOnClickListener());

        Button btn_fitXY = findViewById(R.id.btn_fitXY);
        btn_fitXY.setOnClickListener(new MyOnClickListener());

        Button btn_fitStart = findViewById(R.id.btn_fitStart);
        btn_fitStart.setOnClickListener(new MyOnClickListener());

        Button btn_fitEnd = findViewById(R.id.btn_fitEnd);
        btn_fitEnd.setOnClickListener(new MyOnClickListener());
    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v){

            ImageView iv_scale = findViewById(R.id.iv_scale);

            if (v.getId() == R.id.btn_center){
                iv_scale.setScaleType(ImageView.ScaleType.CENTER);
            }else if (v.getId() == R.id.btn_fitCenter){
                iv_scale.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }else if (v.getId() == R.id.btn_centerCrop){
                iv_scale.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else if (v.getId() == R.id.btn_centerInside){
                iv_scale.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }else if (v.getId() == R.id.btn_fitXY){
                iv_scale.setScaleType(ImageView.ScaleType.FIT_XY);
            }else if (v.getId() == R.id.btn_fitStart){
                iv_scale.setScaleType(ImageView.ScaleType.FIT_START);
            }else if (v.getId() == R.id.btn_fitEnd){
                iv_scale.setScaleType(ImageView.ScaleType.FIT_END);
            }
        }
    }
}