package com.example.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.example.demo.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.tools.spInfo;
import com.example.demo.utils.SPDataUtils;

public class SettingActivity extends BaseActivityTwo {


    private Context context;
    private Switch mSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        context =this;
        spInfo info = SPDataUtils.getspInfo(context);
        mSwitch = (Switch) findViewById(R.id.sw_wifi);

        mSwitch.setChecked(info.getMboolean());


        setTitle("Setting");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件

        initData();
        initView();


    }



    private void initData() {

      mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

              if (isChecked){

                  boolean flag = SPDataUtils.saveUserInfo(context,isChecked);
                  if (flag){
                      showToast("开启");
                  }
              }else {

                  boolean flag = SPDataUtils.saveUserInfo(context,isChecked);
                  if (flag) {
                      showToast("关闭");
                  }
              }

          }
      });




    }


    private void initView() {

    }
}