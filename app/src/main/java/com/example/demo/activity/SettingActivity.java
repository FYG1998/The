package com.example.demo.activity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.example.demo.model.PermissionListener;
import com.example.demo.model.spInfo;
import com.example.demo.update.OkGoUpdateHttp;
import com.example.demo.utils.PackageUtils;
import com.example.demo.utils.ProgressDialogUtil;
import com.example.demo.utils.SPDataUtils;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.listener.ExceptionHandler;
import com.vector.update_app.listener.IUpdateDialogFragmentListener;
import com.vector.update_app.utils.AppUpdateUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingActivity extends BaseActivityTwo {


    private Context context;
    private String mUpdateUrl = "https://naiop.github.io/test/updateApp.json";
    private Switch mSwitch;
    private Button button1,button2,button3 ,btn_update_check ;
    private EditText editTexttime ;
    private TextView texttbsstate,tv_ver_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context =this;

        initView();
        initData();

    }

    private void initView() {
        setTitle("Setting");  // BaseActivityTwo 定义的 标题名称
        setBackBtn(); //继承BaseActivityTwo里定义的 返回事件


        spInfo info = SPDataUtils.getspInfo(context);
        mSwitch = (Switch) findViewById(R.id.sw_wifi);
        mSwitch.setChecked(info.getMboolean());

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        btn_update_check =findViewById(R.id.btn_update_check);
        editTexttime = findViewById(R.id.editTexttime);
        editTexttime.setText(info.getTime()) ;
        texttbsstate = findViewById(R.id.tbs_state);
        tv_ver_setting = findViewById(R.id.tv_ver_setting);


    }

    private void initData() {

        //初始化tbs state
        X5WebView x5WebView = new X5WebView(this);
        if(x5WebView.getX5WebViewExtension() != null){ texttbsstate.setText("ture");}
        initBtnListenser();

        tv_ver_setting.setText(tv_ver_setting.getText()+"  "+PackageUtils.packageName(context));
    }


    //button 事件
    private void initBtnListenser() {


        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    boolean flag = SPDataUtils.saveUserInfo(context,"switch" , isChecked);
                    if (flag){
                        showToast("开启");
                    }
                }else {

                    boolean flag = SPDataUtils.saveUserInfo(context,"switch", isChecked);
                    if (flag) {
                        showToast("关闭");
                    }
                }

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("请点击Switch按钮");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("推荐给好基友");
            }
        });

        //sp 保存 导播图时间
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time = editTexttime.getText().toString();
                boolean flag = SPDataUtils.saveUserInfo(context,"mtime",time);

                if(flag){
                    showToast("保存成功");
                }

            }
        });

        btn_update_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionWithWriteStorage();

            }
        });

    }


    /**
     * 授权并更新
     */
    private void requestPermissionWithWriteStorage(){
        requestRuntimePermissions(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, new PermissionListener() {
            @Override
            public void onGranted() {
                checkAppUpdate();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                showToast("请授予存储卡写入权限！");
            }
        });
    }

    /**
     * 检查app版本
     */
    private void checkAppUpdate() {

        if( !Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "无法获取Sdcard存储卡", Toast.LENGTH_SHORT).show();
            return;
        }
        String path =Environment.getExternalStorageDirectory()+"/the.apk";
        Map<String, String> params = new HashMap<String, String>();
        params.put("appKey", "b97bea014531123f94c3ba7b7afbaad2");
        params.put("appVersion", AppUpdateUtils.getVersionName(this));
        params.put("key1", "value2");
        params.put("key2", "value3");

        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new OkGoUpdateHttp())
                //必须设置，更新地址
                .setUpdateUrl(mUpdateUrl)
                //全局异常捕获
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(false)
                //不显示通知栏进度条
//                .dismissNotificationProgress()
                //是否忽略版本
//                .showIgnoreVersion()
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                .setParams(params)
                //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度，如果是强制更新，则设置无效
//                .hideDialogOnDownloading()
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
                .setTopPic(R.mipmap.top_8)
                //为按钮，进度条设置颜色。
                .setThemeColor(0xffffac5d)
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
//               .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
//                .setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                .setUpdateDialogFragmentListener(new IUpdateDialogFragmentListener() {
                    @Override
                    public void onUpdateNotifyDialogCancel(UpdateAppBean updateApp) {
                        //用户点击关闭按钮，取消了更新，如果是下载完，用户取消了安装，则可以在 onActivityResult 监听到。

                    }
                })
                //不自动，获取
//                .setIgnoreDefParams(true)
                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            final String newVersion = jsonObject.optString("new_version");
                            updateAppBean
                                    //（必须）是否更新Yes,No
                                    .setUpdate(jsonObject.optString("update"))
                                    //（必须）新版本号，
                                    .setNewVersion(newVersion)
                                    //（必须）下载地址
                                    .setApkFileUrl(jsonObject.optString("apk_file_url"))
                                    //测试下载路径是重定向路径
//                                    .setApkFileUrl("http://openbox.mobilem.360.cn/index/d/sid/3282847")
//                                    .setUpdateDefDialogTitle(String.format("AppUpdate 是否升级到%s版本？", newVersion))
                                    //（必须）更新内容
//                                    .setUpdateLog(jsonObject.optString("update_log"))
                                    //测试内容过度
//                                    .setUpdateLog("测试")
                                    .setUpdateLog(jsonObject.optString("update_log"))
                                    //大小，不设置不显示大小，可以不设置
                                    .setTargetSize(jsonObject.optString("target_size"))
                                    //是否强制更新，可以不设置
                                    .setConstraint(jsonObject.getBoolean("constraint"))
                                    //设置md5，可以不设置
                                    .setNewMd5(jsonObject.optString("new_md5"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return updateAppBean;
                    }

                    @Override

                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
                        ProgressDialogUtil.showProgressDialog(SettingActivity.this);
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
                        ProgressDialogUtil.cancelProgressDialog(SettingActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp(String error) {
                        Toast.makeText(SettingActivity.this, "没有新版本", Toast.LENGTH_SHORT).show();
                    }
                });
    }





}