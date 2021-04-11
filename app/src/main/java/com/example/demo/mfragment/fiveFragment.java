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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.activity.AboutActivity;
import com.example.demo.activity.Browser;
import com.example.demo.activity.SettingActivity;
import com.example.demo.activity.SettingSavePage;
import com.example.demo.tencent_tbs.FilechooserActivity;
import com.example.demo.tools.QQUtil;

public class fiveFragment extends Fragment {

    View mRootView;
    RelativeLayout relativeLayout;
    RelativeLayout relativeLayout2;
    RelativeLayout relativeLayout_exit_login;
    RelativeLayout relativeLayout_path;
    RelativeLayout relativeLayout_blame;
    RelativeLayout web;
    RelativeLayout Setting;
    RelativeLayout h;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mRootView = inflater.inflate(R.layout.fragment_five, container, false);

        relativeLayout=mRootView.findViewById(R.id.tbsfile);
        relativeLayout2=mRootView.findViewById(R.id.qqtz);
        relativeLayout_exit_login = mRootView.findViewById(R.id.exit_login);
        relativeLayout_path =mRootView.findViewById(R.id.path);
        relativeLayout_blame=mRootView.findViewById(R.id.blame);
        web =  mRootView.findViewById(R.id.webBrowser);
        Setting=mRootView.findViewById(R.id.Setting);
        h =mRootView.findViewById(R.id.h);

        openfile();
        Test();
        exit();
        path();
        Blame();
        click();
        Setting();
        hh();



        return mRootView;
    }

    public void hh()
    {
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SettingSavePage.class);
                startActivity(intent);




            }
        });
    }

    public void Blame()
    {
        relativeLayout_blame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);




            }
        });
    }

    //打开x5 file选择器
    public void openfile()
    {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(),"onclike",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), FilechooserActivity.class);
                startActivity(intent);




            }
        });
    }

    public void Test()
    {
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=1686358088&version=1";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));*/

                if (!QQUtil.joinQQGroup("Oo7Jsgqio1N3n50qJcLYX50EODExfbQk ", getActivity())) {
                    Toast.makeText(getActivity(), "未安装手Q或安装的版本不支持",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }




    public void exit()
    {
        relativeLayout_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }



    public void path()
    {
        relativeLayout_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dialog dialog = new Dialog(getContext(),R.style.style_dialog);
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_path , null);
                dialog.setContentView(view); //将布局设置给Dialog

                Window window = dialog.getWindow();
                window.setGravity( Gravity.BOTTOM); //设置Dialog从窗体底部弹出
                WindowManager.LayoutParams lp = window.getAttributes();//获得窗体的属性
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);  //将属性设置给窗体
                window.setWindowAnimations(R.style.style_dialog);  //添加动画
                dialog.show();//显示对话框

            }
        });

    }

    public  void click()
    {
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(getActivity(), Browser.class);
               startActivity(intent);

            }
        }) ;

    }


    public  void Setting()
    {
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);

            }
        }) ;

    }

}