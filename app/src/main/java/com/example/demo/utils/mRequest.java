package com.example.demo.utils;

import android.app.Activity;
import android.content.Context;

import com.example.demo.api.JsonCallback;
import com.example.demo.api.StringDialogCallback;
import com.example.demo.model.GlobalData;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class mRequest {
    /**
     * 测试用
     */
    public static void loadData(final Context context, String url) {
        OkGo.<String>get(url)               // 请求方式和请求url
                //.tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .headers("referer","https://y.qq.com/portal/player.html")
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.NO_CACHE)    // 缓存模式，详细请看缓存介绍
                //  .cacheTime(3000)//缓存时间
                .execute(new StringCallback() {

                    @Override
                    public void onStart(com.lzy.okgo.request.base.Request<String, ? extends com.lzy.okgo.request.base.Request> request) {
                        //网络请求前
                    }

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String jsonData = response.body();
                        GlobalData.getInstance().showSoapInfoWithToast(context,jsonData);

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        //网络请求错误
                    }



                    @Override
                    public void onFinish() {
                       // 网络请求成功

                    }

                });
    }
}
