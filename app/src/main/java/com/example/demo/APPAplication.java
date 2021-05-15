package com.example.demo;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsListener;

import java.util.HashMap;

//X5 内核 继承Application  发现后台走子线程再view初始化之前没有加载完x5
//所以优化加入service

public class APPAplication extends Application {

	private Context context;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = this;


		preinitX5WebCore();
		//预加载x5内核
		Intent intent = new Intent(this, LoadX5Service.class);
		startService(intent);
	}

	private void preinitX5WebCore() {
		if (!QbSdk.isTbsCoreInited()) {
			Log.e("调试", " isTbsCoreInited  设置X5初始化完成的回调接口 " );
			QbSdk.preInit(getApplicationContext(), null);// 设置X5初始化完成的回调接口
		}
	}


}
