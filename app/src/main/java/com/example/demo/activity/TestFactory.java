package com.example.demo.activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.aatest.HslClientMqtt;
import com.example.demo.aatest.TestHslClientMqttShow;


public class TestFactory extends BaseActivity {
    private Context context;
    private Button button1,hslmqtt_adapter;


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
        hslmqtt_adapter = findViewById(R.id.hslmqtt_adapter);
    }

    private void initData() {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mIntent(HslClientMqtt.class);
            }
        });

        hslmqtt_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mIntent(TestHslClientMqttShow.class);
            }
        });

    }


}