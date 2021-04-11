package com.example.demo.activity;


import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.sdk.TbsVideo;
import com.tencent.smtt.sdk.WebChromeClient;

// 视频video

public class VideoActivity extends BaseActivity {


    // 测试 播放视频

    private X5WebView x5webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        x5webView = findViewById(R.id.x5_webview_video);


        Intent intent = getIntent();
        String name = "http://111.231.191.26/See%20You%20Again.mp4";  //测试视频url

        String videoUrl =  intent.getStringExtra("url");
        //横屏播放
        if (x5webView.getX5WebViewExtension() != null) {
            //Toast.makeText(this, "开启X5全屏播放模式", Toast.LENGTH_LONG).show();
            Bundle data = new Bundle();
            data.putBoolean("standardFullScreen", false);// true表示标准全屏，false表示X5全屏；不设置默认false，
            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，
            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            x5webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);

            x5webView.loadUrl(name);
            //getWindow().setFormat(PixelFormat.TRANSLUCENT);
            x5webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
            x5webView.setWebChromeClient(new WebChromeClient());

        }



      //  TbsVideo.openVideo(this,name);  //直接打开视频也可以
      //  TbsVideo.openVideo(this, Environment.getExternalStorageDirectory() + “****.mp4”);  //发现播放视频不错，一句话就可以播放本地视频。 https://my.oschina.net/JiangTun/blog/968035


    }

}