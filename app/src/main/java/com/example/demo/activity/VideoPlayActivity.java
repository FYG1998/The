package com.example.demo.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.example.demo.tools.mConfig;
import com.tencent.smtt.sdk.WebChromeClient;
import java.util.List;
import java.util.Map;


// MV　video

public class VideoPlayActivity extends BaseActivity {


    private X5WebView x5webView;
    List<Map<String, Object>> list; //定义 List Map 接受  mConfig.getMvurllist()数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);

       // String videoUrl = "http://111.231.191.26/See%20You%20Again.mp4";  //测试视频地址


        list = mConfig.getMvurllist();
        int i = list.size()-1;
        Map map = list.get(i);
        String cn = (String) map.get("cn");
        String vkey = (String) map.get("vkey");
        String url = (String) map.get("url");
        String videoUrl = url +vkey +"/"+ cn + "?fname=" + cn;
        Intent intent=getIntent();
        String mUrl=intent.getStringExtra("mUrl");


        Log.e("mUrl",videoUrl);

        x5webView = findViewById(R.id.x5_webview);

     /*      //横屏播放
      if (x5webView.getX5WebViewExtension() != null) {
            //Toast.makeText(this, "开启X5全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();
            data.putBoolean("standardFullScreen", false);// true表示标准全屏，false表示X5全屏；不设置默认false，
            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，
            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            x5webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);

            x5webView.loadUrl(videoUrl);
            //getWindow().setFormat(PixelFormat.TRANSLUCENT);
            x5webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
            x5webView.setWebChromeClient(new WebChromeClient());

        }*/




        x5webView.loadUrl(videoUrl);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        x5webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        x5webView.setWebChromeClient(new WebChromeClient());



    }
}