package com.example.marqueeandbbs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Marquee extends AppCompatActivity implements View.OnClickListener{
    private TextView textView4;
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);

        textView4 = findViewById(R.id.textView4);
        textView4.setOnClickListener(this);
        textView4.requestFocus();
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.textView4){
            isPaused = !isPaused;
            if(isPaused){
                textView4.setFocusable(false);
                textView4.setFocusableInTouchMode(false);
            }else{
                textView4.setFocusable(true);
                textView4.setFocusableInTouchMode(true);
                textView4.requestFocus();
            }
        }
    }


}