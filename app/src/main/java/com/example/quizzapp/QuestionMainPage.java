package com.example.quizzapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Locale;

public class QuestionMainPage extends AppCompatActivity implements View.OnClickListener {
    public String question, answer, option4, option2, option3, option1;//qstnn;
    private TextView textViewQuestion, textViewScore, textViewQuestionCount, textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button buttonConfirm;
    private int questionCounter , totalQuestion;
    public int score;
    private boolean answered;
    RelativeLayout rl2;
    private static final long CountDown_mills = 30000;
    private CountDownTimer countDownTimer;
    private long timeleft;
    private long backpresstime;
    public ArrayList<String> qstn, ans, optn1, optn2, optn3, optn4,qstnno;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_main_page);
        rl2 = (RelativeLayout) findViewById(R.id.r2);
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radiogp);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);

        buttonConfirm = findViewById(R.id.confirm);
        rb1.setTextColor(Color.BLUE);
        textViewCountDown.setTextColor(R.color.white);
        buttonConfirm.setOnClickListener(this);
        SharedPreferences sp1 = getSharedPreferences("ColorData", MODE_PRIVATE);
        int r1 = sp1.getInt("s1", 0);
        int r2 = sp1.getInt("s2", 0);
        int r3 = sp1.getInt("s3", 0);



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
        totalQuestion=qstn.size();
        showNextQuestion();
    }

    @SuppressLint("ResourceAsColor")
    private void showNextQuestion() {    //For next question

        rb1.setTextColor(Color.BLUE);
        rb2.setTextColor(Color.BLUE);
        rb3.setTextColor(Color.BLUE);
        rb4.setTextColor(Color.BLUE);

        rbGroup.clearCheck();

        if (questionCounter < totalQuestion) {
            question = qstn.get(questionCounter);
            option1 = optn1.get(questionCounter);
            option2 = optn2.get(questionCounter);
            option3 = optn3.get(questionCounter);
            option4 = optn4.get(questionCounter);
            answer = ans.get(questionCounter);
            textViewQuestion.setText(question);
            rb1.setText(option1);
            rb2.setText(option2);
            rb3.setText(option3);
            rb4.setText(option4);

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + totalQuestion);
            answered = false;
            buttonConfirm.setText("confirm");
            timeleft = CountDown_mills;
            startCount();

        }
        else{

           stopQuiz();
        }

    }


    private void startCount()    //for timing
    {
        countDownTimer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long l) {
                timeleft = l;
                //  Toast.makeText(getApplicationContext(),""+l,Toast.LENGTH_SHORT).show();
                countDown();

            }

            @Override
            public void onFinish() {
                showNextQuestion();

            }
        }.start();
    }


    @SuppressLint("ResourceAsColor")
    private void countDown() {
        int min = (int) (timeleft / 1000) / 60;
        int sec = (int) (timeleft / 1000) % 60;
        String timeformat = String.format(Locale.getDefault(), "%02d:%02d", min, sec);
        textViewCountDown.setText(timeformat);
        if (timeleft < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(Color.BLUE);
        }
    } // timing cod eend


    private void stopQuiz() {
        int a=score;
        Toast.makeText(getApplicationContext(), "Total Score is :"+a, Toast.LENGTH_LONG).show();
        /*SharedPreferences sp=getSharedPreferences("ColorData",MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putInt("score",score);
        ed.commit();*/
        Intent in=new Intent(QuestionMainPage.this,Score.class);
        in.putExtra("score",a);
        startActivity(in);
        //finish();
    }  //for finish

    private void checkAnswer() {   //for answer check

        answered = true;
        countDownTimer.cancel();
        // Toast.makeText(this, ""+answer, Toast.LENGTH_SHORT).show();
        String answerr = ((RadioButton) findViewById(rbGroup.getCheckedRadioButtonId())).getText().toString();
        //  Toast.makeText(this, "" + answerr, Toast.LENGTH_SHORT).show();

        if (answerr.equals(answer)) {
            score++;
            textViewScore.setTextColor(Color.GREEN);
            textViewScore.setText("Score: " + score);
            YoYo.with(Techniques.BounceInUp)
                    .duration(700)
                    .repeat(1)
                    .playOn(findViewById(R.id.text_view_score));
            // Toast.makeText(this, "Hii suraj", Toast.LENGTH_SHORT).show();
        }
        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        if (option1.equals(answer)) {
            rb1.setTextColor(Color.GREEN);
            textViewQuestion.setText("Correct answer is:" + answer);
        } else if (option2.equals(answer)) {
            rb2.setTextColor(Color.GREEN);
            textViewQuestion.setText("Correct answer is:" + answer);
        } else if (option3.equals(answer)) {
            rb3.setTextColor(Color.GREEN);
            textViewQuestion.setText("Correct answer is:" + answer);
        } else if (option4.equals(answer)) {
            rb4.setTextColor(Color.GREEN);
            textViewQuestion.setText("Correct answer is:" + answer);
        }

        if (questionCounter < totalQuestion) {
            buttonConfirm.setText("Next");
        } else {
            buttonConfirm.setText("Finish");


        }

    }   //answer checking code end*/


    @Override
    public void onClick(View view) {
        if (!answered) {
            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                checkAnswer();
            } else {
                Toast.makeText(this, "please Select", Toast.LENGTH_LONG).show();
            }

        } else {
            showNextQuestion();

        }

    }

    @Override
    public void onBackPressed() {
       if(backpresstime+2000>System.currentTimeMillis())
       {
           stopQuiz();
       }
       else
       {
           Toast.makeText(this, "press back again to finish", Toast.LENGTH_LONG).show();
       }
       backpresstime=System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
        {
            countDownTimer.cancel();
        }
    }
}

