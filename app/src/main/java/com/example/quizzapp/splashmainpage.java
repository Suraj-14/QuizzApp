package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splashmainpage extends AppCompatActivity {

    Handler hd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashmainpage);
        hd=new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(splashmainpage.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },2000);
    }
}
