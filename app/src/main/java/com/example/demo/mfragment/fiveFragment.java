package com.example.demo.mfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.activity.AboutActivity;
import com.example.demo.activity.Browser;
import com.example.demo.activity.SettingActivity;
import com.example.demo.activity.SettingSavePage;
import com.example.demo.activity.FilechooserActivity;
import com.example.demo.tools.QQUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class fiveFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_five, container, false);


        relativeLayout=view.findViewById(R.id.tbsfile);
        relativeLayout2=view.findViewById(R.id.qqtz);
        relativeLayout_exit_login = view.findViewById(R.id.exit_login);
        relativeLayout_path =view.findViewById(R.id.path);
        relativeLayout_blame=view.findViewById(R.id.blame);
        web =  view.findViewById(R.id.webBrowser);
        Setting=view.findViewById(R.id.Setting);
        h =view.findViewById(R.id.h);


        initBtnListenser();
        initView();
        initData();



        List images = new ArrayList();
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg");
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg");
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg");


        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.Accordion);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //增加点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
               // Toast.makeText(MainActivity.this, "position"+position, Toast.LENGTH_SHORT).show();
            }
        });

        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });

        banner.setClipToOutline(true);



        return view;
    }



    public static class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide
                    .with(context)
                    .load(path)
                    .centerCrop()
                    .into(imageView);
        }
    }

    private void initView() {

    }

    private void initData() {

    }



    private void initBtnListenser() {

        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SettingSavePage.class);
                startActivity(intent);
            }
        });


        relativeLayout_blame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);




            }
        });


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(),"onclike",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), FilechooserActivity.class);
                startActivity(intent);




            }
        });


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


        relativeLayout_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


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


        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Browser.class);
                startActivity(intent);

            }
        }) ;

        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);

            }
        }) ;

    }




}