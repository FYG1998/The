package com.example.demo.mfragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.activity.AboutActivity;
import com.example.demo.activity.Browser;
import com.example.demo.activity.CodeActivity;
import com.example.demo.activity.EditTextActivity;
import com.example.demo.activity.LoginActivity;
import com.example.demo.activity.MainTbs;
import com.example.demo.activity.SettingActivity;
import com.example.demo.activity.TestFactory;
import com.example.demo.utils.WXQQUtil;
import com.example.demo.utils.SoundUtils;


public class fiveFragment extends Fragment {

    RelativeLayout aboutapp, webbrowser, tbsfile, qqgroup, factorymodel, downlaodpath, setting, exitlogin, de;
    ImageView img_header;

    private void initView(View view) {
        aboutapp = view.findViewById(R.id.aboutapp);
        webbrowser = view.findViewById(R.id.webbrowser);
        tbsfile = view.findViewById(R.id.tbsfile);
        qqgroup = view.findViewById(R.id.qqgropu);
        factorymodel = view.findViewById(R.id.factorymodel);
        downlaodpath = view.findViewById(R.id.downloadpath);
        setting = view.findViewById(R.id.Setting);
        exitlogin = view.findViewById(R.id.exitlogin);
        de = view.findViewById(R.id.de);
        img_header = view.findViewById(R.id.img_header);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initData() {

        initBtnListenser();
        img_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 0);

            }
        });
    }

    private void initBtnListenser() {

        //关于app
        aboutapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);

            }
        });

        //Text
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "点击一下", Toast.LENGTH_SHORT).show();
                SoundUtils.playSound(R.raw.sucess);
                Intent intent = new Intent(getActivity(), CodeActivity.class);
                startActivity(intent);


            }
        });

        //浏览器
        webbrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Browser.class);
                startActivity(intent);

            }
        });

        //tbs file
        tbsfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainTbs.class);
                startActivity(intent);
            }
        });


        //加入qq group
        qqgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!WXQQUtil.joinQQGroup("Oo7Jsgqio1N3n50qJcLYX50EODExfbQk ", getActivity())) {
                    Toast.makeText(getActivity(), "未安装手Q或安装的版本不支持", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //下载路径
        downlaodpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(getContext(), R.style.style_dialog);
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_path, null);
                dialog.setContentView(view); //将布局设置给Dialog
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM); //设置Dialog从窗体底部弹出
                WindowManager.LayoutParams lp = window.getAttributes();//获得窗体的属性
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);  //将属性设置给窗体
                window.setWindowAnimations(R.style.style_dialog);  //添加动画
                dialog.show();//显示对话框

            }
        });


        //后续Demo测试 写这个里面
        factorymodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EditTextActivity.class);
                intent.putExtra("title", "测试密码");
                startActivity(intent);



            }
        });

        //设置
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        //退出app
        exitlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }


}