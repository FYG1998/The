package com.example.demo.mfragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.demo.R;
import com.example.demo.adapter.RecyclerViewAdapter;
import com.example.demo.model.Entity;
import com.example.demo.model.ImgModel;
import com.example.demo.utils.GsonUtil;
import com.example.demo.utils.ProgressDialogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 可以通过LayoutManager实现各种不同的排列方式的布局
 */

//https://www.jianshu.com/p/b4bb52cdbeb7    RecyclerView

public class ImgFragment extends Fragment {

    private Activity activity;
    private RecyclerView recyclerView;
    private List<ImgModel> ImgList = new ArrayList<>();
    private int index;

    public ImgFragment() {

    }

    //Tab 页position
    public static Fragment newInstance(int position) {
        ImgFragment fragment = new ImgFragment();
        fragment.index = position;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_img, container, false);

        recyclerView = view.findViewById(R.id.fragment_recyclerview);
        activity=getActivity();
        //测试
        initFruits();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),ImgList);
        recyclerView.setAdapter(adapter);

        Log.e("调试", "imgFragment --imglist 200 4");
        //imglist("200","4");

        Testget();
        return  view;
    }




    private void Testget(){
        String a = "https://api.github.com/repos/square/retrofit/contributors";
        String b = "http://msearchcdn.kugou.com/new/app/i/search.php?cmd=302&keyword=%E5%91%A8%E6%9D%B0%E4%BC%A6";
        String c = "https://naiop.github.io/test/updateApp.json";
        //Config.SongName("赵雷")

        OkGo.<String>get("http://bz.eleuu.com/api.php?cid=36&start=30&count=30")     // 请求方式和请求url
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
                        String json = response.body().toString();
                        Entity lot = GsonUtil.fromJson(json, Entity.class);
                        String a =lot.data.get(0).getUrl();
                        Log.d("teaaaast",a);



                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }



                    @Override
                    public void onFinish() {
                        ProgressDialogUtil.cancelProgressDialog(activity);

                    }

                });

    }


    /**
     * 模拟的数据
     */
    private void initFruits() {

        ImgModel apple = new ImgModel ("http://browser9.qhimg.com//bdr//__85//t010824ab8b5cdfa138.jpg");
        ImgList.add(apple);
        ImgModel banana = new ImgModel("http://ww3.sinaimg.cn/large/610dc034jw1f6gcxc1t7vj20hs0hsgo1.jpg");
        ImgList.add(banana);
        ImgModel orange = new ImgModel("http://ww4.sinaimg.cn/large/610dc034jw1f6f5ktcyk0j20u011hacg.jpg");
        ImgList.add(orange);
        ImgModel watermelon = new ImgModel("http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg");
        ImgList.add(watermelon);
        ImgModel pear = new ImgModel("http://ww3.sinaimg.cn/large/610dc034jw1f6aipo68yvj20qo0qoaee.jpg");
        ImgList.add(pear);
        ImgModel grape = new ImgModel ("http://ww3.sinaimg.cn/large/610dc034jw1f69c9e22xjj20u011hjuu.jpg");
        ImgList.add(grape);
        ImgModel pineapple = new ImgModel ("http://ww3.sinaimg.cn/large/610dc034jw1f689lmaf7qj20u00u00v7.jpg");
        ImgList.add(pineapple);
        ImgModel strawberry = new ImgModel("http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg");
        ImgList.add(strawberry);



    }



}