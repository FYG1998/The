package com.example.demo.mfragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.adapter.MvListBaseAdapter;
import com.example.demo.model.URLinfo;
import com.example.demo.model.mCallback;
import com.example.demo.model.mConfig;
import com.example.demo.model.mOKHttp;
import com.example.demo.utils.ProgressDialogUtil;
import com.example.demo.utils.playUrl;
import com.tencent.smtt.sdk.TbsVideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.DOWNLOAD_SERVICE;
import static com.example.demo.model.mFileTool.saveBit;
import static com.example.demo.model.mFileTool.saveIO1;


public class threeFragment extends Fragment {

    private Context context;
    protected View view;
    private TextView textView;
    private EditText editText;
    private Button btngo;
    private ImageView imageView_cancel;
    private RelativeLayout but_notice;
    private ListView mListView;
    private View inflate;

    //在Fragment中实例化控件
    void initView(View view) {
        editText = (EditText) view.findViewById(R.id.et_search);
        btngo = (Button) view.findViewById(R.id.go);
        textView = (TextView) view.findViewById(R.id.text_notice);
        mListView = (ListView) view.findViewById(R.id.fragment_list);//实例化listview 组件
        imageView_cancel = (ImageView) view.findViewById(R.id.cancel);
        but_notice = (RelativeLayout) view.findViewById(R.id.but_notice);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_three, container, false);
        context =getActivity();

        initView(view);
        initData();
        return view;
    }

    private void initData() {

        mImg(); //下载图片
        initBtnListenser();
        textView.setText(URLinfo.getmTextnotice()); //notice
        mtv_List2("抖音");
    }


    //获取导播图
    public void mImg() {
        //第一步获取okHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        //第二步构建Request对象
        Request request = new Request.Builder()
                //.url(URLinfo.imgurl)
                .url("http://bz.eleuu.com/api.php?cid=phone&do=get2tag&tag=-4&page=1&show=location")
                .get()
                .build();
        //第三步构建Call对象
        Call call = client.newCall(request);
        //第四步:异步get请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream flsinputStream = response.body().byteStream();//得到图片的流

                saveBit(flsinputStream);

            }
        });
    }

    //点击事件  Listenser
    public void initBtnListenser() {
        //点击 mv list 条目的事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                List<Map<String, Object>> list; //定义list
                list = MvListBaseAdapter.getLits();  //接受数据
                Map map = list.get(position);
                String mvid = (String) map.get("mvmid");
                String name = (String) map.get("name");

                playUrl.play(mvid);
                show_dialog(name);
            }
        });


        //imageButton 搜索事件
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //onClick方法中调用如下三行代码，获取焦点失去焦点
                v.setFocusableInTouchMode(true);
                v.requestFocus();
                v.setFocusableInTouchMode(false);

                if (!(TextUtils.isEmpty(editText.getText().toString()))) {
                    mv_List();
                } else {
                    Toast.makeText(getActivity(), "搜索关键字不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //edittext 事件  ,edittext失去焦点收起软键盘
        //setOnFocusChangeListener  焦点事件
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            //v 发生变化的视图    hasFocus:用来判断视图是否获得了焦点
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

            }
        });


        //软键盘的监听事件     android:imeOptions="actionSearch"
        //https://www.cnblogs.com/whycxb/p/9468869.html

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:

                        mv_List();
                        InputMethodManager imm = ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        break;

                    default:
                        break;
                }

                return true;

            }
        });


        imageView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });


        but_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext(), R.style.style_dialog);
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_notice, null);
                dialog.setContentView(view); //将布局设置给Dialog
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM); //设置Dialog从窗体底部弹出
                WindowManager.LayoutParams lp = window.getAttributes();//获得窗体的属性
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);  //将属性设置给窗体
                window.setWindowAnimations(R.style.style_dialog);  //添加动画
                dialog.show();//显示对话框
            }
        });


    }


    public void show_dialog(String name) {
        final String pathname = name;

        final Dialog dialog = new Dialog(getContext(), R.style.style_dialog);
        inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
        dialog.setContentView(inflate); //将布局设置给Dialog

        //初始化控件
        Button bf = inflate.findViewById(R.id.play);
        Button down = inflate.findViewById(R.id.down);


        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); //设置Dialog从窗体底部弹出
        WindowManager.LayoutParams lp = window.getAttributes();//获得窗体的属性
        //  lp.y = 0;//设置Dialog距离底部的距离
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);  //将属性设置给窗体
        window.setWindowAnimations(R.style.style_dialog);  //添加动画
        dialog.show();//显示对话框


        bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Map<String, Object>> list = mConfig.getMvurllist();
                int i = list.size() - 1;
                Map map = list.get(i);
                String cn = (String) map.get("cn");
                String vkey = (String) map.get("vkey");
                String url = (String) map.get("url");
                String videoUrl = url + vkey + "/" + cn + "?fname=" + cn;
                Log.d("调试", videoUrl);
                Toast.makeText(getActivity(), "开启X5播放模式", Toast.LENGTH_LONG).show();

                getActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT);
                TbsVideo.openVideo(context, videoUrl);
                dialog.dismiss();

            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Map<String, Object>> list = mConfig.getMvurllist();
                int i = list.size() - 1;
                Map map = list.get(i);
                String cn = (String) map.get("cn");
                String vkey = (String) map.get("vkey");
                String url = (String) map.get("url");
                String videoUrl = url + vkey + "/" + cn + "?fname=" + cn;

                //DownMv(pathname,videoUrl);
                mvdown(videoUrl, pathname);

                Toast.makeText(getActivity(), "正在下载", Toast.LENGTH_LONG).show();


                dialog.dismiss();
            }
        });

    }


    //获取mv list的方法
    public void mv_List() {
        final String misuelist = URLinfo.musiclisturl1 + editText.getText().toString() + URLinfo.musiclisturl2;
        mOKHttp.mConfig(misuelist).getRequest(new mCallback() {
            @Override
            public void onSuccess(final String res) {//成功的okhttp回
                getActivity().runOnUiThread(new Runnable() { //线程
                    @Override
                    public void run() { //主线程操作

                        String jsondta = res;
                        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                        if (jsondta != null) {
                            try {

                                JSONObject json = new JSONObject(jsondta);
                                JSONObject json_data = json.getJSONObject("data");
                                JSONObject json_song = json_data.getJSONObject("song");
                                JSONArray json_list = json_song.getJSONArray("list");//获取到了list （60条记录） ;[]集合json


                                for (int i = 0; i < json_list.length(); i++) {
                                    Map<String, Object> map = new HashMap<String, Object>();

                                    Log.e("调试", String.valueOf(json_list.length()));
                                    JSONObject json_listobj = json_list.getJSONObject(i);  //把集合[]json 转换 数组 {}
                                    String json_title = json_listobj.getString("title"); //获取歌曲 title
                                    String json_mid = json_listobj.getString("mid"); //获取歌曲 mid

                                    JSONObject json_mv = json_listobj.getJSONObject("mv");
                                    String mvmid = json_mv.getString("vid");

                                    JSONObject json_img = json_listobj.getJSONObject("album");

                                    if (mvmid.length() > 0) {
                                        String img = json_img.getString("pmid");

                                        map.put("name", json_title);
                                        map.put("mid", json_mid);
                                        map.put("mvmid", mvmid);
                                        map.put("pmid_img", img);

                                        list.add(map);
                                    }


                                }
                                Log.d("map", list.toString());


                                MvListBaseAdapter adapter = new MvListBaseAdapter(getContext());
                                adapter.setList(list);

                                        /*SimpleAdapter adapter = new SimpleAdapter(
                                                getActivity(),
                                                list,R.layout.item_list,
                                                new String[]{"name","mid"},
                                                new int[]{R.id.name,R.id.mid});*/

                                mListView.setAdapter(adapter);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
            }
        });

    }

    //获取mv list的方法
    public void mtv_List2(String t) {

        final String misuelist = URLinfo.musiclisturl1 + t + URLinfo.musiclisturl2;

        mOKHttp.mConfig(misuelist).getRequest(new mCallback() {
            @Override
            public void onSuccess(final String res) {//成功的okhttp回调
                getActivity().runOnUiThread(new Runnable() { //线程
                    @Override
                    public void run() { //主线程操作

                        String jsondta = res;

                        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

                        if (jsondta != null) {
                            try {

                                JSONObject json = new JSONObject(jsondta);
                                JSONObject json_data = json.getJSONObject("data");
                                JSONObject json_song = json_data.getJSONObject("song");
                                JSONArray json_list = json_song.getJSONArray("list");//获取到了list （60条记录） ;[]集合json

                                for (int i = 0; i < json_list.length(); i++) {
                                    Map<String, Object> map = new HashMap<String, Object>();

                                    JSONObject json_listobj = json_list.getJSONObject(i);  //把集合[]json 转换 数组 {}
                                    String json_title = json_listobj.getString("title"); //获取歌曲 title
                                    String json_mid = json_listobj.getString("mid"); //获取歌曲 mid

                                    JSONObject json_mv = json_listobj.getJSONObject("mv");
                                    String mvmid = json_mv.getString("vid");

                                    JSONObject json_img = json_listobj.getJSONObject("album");


                                    if (mvmid.length() > 0) {
                                        String img = json_img.getString("pmid");

                                        map.put("name", json_title);
                                        map.put("mid", json_mid);
                                        map.put("mvmid", mvmid);
                                        map.put("pmid_img", img);

                                        list.add(map);
                                    }

                                }
                                Log.e("map", list.toString());


                                MvListBaseAdapter adapter = new MvListBaseAdapter(getContext());
                                adapter.setList(list);

                                mListView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
            }
        });

    }


    /**
     * ok http 文件流下载
     * 加入线程后台下载
     *
     * @param url
     * @param pathname
     */
    public void mvdown(String url, final String pathname) {
        //第一步获取okHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        //第二步构建Request对象
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        //第三步构建Call对象
        Call call = client.newCall(request);
        //第四步:异步get请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final InputStream flsinputStream = response.body().byteStream();//得到图片的流

                //创建子线程
                Thread myThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Boolean b = saveIO1(flsinputStream, pathname);
                            if (b) {

                                Message msg = new Message();
                                msg.what = 1;
                                handler.sendMessage(msg);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                myThread.start();//启动线程


            }
        });
    }


    /**
     * 线程 ;安卓中的网络访问要在子线程中访问 UI只能在主线程程中更新
     */
    @SuppressLint("HandlerLeak") //线程
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    /**
     * Android系统下载管理DownloadManager
     *
     * @param filename
     * @param downloadUrl
     */
    private void DownMv(String filename, String downloadUrl) {
        //创建request对象  下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        //设置什么网络情况下可以下载
        //request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //设置漫游状态下是否可以下载
        request.setAllowedOverRoaming(false);
        //设置通知栏的标题
        request.setTitle(filename);
        //设置通知栏的message
        request.setDescription("正在下载.....");

        //设置文件存放目录   xml-->mpath
        //http://gelitenight.github.io/android/2017/01/29/solve-FileUriExposedException-caused-by-file-uri-with-FileProvider.html?utm_source=tuicool#section-3

        //request.setDestinationInExternalFilesDir(getActivity(), Environment.DIRECTORY_DOWNLOADS,filename+"mv.mp4");

        String p = "/storage/emulated/0/XXXXXXX";
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename + ".mp4");

        Log.e("path", Environment.DIRECTORY_DOWNLOADS);

//------------------------------------------------------------------>>>>>>>
        // request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().toString(),filename+"mv.mp4");
        //获取系统服务
        DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
        //进行下载
        downloadManager.enqueue(request);
    }


}