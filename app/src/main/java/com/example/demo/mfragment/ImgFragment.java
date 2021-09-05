package com.example.demo.mfragment;

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
import com.example.demo.model.ImgModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 可以通过LayoutManager实现各种不同的排列方式的布局
 */

//https://www.jianshu.com/p/b4bb52cdbeb7    RecyclerView

public class ImgFragment extends Fragment {

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
        //测试
        initFruits();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),ImgList);
        recyclerView.setAdapter(adapter);

        Log.e("调试", "imgFragment --imglist 200 4");
        //imglist("200","4");
        return  view;
    }




   /*  图片地址过期 String str = "https://gank.io/api/data/福利/" + count + "/" + "/" + page;
   public  void imglist(int count,int page){

        final String getimgurl= "http://gank.io/api/data/福利/" + count + "/" + "/" + page;

        mOKHttp.mConfig(getimgurl).getRequest(new mCallback() {
            @Override
            public void onSuccess(final String res) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String jsondta = res;

                        Log.e("调试", "imgFragment --获取img"+res);

                        if(jsondta!=null){
                            try {
                                JSONObject json = new JSONObject(jsondta);
                                JSONArray json_list = json.getJSONArray("results");

                                for (int i = 0; i < json_list.length(); i++) {

                                    Map<String ,Object> map=new HashMap<String, Object>();
                                    JSONObject json_listobj = json_list.getJSONObject(i);
                                    String json_img_url = json_listobj.getString("url");

                                    ImgList.add(new ImgModel(json_img_url) );
                                }

                                GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
                                recyclerView.setLayoutManager(layoutManager);
                                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),ImgList);
                                recyclerView.setAdapter(adapter);

                            }
                            catch (JSONException e) { e.printStackTrace(); }

                        }

                    }
                });
            }

            @Override
            public void onFailure(Exception e) {

                Log.e("调试", "imgFragment --okhttp 失败");

            }
        });

    }*/


    /**
     * 模拟的数据
     */
    private void initFruits() {

        ImgModel apple = new ImgModel ("http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg");
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
        ImgModel cherry = new ImgModel("http://ww2.sinaimg.cn/large/610dc034jw1f65f0oqodoj20qo0hntc9.jpg");
        ImgList.add(cherry);
        ImgModel mango = new ImgModel("http://ww2.sinaimg.cn/large/c85e4a5cgw1f62hzfvzwwj20hs0qogpo.jpg");
        ImgList.add(mango);


    }



}