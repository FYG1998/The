package com.example.demo.mfragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.demo.R;
import com.example.demo.tools.URLinfo;
import com.example.demo.tools.bitmap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tencent.smtt.sdk.QbSdk;

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


public class oneFragment extends Fragment {

     View mRootView;

     Button button;
     ImageView imageView;
     String albummid ="http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView =inflater.inflate(R.layout.fragment_one, container, false);

        imageView=mRootView.findViewById(R.id.img);
        button = mRootView.findViewById(R.id.buimg);
        btimg();


        FloatingActionButton fab = mRootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        return mRootView;
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



}