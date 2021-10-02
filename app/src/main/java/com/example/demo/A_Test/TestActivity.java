package com.example.demo.A_Test;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.demo.R;
import com.example.demo.activity.CodeActivity;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.TbsVideo;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.net.MalformedURLException;
import java.net.URL;


public class TestActivity extends Activity {

    private Activity activity;
    private Context context;
    private X5WebView mWebView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        activity = this;
        context =this;
         editText = findViewById(R.id.editText);
        mWebView = findViewById(R.id.my_webview);


    }

    public void onClick(View v){
        String url1 = "https://vip.bljiex.com/?v=";
        String url2 = editText.getText().toString();


        mWebView.loadUrl(url1+url2);
        //https://www.cnblogs.com/lanxingren/p/10697106.html
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                try {
                    URL url = new URL(request.getUrl().toString());

                    if(url.toString().substring(0,22).equals("https://omts.tc.qq.com"))
                    {
                        Log.e("InternetActivity", url.toString());
                        TbsVideo.openVideo(context, url.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Log.e("InternetActivity", request.toString()); //https://omts.tc.qq.com
                return super.shouldInterceptRequest(view, request);
            }

        });



    }




}