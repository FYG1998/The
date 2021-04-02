package com.example.demo.tencent_tbs;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.tencent.smtt.sdk.ValueCallback;

public class FilechooserActivity extends BaseActivity {

    /**
     * X5内核 用于展示文件选择器
     */

    private X5WebView webView;
    private Button button;
    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filechooser);

        webView = (X5WebView) findViewById(R.id.web_filechooser);
        button = findViewById(R.id.bttest);
        webView = (X5WebView) findViewById(R.id.web_filechooser);
        open();

        /*webView.setWebChromeClient(new WebChromeClient() {
            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                Log.i("test", "openFileChooser 1");
                FilechooserActivity.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsgs) {
                Log.i("test", "openFileChooser 2");
                FilechooserActivity.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android  > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.i("test", "openFileChooser 3");
                FilechooserActivity.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android  >= 5.0
            public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             FileChooserParams fileChooserParams) {
                Log.i("test", "openFileChooser 4:" + filePathCallback.toString());
                FilechooserActivity.this.uploadFiles = filePathCallback;
                openFileChooseProcess();
                return true;
            }

        });

        webView.loadUrl("file:///android_asset/webpage/fileChooser.html");*/

    }

    public void open()
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooseProcess();
            }
        });
    }

    private void openFileChooseProcess() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i, "test"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

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
     */
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (this.webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }



}