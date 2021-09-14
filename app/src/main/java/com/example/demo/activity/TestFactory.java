package com.example.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.demo.base.BaseActivity;
import com.example.demo.R;
import com.example.demo.A_Test.HslClientMqtt;
import com.example.demo.A_Test.TestHslClientMqttShow;
import com.example.demo.base.BaseActivityTwo;

public class TestFactory extends BaseActivityTwo implements View.OnClickListener {
    private Context context;
    private Button hsl_hslmqtt,hslmqtt_adapter,station_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testfactory);
        context =this;
        setTitle("Test Page");
        setBackBtn();


        initView();
        initData();
    }


    private void initView() {
        hsl_hslmqtt = findViewById(R.id.hslmqtt);
        hslmqtt_adapter = findViewById(R.id.hslmqtt_adapter);
        station_adapter = findViewById(R.id.station);

        hsl_hslmqtt.setOnClickListener(this);
        hslmqtt_adapter.setOnClickListener(this);
        station_adapter.setOnClickListener(this);
    }

    private void initData() {
    }



    // Click
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hslmqtt:
                mIntent(HslClientMqtt.class);
                break;
            case R.id.hslmqtt_adapter:
                mIntent(TestHslClientMqttShow.class);
                break;
            case R.id.station:
                mIntent(StationActivity.class);
                break;

        }

    }
}