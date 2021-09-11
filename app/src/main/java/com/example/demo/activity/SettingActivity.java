package com.example.demo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import com.example.demo.base.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.example.demo.model.PermissionListener;
import com.example.demo.model.spInfo;
import com.example.demo.update.CheckAppUpdate;
import com.example.demo.utils.PackageUtils;
import com.example.demo.utils.SPDataUtils;
import java.util.List;


public class SettingActivity extends BaseActivityTwo {

    private Context context;
    private Activity activity;
    private String mUpdateUrl = "https://naiop.github.io/test/updateApp.json";
    private Switch mSwitch;
    private Button button1, button2, button3, btn_update_check, btn_tbs_init;
    private EditText editTexttime;
    private TextView texttbsstate, tv_ver_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context = this;
        activity = this;

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
        btn_update_check = findViewById(R.id.btn_update_check);
        editTexttime = findViewById(R.id.editTexttime);
        editTexttime.setText(info.getTime());
        texttbsstate = findViewById(R.id.tbs_state);
        tv_ver_setting = findViewById(R.id.tv_ver_setting);
        btn_tbs_init = findViewById(R.id.btn_tbs_init);


    }

    private void initData() {

        //初始化tbs state
        X5WebView x5WebView = new X5WebView(this);
        if (x5WebView.getX5WebViewExtension() != null) {
            texttbsstate.setText("ture");
        }
        initBtnListenser();

        tv_ver_setting.setText(tv_ver_setting.getText() + "  " + PackageUtils.packageName(context));
    }


    //button 事件
    private void initBtnListenser() {


        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    boolean flag = SPDataUtils.saveUserInfo(context, "switch", isChecked);
                    if (flag) {
                        showToast("开启");
                    }
                } else {

                    boolean flag = SPDataUtils.saveUserInfo(context, "switch", isChecked);
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
                boolean flag = SPDataUtils.saveUserInfo(context, "mtime", time);

                if (flag) {
                    showToast("保存成功");
                }

            }
        });

        btn_update_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionWithWriteStorage();

            }
        });

    }


    /**
     * 授权并更新
     */
    private void requestPermissionWithWriteStorage() {
        requestRuntimePermissions(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, new PermissionListener() {
            @Override
            public void onGranted() {
                CheckAppUpdate.checkAppUpdate(activity, mUpdateUrl); //检查app版本
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                showToast("请授予存储卡写入权限！");
            }
        });
    }


}