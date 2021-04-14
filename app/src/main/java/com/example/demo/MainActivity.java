package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.demo.tools.URLinfo;
import com.example.demo.tools.mCallback;
import com.example.demo.tools.mOKHttp;
import com.example.demo.tools.spInfo;
import com.example.demo.utils.SPDataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import static com.example.demo.tools.URLinfo.setImgurl;


public class MainActivity extends BaseActivity {

    private ImageView imageView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context =this;

        imageView=findViewById(R.id.imageview);
        String imgPath = Environment.getExternalStorageDirectory().getPath() + "/A_Test/b";

        Bitmap bitmap = getLoacalBitmap(imgPath);   //从本地取图片(在cdcard中获取)
        if(fileIsExists(imgPath)){  //判断文件是否存在
            imageView .setImageBitmap(bitmap); //设置Bitmap
        }else {
            imageView.setImageDrawable( ResourcesCompat.getDrawable(getResources(), R.drawable.splash, null)); //不存在加载原先设置
        }

        Guidechart(); // 获取导播图网址
        getAsync1();

        spInfo info = SPDataUtils.getspInfo(context);
        final int s = Integer.parseInt(info.getUpass());
        Log.e("ttyy", String.valueOf(s));

        //创建子线程 延续
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(s);//使程序休眠一秒
                    mIntent(CoreFragment.class); //intent跳转
                    finish();//关闭当前活动
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程


 /*       //方法二
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, CoreFragment.class);
                startActivity(intent);
                finish();
            }
        }, 1000);*/


    }


    //获取导播图网址
    public  void Guidechart(){
        mOKHttp.mConfig(URLinfo.getimgUrl).getRequest(new mCallback() {
            @Override
            public void onSuccess(String res) {  //okhttp 成功回调
                String json = res;
                String contr ="";
                if(json!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        contr = jsonObject.optString("content"); //获取到content 数据

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                String zzbds="(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?"; //截取字符串网址 正则表达式
                Pattern pattern= Pattern.compile(zzbds);   //pattern 正则表达式类
                Matcher matcher = pattern.matcher(contr.toString());
                while (matcher.find())
                {
                    String data =matcher.group();
                    Log.e("ttt",data);
                    setImgurl(data);
                }
            }

            @Override
            public void onFailure(Exception e) { //okhttp 失败回调
            }
        });

    }

    //公告栏文字 okhttp 获取
    public void getAsync1(){

        mOKHttp.mConfig(URLinfo.NoticeUrl).getRequest(new mCallback() {
            @Override
            public void onSuccess(final String res) {//成功的okhttp回调
               runOnUiThread(new Runnable() { //线程
                    @Override
                    public void run() { //主线程操作
                        String json = res;
                        String contr ="";
                        if(json!=null){
                            try {
                                JSONObject jsonObject = new JSONObject(json); //Jsons 解析
                                contr = jsonObject.optString("content");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // java 正则表达式 截取 https://blog.csdn.net/u013456370/article/details/78490052/
                        String regex="json(.*?)json";
                        Pattern pattern= Pattern.compile(regex);   //pattern 正则表达式类
                        Matcher matcher = pattern.matcher(contr.toString());

                        while (matcher.find())
                        {
                            String data =matcher.group(1);
                            URLinfo.setmTextnotice(data);
                        }

                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
            }
        });

    }






}