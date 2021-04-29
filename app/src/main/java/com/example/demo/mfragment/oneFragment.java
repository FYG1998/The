package com.example.demo.mfragment;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.activity.VideoActivity;
import com.example.demo.tools.URLinfo;
import com.example.demo.tools.bitmap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tencent.smtt.sdk.QbSdk;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;


import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class oneFragment extends Fragment {

     View mRootView;

     Button button;
     ImageView imageView;
     String albummid ="http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg";


    private EditText mUrl;
    private Button mGo;
    private String api ="https://vip.bljiex.com/?v=";
    private TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView =inflater.inflate(R.layout.fragment_one, container, false);

        imageView=mRootView.findViewById(R.id.img);
        button = mRootView.findViewById(R.id.buimg);
        btimg();


        mUrl =	mRootView.findViewById(R.id.editUrlone);
        mGo = mRootView.findViewById(R.id.btnGo1);
        textView = mRootView.findViewById(R.id.showtext);
        initBtnListenser();


        FloatingActionButton fab = mRootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        List images = new ArrayList();
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg");
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg");
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg");


        Banner banner = (Banner) mRootView.findViewById(R.id.banner);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.Accordion);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置图片加载器
        banner.setImageLoader(new fiveFragment.GlideImageLoader());
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



        return mRootView;
    }



    public class GlideImageLoader extends ImageLoader {
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


    public void btimg()
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //   Bitmap bitmap = getLoacalBitmap(imgPath);
                       //getalbummidim(albummid,imageView);
                     //  GetNetIp(URLinfo.NoticeUrl);


                       // String urlpath = "\/\/img.xjh.me\/desktop\/img\/57992976_p0.jpg";

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
        });
    }



    public static void getalbummidim(String albummid, @NotNull final ImageView imageView) {
        //final String url = "http://y.gtimg.cn/music/photo_new/T002R90x90M000" + albummid + ".jpg?max_age=2592000";
        //Log.d("调试输出", url);

        final String url=albummid;
        imageView.setImageBitmap(bitmap.getInternetPicture(url));

    }


    //公告栏文字  http 获取

    public void GetNetIp(String UrlPath) {

        String str = null;
        // 1、确定网址
        // http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg
        String urlpath = UrlPath;
        // 2、获取Uri
        try {
            URL uri = new URL(urlpath);

            // 3、获取连接对象、此时还没有建立连接
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();

            // 4、初始化连接对象
            // 设置请求的方法，注意大写
            connection.setRequestMethod("GET");
            // 读取超时
            connection.setReadTimeout(5000);
            // 设置连接超时
            connection.setConnectTimeout(5000);
            // 5、建立连接
            connection.connect();

            // 6、获取成功判断,获取响应码
            // 6、获取成功判断,获取响应码
            if (connection.getResponseCode() == 200) {
                // 7、拿到服务器返回的流，客户端请求的数据，就保存在流当中
                InputStream is = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                str = reader.readLine();

                is.close();



                Log.i("tag",str);

                // 9、把图片设置到UI主线程
                // ImageView中,获取网络资源是耗时操作需放在子线程中进行,通过创建消息发送消息给主线程刷新控件；

                Log.i("tp", "网络请求成功");

            } else {
                Log.v("tag", "网络请求失败");
               // bm = null;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Bitmap getInternetPicture(String UrlPath) {
        Bitmap bm = null;
        // 1、确定网址
        // http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg
        String urlpath = UrlPath;
        // 2、获取Uri
        try {
            URL uri = new URL(urlpath);

            // 3、获取连接对象、此时还没有建立连接
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            // 4、初始化连接对象
            // 设置请求的方法，注意大写
            connection.setRequestMethod("GET");
            // 读取超时
            connection.setReadTimeout(5000);
            // 设置连接超时
            connection.setConnectTimeout(5000);
            // 5、建立连接
            connection.connect();

            // 6、获取成功判断,获取响应码
            if (connection.getResponseCode() == 200) {
                // 7、拿到服务器返回的流，客户端请求的数据，就保存在流当中
                InputStream is = connection.getInputStream();
                // 8、从流中读取数据，构造一个图片对象GoogleAPI
                bm = BitmapFactory.decodeStream(is);  ///把流转化为Bitmap图片

                is.close();

                // 9、把图片设置到UI主线程
                // ImageView中,获取网络资源是耗时操作需放在子线程中进行,通过创建消息发送消息给主线程刷新控件；

                Log.i("tp", "网络请求成功");

            } else {
                //Log.v("tag", "网络请求失败");
                bm = null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bm;

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






    private void initBtnListenser()
    {
        mGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String url = api + mUrl.getText().toString();

                //intent 传值 https://blog.csdn.net/qq_36721053/article/details/53637667
                Intent intent =new Intent(getActivity(), VideoActivity.class); //启动
                intent.putExtra("url", url);
                startActivity(intent);

            }
        });



        //setOnFocusChangeListener  焦点事件
        mUrl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mGo.setVisibility(View.VISIBLE);


                } else {
                    mGo.setVisibility(View.GONE);

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

        });



        //文本变化监听器addTextChangedListener中TextWatcher方法三个方法意义
        mUrl.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                String url = null;

                if (mUrl.getText() != null)
                {
                    url = mUrl.getText().toString();
                }

                if (url == null || mUrl.getText().toString().equalsIgnoreCase(""))
                //equalsIgnoreCase() 方法用于将字符串与指定的对象比较，不考虑大小写。如果给定对象与字符串相等，则返回 true；否则返回 false。
                {
                    mGo.setText("请输入网址");
                    mGo.setTextColor(0X6F0F0F0F);
                }
                else
                {
                    mGo.setText("进入");
                    mGo.setTextColor(0X6F0000CD);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }
        });


    }












}