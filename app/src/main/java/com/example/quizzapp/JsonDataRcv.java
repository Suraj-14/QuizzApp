package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonDataRcv extends AppCompatActivity {
    RequestQueue rq;
    public static ArrayList<String> qstnno,qstn,ans,optn1,optn2,optn3,optn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data_rcv);
        qstnno=new ArrayList<String>();
        qstn= new ArrayList<String>();
        ans= new ArrayList<String>();
        optn1=new ArrayList<String>();
        optn2=new ArrayList<String>();
        optn3=new ArrayList<String>();
        optn4=new ArrayList<String>();
        rq= Volley.newRequestQueue(this);
        String URL="http://www.json-generator.com/api/json/get/cuokTGMSsy?indent=2";
        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET,URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray ja=response.getJSONArray("Question");
                    for(int i=0;i<ja.length();i++)
                    {
                        JSONObject obj=ja.getJSONObject(i);
                        String qstnn=obj.getString("qstnno");
                        String question=obj.getString("question");
                        String answer=obj.getString("answer");
                        String option4=obj.getString("option4");
                        String option2=obj.getString("option2");
                        String option3=obj.getString("option3");
                        String option1=obj.getString("option1");

                       // qstnno.add(qstnn);
                        qstn.add(question);
                        ans.add(answer);
                        optn1.add(option1);
                        optn2.add(option2);
                        optn3.add(option3);
                        optn4.add(option4);

                         Intent intent=new Intent(JsonDataRcv.this,QuestionMainPage.class);
                        //intent.putStringArrayListExtra("qstno",qstnno);
                        intent.putStringArrayListExtra("qstn",qstn);
                        intent.putStringArrayListExtra("ans",ans);
                        intent.putStringArrayListExtra("optn1",optn1);
                        intent.putStringArrayListExtra("optn2",optn2);
                        intent.putStringArrayListExtra("optn3",optn3);
                        intent.putStringArrayListExtra("optn4",optn4);

                        startActivity(intent);

                       // Toast.makeText(getApplicationContext(),"Qstn n0. :"+qstnno+"\n Question :"+question+"\n option1 :"+option1+"\n option 2:"+option2+"\n option3 :"+option3+"\n option4 :"+option4+"\n Answer :"+answer,Toast.LENGTH_LONG).show();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),""+error.toString(),Toast.LENGTH_LONG).show();

            }
        });
        rq.add(jor);
    }
}

