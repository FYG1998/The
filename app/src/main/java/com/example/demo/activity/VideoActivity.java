package com.example.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.demo.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;


public class VideoActivity extends BaseActivityTwo {



    private X5WebView x5webView;
    private RelativeLayout rlRoot;

    TbsReaderView tbsReaderView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        x5webView = findViewById(R.id.x5_webview_video);
        rlRoot = findViewById(R.id.rl_root);

        //主要使用到了TbsReaderView类，并且给Activity实现ReaderCallback接口，并且实现其接口方法（可以不处理，但是需实现）。初始化TbsReaderView需要动态构建，如下：
         tbsReaderView = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });

        Intent intent = getIntent();
        String name = "http://111.231.191.26/See%20You%20Again.mp4";  //测试视频url

       url = "/storage/emulated/0/tencent/MicroMsg/Download/测试文档.doc";


        String videoUrl =  intent.getStringExtra("url");
        //横屏播放
       /* if (x5webView.getX5WebViewExtension() != null) {
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

        }*/

        rlRoot.addView(tbsReaderView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));//装载TbsReaderView的视图
        openFile();


      //  TbsVideo.openVideo(this,name);  //直接打开视频也可以
      //  TbsVideo.openVideo(this, Environment.getExternalStorageDirectory() + “****.mp4”);  //发现播放视频不错，一句话就可以播放本地视频。 https://my.oschina.net/JiangTun/blog/968035


    }


    private void openFile() {
        File file = new File(url);
        if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
        }
        Bundle bundle = new Bundle();
        bundle.putString("filePath", url);
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());

        Log.e("tag",bundle.toString());
        boolean result = tbsReaderView.preOpen(parseFormat(parseName(url)), false);
        if (result) {
            tbsReaderView.openFile(bundle);
        }
    }

    private String parseFormat(String fileName) {
        Log.e("tag",fileName);
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String parseName(String url) {
        String fileName = null;
        try {
            fileName = url.substring(url.lastIndexOf("/") + 1);
        } finally {
            if (TextUtils.isEmpty(fileName)) {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        }

        Log.e("tag",fileName);
        return fileName;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        tbsReaderView.onStop();
    }
}