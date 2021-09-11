package com.example.demo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.demo.base.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.sdk.ValueCallback;

public class FileChooser extends BaseActivityTwo {

    /**
     * X5内核 用于展示文件选择器
     */
    private X5WebView webView;
    private Button button;
    private TextView textView;
    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;

    private void initView() {

        setTitle("FileChoose");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件
        button = findViewById(R.id.bttest);
        textView =findViewById(R.id.pathfile);
        webView =  findViewById(R.id.web_filechooser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filechooser);

        initView();
        initData();


    }

    private void initData() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooseProcess();
            }
        });
    }



    private void openFileChooseProcess() {
        Intent intent  = new Intent(Intent.ACTION_GET_CONTENT);
        intent .addCategory(Intent.CATEGORY_OPENABLE);
        intent .setType("*/*");
        //从A页面使用startActivityForResult（）跳转到B页面，B页面点击返回时将新写入的值传回到A页面。
        startActivityForResult(Intent.createChooser(intent , "file"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        textView.setText(data.toString());

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    if (null != uploadFiles) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFiles.onReceiveValue(new Uri[]{result});
                        uploadFiles = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }

        }
    }


    /**
     * 确保注销配置能够被释放
     * onDestroy() android 生命周期
     * 当Activity(用户调用finish()或系统由于内存不足)被系统销毁杀掉时系统调用，（整个生命周期只调用1次）用来释放onCreate()方法中创建的资源，如结束线程等。
     */
    @Override
    protected void onDestroy() {
        if (this.webView != null)
        {
            webView.destroy();
        }
        super.onDestroy();
    }



}