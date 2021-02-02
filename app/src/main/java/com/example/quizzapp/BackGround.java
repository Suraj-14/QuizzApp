package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BackGround extends AppCompatActivity {
    ListView lv;
    public static  ArrayList<String> qstn, ans, optn1, optn2, optn3, optn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_ground);
        lv=(ListView)findViewById(R.id.lv);


        qstn = new ArrayList<String>();
        ans = new ArrayList<String>();
        optn1 = new ArrayList<String>();
        optn2 = new ArrayList<String>();
        optn3 = new ArrayList<String>();
        optn4 = new ArrayList<String>();
        Intent intent = getIntent();
        qstn = intent.getStringArrayListExtra("qstn");
        ans = intent.getStringArrayListExtra("ans");
        optn1 = intent.getStringArrayListExtra("optn1");
        optn2 = intent.getStringArrayListExtra("optn2");
        optn3 = intent.getStringArrayListExtra("optn3");
        optn4 = intent.getStringArrayListExtra("optn4");
        Adapter adp=new Adapter(this,qstn.size());
        lv.setAdapter(adp);
    }


}



