package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.sdk.WebChromeClient;

// 视频video

public class VideoActivity extends BaseActivity {

    private X5WebView x5webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        x5webView = findViewById(R.id.x5_webview_video);


        Intent intent = getIntent();
        String name = "http://111.231.191.26/See%20You%20Again.mp4";

        String videoUrl =  intent.getStringExtra("url");

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

        }


        //TbsVideo.openVideo(this,videoUrl);




    }


}