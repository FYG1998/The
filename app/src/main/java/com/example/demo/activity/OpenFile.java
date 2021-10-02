package com.example.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.demo.base.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.File;

public class OpenFile extends BaseActivityTwo {
    private X5WebView x5webView;
    private RelativeLayout rlRoot;
    private TbsReaderView tbsReaderView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openfile);

        x5webView = findViewById(R.id.x5_webview_video);
        rlRoot = findViewById(R.id.rl_root);

        //主要使用到了TbsReaderView类，并且给Activity实现ReaderCallback接口，并且实现其接口方法（可以不处理，但是需实现）。初始化TbsReaderView需要动态构建，如下：
         tbsReaderView = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });

        final Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("path");
        }

      // url = "/storage/emulated/0/A_Test/test.pdf";

        rlRoot.addView(tbsReaderView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));//装载TbsReaderView的视图
        openFile();

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