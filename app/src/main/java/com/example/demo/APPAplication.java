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
/**
 * APP全局类，继承Application
 * Created
 */
public class APPAplication extends Application {

	//提供全局的上下文对象
	private static Context appContext;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		appContext = this;


		//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
		QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

			@Override
			public void onViewInitFinished(boolean arg0) {
				// TODO Auto-generated method stub
				//x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
				Log.d("app", " onViewInitFinished is " + arg0);
			}

			@Override
			public void onCoreInitFinished() {
				// TODO Auto-generated method stub
			}
		};
		//x5内核初始化接口
		QbSdk.initX5Environment(getApplicationContext(),  cb);


		/*//全局初始化x5内核
		preinitX5WebCore();
		Intent intent = new Intent(this, LoadX5Service.class);
		startService(intent);*/
	}

	private void preinitX5WebCore() {
		if (!QbSdk.isTbsCoreInited()) {
			Log.e("调试", " isTbsCoreInited  设置X5初始化完成的回调接口 " );
			QbSdk.preInit(getApplicationContext(), null);// 设置X5初始化完成的回调接口
		}
	}

	public static Context getAppContext(){
		return appContext;
	}


}
