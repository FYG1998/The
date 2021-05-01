package com.example.demo.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.BaseActivity;
import com.example.demo.R;


public class TestFactory extends BaseActivity {
    private Context context;
    private Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testfactory);

        context =this;
        initView();
        initData();
    }


    private void initView() {

        button1 = findViewById(R.id.hslmqtt);
    }

    private void initData() {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mIntent(HslClientMqtt.class);
            }
        });

    }


}