package com.example.demo.umodel;

import android.util.Log;
import com.example.demo.BaseActivity;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class mOKHttp {
    //创建一个单例模式
    //参考博客： https://gitee.com/hwdroid/myapp/

    private static OkHttpClient client;
    public  static  mOKHttp okhttp = new mOKHttp();
    private static String requestUrl;

    public mOKHttp(){
    }

    public  static mOKHttp mConfig(String url){
         client = new OkHttpClient.Builder().build();  //第一步获取okHttpClient对象
        requestUrl=url;
        return okhttp;
    }

    //post请求  待写
    public void postRequest(mCallback callback){


    }

    //get请求  请求字符串 json
    public void getRequest(final mCallback callback){

        //SharedPreferences sp = context.getSharedPreferences("sp_ttit", MODE_PRIVATE);
        //String token = sp.getString("token", "");后期写

        //第二步构建Request对象
        final Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .build();
        //第三步构建Call对象
         Call call = client.newCall(request);
         call.enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                 callback.onFailure(e);
                 Log.e("onFailure","失败");
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {

                 final String result = response.body().string();
                 callback.onSuccess(result);
                 Log.e("ttit", "成功"+result);
             }
         });

    }

    //get获取图片流，然后写入指定路径内存
    public static  void getRequestImgBitmap(final mCallback callback){

        //第二步构建Request对象
        final Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .build();
        //第三步构建Call对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
                Log.e("onFailure","失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream flsinputStream = response.body().byteStream();//得到图片的流
                BaseActivity baseActivity =new BaseActivity();
                baseActivity.saveBit(flsinputStream);
                callback.onSuccess("图片获取成功");

                Log.e("ttit", "成功");
            }
        });

    }


}
