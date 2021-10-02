package com.example.demo.mfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.activity.OpenFile;
import com.example.demo.model.GlobalData;
import com.example.demo.model.URLinfo;
import com.example.demo.model.bitmap;
import com.example.demo.utils.mRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import org.jetbrains.annotations.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.bitmap.getInternetPicture;


public class oneFragment extends Fragment {

    private Context context;
    private ImageView imageView;
    private FloatingActionButton fab;
    private List list;
    private Banner banner;
    private EditText edit_url;

    private String api ="https://vip.bljiex.com/?v=";
    private String albummid ="http://p7.qhimg.com/bdr/__85/t01f6858f53ad68e60a.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_one, container, false);
        context = getActivity();

        initView(view);
        initData();

        return view;
    }

    private void initView(View view) {

        list = new ArrayList();
        list.add("https://naiop.github.io/images/bg1.jpg");
        list.add("https://naiop.github.io/images/bg2.jpg");
        list.add("https://naiop.github.io/images/bg3.jpg");

        edit_url = view.findViewById(R.id.et_url);
        imageView=view.findViewById(R.id.img);
        fab = view.findViewById(R.id.fab);
        banner = (Banner) view.findViewById(R.id.banner);
    }

    private void initData() {
        lunbotu();
        initBtnListenser();

    }

    private void lunbotu() {
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置图片加载器
        banner.setImageLoader(new oneFragment.GlideImageLoader());
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setClipToOutline(true);
    }


    public  class GlideImageLoader extends ImageLoader {
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


    /**
     *  按钮事件list
     */
    private void initBtnListenser()
    {
        // FloatingActionButton 浮动操作按钮 事件
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "继续加载图片吗？", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getContext(), "确定", Toast.LENGTH_SHORT).show();
                                btimg();
                                String _url = "https://vip.bljiex.com/?v=https://v.qq.com/x/cover/mzc00200lxzhhqz/d0040ofabxk.html";
                                //String _url ="http://c.y.qq.com/rsc/fcgi-bin/fcg_user_created_diss?hostuin=1596782257&size=1000&format=json";
                                mRequest.loadData(context,edit_url.getText().toString().isEmpty() ? _url : edit_url.getText().toString());
                            }
                        }).show();


            }
        });

        //Banner  点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                 Toast.makeText(getContext(),"position"+position, Toast.LENGTH_SHORT).show();
            }
        });

        //Banner给任意view设置圆角outline.setRoundRect()方法实现
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });



    }



    public void btimg()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String Getimgpath ="https://img.xjh.me/random_img.php?return=json";

                String urlpath = URLinfo.getImgurl();
                Bitmap bitmap= getInternetPicture(albummid);
                //imageView.setImageBitmap(bitmap);

                Message message = handler.obtainMessage();
                if(bitmap !=null){
                    message.what = 1;
                    message.obj = bitmap; //获取成功
                }else {
                    message.what = 0;
                    message.obj = "获取失败";
                }
                // 发消息通知主线程更新UI
                handler.sendMessage(message);

            }
        }).start();
    }


    /**
     * 线程 ;安卓中的网络访问要在子线程中访问 UI只能在主线程程中更新
     */
    @SuppressLint("HandlerLeak") //线程
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Bitmap s = (Bitmap) msg.obj;

                    Toast.makeText(getContext(),"ok",Toast.LENGTH_SHORT).show();

                    imageView.setImageBitmap(s);
                    break;
                case 2:
                    String ss = (String) msg.obj;
                    Toast.makeText(getContext(),ss,Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

















}