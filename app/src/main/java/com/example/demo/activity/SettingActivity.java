package com.example.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import com.example.demo.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.model.spInfo;
import com.example.demo.utils.SPDataUtils;

public class SettingActivity extends BaseActivityTwo {


    private Context context;
    private Switch mSwitch;
    private Button button1,button2,button3  ;
    private EditText editTexttime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context =this;

        initView();
        initData();

    }

    private void initView() {
        setTitle("Setting");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件

        spInfo info = SPDataUtils.getspInfo(context);
        mSwitch = (Switch) findViewById(R.id.sw_wifi);
        mSwitch.setChecked(info.getMboolean());

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        editTexttime = findViewById(R.id.editTexttime);
        editTexttime.setText(info.getTime()) ;

    }

    private void initData() {

        initBtnListenser();
    }


    //button 事件
    private void initBtnListenser() {


        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    boolean flag = SPDataUtils.saveUserInfo(context,"switch" , isChecked);
                    if (flag){
                        showToast("开启");
                    }
                }else {

                    boolean flag = SPDataUtils.saveUserInfo(context,"switch", isChecked);
                    if (flag) {
                        showToast("关闭");
                    }
                }

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("请点击Switch按钮");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("推荐给好基友");
            }
        });

        //sp 保存 导播图时间
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time = editTexttime.getText().toString();
                boolean flag = SPDataUtils.saveUserInfo(context,"mtime",time);

                if(flag){
                    showToast("保存成功");
                }

            }
        });

    }



}