package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.demo.base.BaseActivity;
import com.example.demo.base.CoreFragment;
import com.example.demo.model.URLinfo;
import com.example.demo.model.Config;
import com.example.demo.model.mCallback;
import com.example.demo.model.mOKHttp;
import com.example.demo.model.spInfo;
import com.example.demo.utils.SPDataUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.core.content.res.ResourcesCompat;
import static com.example.demo.model.URLinfo.setImgurl;


public class MainActivity extends BaseActivity {

    private ImageView imageView;
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity =this;
        ininView();
        initData();
    }

    private void ininView() {
        imageView = findViewById(R.id.imageview);

        String imgPath = Environment.getExternalStorageDirectory().getPath() + "/A_Test/b";
        final Bitmap bitmap = getLoacalBitmap(imgPath);   //从本地取图片(在cdcard中获取)
        if (fileIsExists(imgPath)) {  //判断文件是否存在
            imageView.setImageBitmap(bitmap); //设置Bitmap
        } else {
            imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.splash, null)); //不存在加载原先设置
        }

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按保存图片
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HH:mm:ss");// yyyy年MM月dd日 HH:mm:ss
                    Date date = new Date(System.currentTimeMillis());

                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/A_Test/" + simpleDateFormat.format(date) + ".jpg");
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                showToast("安");
                return false;//false 去掉震动
            }
        });
    }

    private void initData() {
        Guidechart(); // 获取导播图网址
        Responsion();
        downLoadDatabase();
        msleep();
    }

    // 使程序休眠几秒后跳转
    private void msleep() {
        spInfo info = SPDataUtils.getspInfo(context);
        final int s = Integer.parseInt(info.getTime());
        //创建子线程 延续
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(s);//使程序休眠一秒
                    mIntent(CoreFragment.class); //intent跳转
                    finish();//关闭当前活动
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程


 /*       //方法二
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, CoreFragment.class);
                startActivity(intent);
                finish();
            }
        }, 1000);*/
    }


    //获取导播图网址
    public void Guidechart() {
        mOKHttp.mConfig(URLinfo.getimgUrl).getRequest(new mCallback() {
            @Override
            public void onSuccess(String res) {  //okhttp 成功回调
                String json = res;
                String contr = "";
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        contr = jsonObject.optString("content"); //获取到content 数据

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                String zzbds = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?"; //截取字符串网址 正则表达式
                Pattern pattern = Pattern.compile(zzbds);   //pattern 正则表达式类
                Matcher matcher = pattern.matcher(contr.toString());
                while (matcher.find()) {
                    String data = matcher.group();
                    Log.e("ttt", data);
                    setImgurl(data);
                }
            }

            @Override
            public void onFailure(Exception e) { //okhttp 失败回调
            }
        });

    }

    //公告栏文字 okhttp 获取
    public void Responsion() {
        mOKHttp.mConfig(URLinfo.NoticeUrl).getRequest(new mCallback() {
            @Override
            public void onSuccess(final String res) {//成功的okhttp回调
                runOnUiThread(new Runnable() { //线程
                    @Override
                    public void run() { //主线程操作
                        String json = res;
                        String contr = "";
                        if (json != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(json); //Jsons 解析
                                contr = jsonObject.optString("content");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // java 正则表达式 截取 https://blog.csdn.net/u013456370/article/details/78490052/
                        String regex = "json(.*?)json";
                        Pattern pattern = Pattern.compile(regex);   //pattern 正则表达式类
                        Matcher matcher = pattern.matcher(contr.toString());

                        while (matcher.find()) {
                            String data = matcher.group(1);
                            URLinfo.setmTextnotice(data);
                            Log.d("TAG", data);
                        }

                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
            }
        });

        String a = "https://api.github.com/repos/square/retrofit/contributors";
        String b = "http://msearchcdn.kugou.com/new/app/i/search.php?cmd=302&keyword=%E5%91%A8%E6%9D%B0%E4%BC%A6";
        String c = "https://naiop.github.io/test/updateApp.json";

        /*OkGo.<String>get(Config.SongName("赵雷"))     // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.NO_CACHE)    // 缓存模式，详细请看缓存介绍
                //  .cacheTime(3000)//缓存时间
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        ProgressDialogUtil.showProgressDialog(activity);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        ToastUtil.showLong(context,response.body());

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }



                    @Override
                    public void onFinish() {
                        ProgressDialogUtil.cancelProgressDialog(activity);

                    }

                });*/








    }




    private void downLoadDatabase() {

        String zpl = "https://api.labelary.com/v1/printers/8dpmm/labels/4x6/0/";
        OkGo.<File>get(zpl)
                .headers("Accept", "application/pdf") //下载文件要添加headers
                .params("", Config.zplCode(null))
                .execute(new FileCallback(Config.mPath(), "test.pdf") {   //指定下载的路径  下载文件名
                    @Override
                    public void onSuccess(Response<File> response) {
                        Log.i("randomcode", "下载成功1 " + response.body());
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        Log.i("randomcode", "下载失败1 " + response.body());
                    }
                });

    }


}