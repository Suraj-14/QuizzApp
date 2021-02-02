package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Handler hd;
    TextView tv;
    Button play;
    RelativeLayout rl1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=(Button)findViewById(R.id.button);
        play.setOnClickListener(this);
        rl1=(RelativeLayout)findViewById(R.id.r1);
        tv=(TextView)findViewById(R.id.textView);
    }

    @Override
    public void onClick(View view) {
       if(view.getId()==R.id.button)
       {
           Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animzoom);
           YoYo.with(Techniques.Pulse)
                   .duration(400)
                   .repeat(3)
                   .playOn(findViewById(R.id.textView));
           hd=new Handler();
           hd.postDelayed(new Runnable() {
              @Override
               public void run() {
                   Intent i=new Intent(MainActivity.this,JsonDataRcv.class);
                   startActivity(i);
              }
           },2000);

       }
    }
}