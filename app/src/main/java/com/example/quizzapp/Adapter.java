package com.example.quizzapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by suraj on 08-07-2019.
 */

public class Adapter extends BaseAdapter
        {
        Context c;
        int length;
        LayoutInflater inflater;
        Adapter(Context c, int length)
        {
        this.c=c;
        this.length=length;
        inflater=LayoutInflater.from(c);
        }
@Override
public int getCount()
        {
        return length;

        }

@Override
public Object getItem(int i) {
        return i;

        }

@Override
public long getItemId(int i) {
        return 0;
        }

@Override
public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.piclayout,null);
        TextView tv1=(TextView)view.findViewById(R.id.textView);
        TextView tv2=(TextView)view.findViewById(R.id.textView2);
        TextView tv3=(TextView)view.findViewById(R.id.textView3);
        TextView tv4=(TextView)view.findViewById(R.id.textView4);
        TextView tv5=(TextView)view.findViewById(R.id.textView5);
        TextView tv6=(TextView)view.findViewById(R.id.textView6);
        TextView tv7=(TextView)view.findViewById(R.id.textView7);
        tv1.setText(BackGround.qstn.get(i));
        tv2.setText(BackGround.optn1.get(i));
        tv3.setText(BackGround.optn2.get(i));
        tv4.setText(BackGround.optn3.get(i));
        tv5.setText(BackGround.optn4.get(i));
        tv6.setText(BackGround.ans.get(i));



        return view;

        }
        }
