package com.example.demo.mfragment;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Environment;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.activity.VideoPlayActivity;
import com.example.demo.adapter.MvAdapter;
import com.example.demo.adapter.mAdapter;
import com.example.demo.tools.SongData;
import com.example.demo.tools.URLinfo;
import com.example.demo.tools.mCallback;
import com.example.demo.tools.mConfig;
import com.example.demo.tools.mOKHttp;
import com.example.demo.tools.playUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.DOWNLOAD_SERVICE;
import static com.example.demo.tools.mFileTool.saveBit;
import static com.example.demo.tools.mFileTool.saveIO;


public class threeFragment extends Fragment {

    protected View mRootView;
    private TextView textView;
    private EditText editText;
    private ImageButton imageButton;
    private ImageView imageView_cancel;

    private ListView mListView;
    private View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView= inflater.inflate(R.layout.fragment_three, container, false);

        initView(mRootView);
        mImg(); //down img
        initBtnListenser();

        if(URLinfo.mTextnotice!="My Text Notice"){ textView.setText(URLinfo.getmTextnotice()); } //跑马灯判断
        mtv_List2("我们不一样");

        return mRootView;
    }


    //在Fragment中实例化控件
    void initView(View mRootView){
        editText=(EditText)mRootView.findViewById(R.id.et_search);
        imageButton=(ImageButton) mRootView.findViewById(R.id.go);
        textView=(TextView) mRootView.findViewById(R.id.text_notice);
        mListView=(ListView)mRootView.findViewById(R.id.fragment_list);//实例化listview 组件
        imageView_cancel = (ImageView) mRootView.findViewById(R.id.cancel);

    }

    //获取导播图
    public  void mImg() {
        //第一步获取okHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        //第二步构建Request对象
        Request request = new Request.Builder()
                .url(URLinfo.imgurl)
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
    public void initBtnListenser(){

        //点击 mv list 条目的事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                List<Map<String, Object>> list; //定义list
                list = MvAdapter.getLits();  //接受数据
                Map map = list.get(position);
                String mvid = (String) map.get("mvmid");
                String name = (String) map.get("name");


                playUrl.play(mvid);
                show_dialog(name);
                //Toast.makeText(getActivity(), mvid,Toast.LENGTH_SHORT).show();


            }
        });





        //imageButton 事件
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //onClick方法中调用如下三行代码，获取焦点失去焦点
                v.setFocusableInTouchMode(true);
                v.requestFocus();
                v.setFocusableInTouchMode(false);


                mv_List();

            }
        });


        //edittext 事件  ,edittext失去焦点收起软键盘
        //setOnFocusChangeListener  焦点事件
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            //v 发生变化的视图    hasFocus:用来判断视图是否获得了焦点
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager imm =  ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
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
                        InputMethodManager imm =  ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
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







    }

    //列表list
    public void mlist(){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String misuelist=URLinfo.musiclisturl1 + editText.getText().toString() + URLinfo.musiclisturl2;

                mOKHttp.mConfig(misuelist).getRequest(new mCallback() {
                    @Override
                    public void onSuccess(final String res) {//成功的okhttp回调
                        getActivity().runOnUiThread(new Runnable() { //线程
                            @Override
                            public void run() { //主线程操作

                                String songdata = res;


                                if(songdata!=null){
                                    try {

                                        ArrayList list = new ArrayList();
                                        JSONObject json = new JSONObject(songdata);
                                        JSONObject json_data = json.getJSONObject("data");
                                        JSONObject json_song = json_data.getJSONObject("song");
                                        JSONArray json_list = json_song.getJSONArray("list");
                                        SongData SongInfoDataList = new SongData();
                                        for (int i = 0; i < json_list.length(); i++) {

                                            JSONObject json_listobj = json_list.getJSONObject(i);
                                            JSONObject file = json_listobj.getJSONObject("file");
                                            JSONObject album = json_listobj.getJSONObject("album");
                                            SongInfoDataList = new SongData();

                                            String singername = json_listobj.getJSONArray("singer").getJSONObject(0).getString("name");
                                            SongInfoDataList.setSongname(json_listobj.getString("title"));
                                            SongInfoDataList.setSingername(singername);
                                            SongInfoDataList.setSize_128(file.getString("size_128"));
                                            SongInfoDataList.setSize_320( file.getString("size_320"));
                                            SongInfoDataList.setSize_ape(file.getString("size_ape"));
                                            SongInfoDataList.setSize_flac(file.getString("size_flac"));
                                            SongInfoDataList.setAlbum_mid(album.getString("mid"));
                                            SongInfoDataList.setMedia_mid( json_listobj.getString("mid"));

                                            list.add(SongInfoDataList);

                                            Log.e("调试输出",SongInfoDataList.getSingername());

                                        }

                                        Log.e("调试输出1",list.toString());
                                        mAdapter adapter = new mAdapter(getContext(),list);
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
        });

    }


    //mv download
    public  void mvdown(String url, final String pathname) {
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

                InputStream flsinputStream = response.body().byteStream();//得到图片的流

                saveIO(flsinputStream ,pathname);

            }
        });
    }

    //弹窗
    public void show_dialog(String name){

        final String pathname = name;

        final Dialog dialog = new Dialog(getContext(),R.style.style_dialog);
        inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
        dialog.setContentView(inflate); //将布局设置给Dialog

        //初始化控件

        Button bf = inflate.findViewById(R.id.play);
        Button down = inflate.findViewById(R.id.down);


        // play.setOnClickListener();
        // down.setOnClickListener(downClick);

        Window window = dialog.getWindow();
        window.setGravity( Gravity.BOTTOM); //设置Dialog从窗体底部弹出
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

                Intent intent =new Intent(getActivity(),VideoPlayActivity.class); //启动
                startActivity(intent);

                dialog.dismiss();

            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   List<Map<String, Object>> list = mConfig.getMvurllist();
                   int i = list.size()-1;
                   Map map = list.get(i);
                   String cn = (String) map.get("cn");
                   String vkey = (String) map.get("vkey");
                   String url = (String) map.get("url");
                   String videoUrl = url +vkey +"/"+ cn + "?fname=" + cn;



                   DownMv(pathname,videoUrl);
                  // mvdown(videoUrl,pathname);

                   Toast.makeText(getActivity(), "正在下载", Toast.LENGTH_LONG).show();


                dialog.dismiss();
            }
        });

    }




    //获取mv list的方法
    public  void mv_List(){
        final String misuelist=URLinfo.musiclisturl1 + editText.getText().toString() + URLinfo.musiclisturl2;

        mOKHttp.mConfig(misuelist).getRequest(new mCallback() {
            @Override
            public void onSuccess(final String res) {//成功的okhttp回调
                getActivity().runOnUiThread(new Runnable() { //线程
                    @Override
                    public void run() { //主线程操作

                        String jsondta = res;


                        List <Map<String , Object>> list = new ArrayList<Map<String , Object>>();



                        if(jsondta!=null){
                            try {



                                JSONObject json = new JSONObject(jsondta);
                                JSONObject json_data = json.getJSONObject("data");
                                JSONObject json_song = json_data.getJSONObject("song");
                                JSONArray json_list = json_song.getJSONArray("list");//获取到了list （60条记录） ;[]集合json



                                for (int i = 0; i < json_list.length(); i++) {
                                    Map<String ,Object> map=new HashMap<String, Object>();


                                    Log.e("tty", String.valueOf(json_list.length()));
                                    JSONObject json_listobj = json_list.getJSONObject(i);  //把集合[]json 转换 数组 {}
                                    String json_title = json_listobj.getString("title"); //获取歌曲 title
                                    String json_mid = json_listobj.getString("mid"); //获取歌曲 mid

                                    JSONObject json_mv = json_listobj.getJSONObject("mv");
                                    String mvmid = json_mv.getString("vid");

                                    JSONObject json_img = json_listobj.getJSONObject("album");


                                    if(mvmid.length() > 0)
                                    {
                                        String img = json_img.getString("pmid");

                                        map.put("name",json_title);
                                        map.put("mid",json_mid);
                                        map.put("mvmid",mvmid);
                                        map.put("pmid_img",img);

                                        list.add(map);
                                    }


                                }
                                Log.e("map", list.toString());


                                MvAdapter adapter = new MvAdapter(getContext());
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
    public  void mtv_List2(String t){
        final String misuelist=URLinfo.musiclisturl1 + t + URLinfo.musiclisturl2;

        mOKHttp.mConfig(misuelist).getRequest(new mCallback() {
            @Override
            public void onSuccess(final String res) {//成功的okhttp回调
                getActivity().runOnUiThread(new Runnable() { //线程
                    @Override
                    public void run() { //主线程操作

                        String jsondta = res;


                        List <Map<String , Object>> list = new ArrayList<Map<String , Object>>();



                        if(jsondta!=null){
                            try {



                                JSONObject json = new JSONObject(jsondta);
                                JSONObject json_data = json.getJSONObject("data");
                                JSONObject json_song = json_data.getJSONObject("song");
                                JSONArray json_list = json_song.getJSONArray("list");//获取到了list （60条记录） ;[]集合json



                                for (int i = 0; i < json_list.length(); i++) {
                                    Map<String ,Object> map=new HashMap<String, Object>();

                                    JSONObject json_listobj = json_list.getJSONObject(i);  //把集合[]json 转换 数组 {}
                                    String json_title = json_listobj.getString("title"); //获取歌曲 title
                                    String json_mid = json_listobj.getString("mid"); //获取歌曲 mid

                                    JSONObject json_mv = json_listobj.getJSONObject("mv");
                                    String mvmid = json_mv.getString("vid");

                                    JSONObject json_img = json_listobj.getJSONObject("album");


                                    if(mvmid.length() > 0)
                                    {
                                        String img = json_img.getString("pmid");

                                        map.put("name",json_title);
                                        map.put("mid",json_mid);
                                        map.put("mvmid",mvmid);
                                        map.put("pmid_img",img);

                                        list.add(map);
                                    }


                                }
                                Log.e("map", list.toString());


                                MvAdapter adapter = new MvAdapter(getContext());
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


/*    public void DownMv(String filename,String downloadUrl,String FileType) {


        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(filename);
        request.setAllowedOverRoaming(false);
        request.setDescription(filename);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir("/XXXXXXX/",filename+FileType);
        DownloadManager downloadManager = (DownloadManager)getActivity().getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);


    }*/

    private void DownMv(String filename,String downloadUrl) {
        //创建request对象  下载任务
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(downloadUrl));
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

        String p ="/storage/emulated/0/XXXXXXX";


        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,filename+".mp4");

        Log.e("path",Environment.DIRECTORY_DOWNLOADS);


//------------------------------------------------------------------>>>>>>>
       // request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().toString(),filename+"mv.mp4");
        //获取系统服务
        DownloadManager downloadManager = (DownloadManager)getActivity().getSystemService(DOWNLOAD_SERVICE);
        //进行下载
        downloadManager.enqueue(request);
    }







}