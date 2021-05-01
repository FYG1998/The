package com.example.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.example.demo.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.utils.PackageUtils;

/**
 * 关于app
 */

public class AboutActivity extends BaseActivityTwo {

    private TextView tv_app_version;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        context = this;

        initView();
        initData();
    }

    private void initView() {

        setTitle("Aboute");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件
        tv_app_version = findViewById(R.id.tv_app_version);
    }

    private void initData() {

        String version = PackageUtils.packageName(context);
        if(version != null) {
            String msg = String.format(getString(R.string.app_version), version);
            tv_app_version.setText(msg);
        }
    }


}