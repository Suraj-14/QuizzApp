package com.example.quizzapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {
    Handler hd;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tv1=(TextView)findViewById(R.id.textView9);
        tv2=(TextView)findViewById(R.id.textView10);
        Intent i=getIntent();
        final int score=i.getIntExtra("score",0);
        tv2.setText(""+score);
        tv2.setTextColor(Color.GREEN);
        hd=new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Score.this,MainActivity.class);
                startActivity(i);
            }
        },3000);
    }
}
