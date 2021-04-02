package com.example.demo.activity;


import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.demo.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.utils.PackageUtils;


public class AboutActivity extends BaseActivityTwo {

    private TextView tv_app_version;
    private Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        setBackBtn();

        context = this;
        tv_app_version = findViewById(R.id.tv_app_version);

        String version = PackageUtils.packageName(context);
        if(version != null) {
            String msg = String.format(getString(R.string.app_version), version);
            tv_app_version.setText(msg);
        }
    }

    private void initData() {

    }
    private void initView() {

    }





}