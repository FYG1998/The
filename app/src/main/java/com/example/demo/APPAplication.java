package com.example.demo;
import android.app.Application;
import android.util.Log;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

//X5 内核 继承Application

public class APPAplication extends Application {

	private static APPAplication context;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = this;

		initData();
		initView();


	}
	public static APPAplication getContext() {
		return context;
	}

	private void initView() {

		//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
		QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

			@Override
			public void onViewInitFinished(boolean arg0) {
				// TODO Auto-generated method stub
				//x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
				Log.d("调试", " onViewInitFinished is " + arg0);
			}

			@Override
			public void onCoreInitFinished() {
				// TODO Auto-generated method stub
				Log.e("调试", "APPAplication --初始化X5失败 ");

			}
		};
		//x5内核初始化接口
		QbSdk.initX5Environment(getContext(),  cb);


	}


	private void initData() {
	}



}
