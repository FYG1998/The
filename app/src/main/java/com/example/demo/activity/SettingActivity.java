package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.demo.BaseActivityTwo;
import com.example.demo.R;

public class SettingActivity extends BaseActivityTwo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        setTitle("Setting");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件

        initData();
        initView();


    }



    private void initData() {

    }

    private void initView() {

    }
}