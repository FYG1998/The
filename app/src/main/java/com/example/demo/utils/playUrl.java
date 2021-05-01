package com.example.demo.utils;

import android.util.Log;

import com.example.demo.tools.mCallback;
import com.example.demo.model.mConfig;
import com.example.demo.tools.mOKHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拼接地址获取腾讯qq音乐 mv 地址
 */


public class playUrl {

    public static void play( final String id)
    {
        String a = "http://u.y.qq.com/cgi-bin/musicu.fcg?g_tk=5381&uin=2501256238&ct=23&cv=0&format=json&data={\"getMVInfo\":{\"module\":\"video.VideoDataServer\",\"method\":\"get_video_info_batch\",\"param\":{\"vidlist\":[\"";
        String b = "\"],\"required\":[\"vid\",\"sid\",\"gmid\",\"type\",\"name\",\"cover_pic\",\"video_switch\",\"msg\"],\"from\":\"h5.mvplay\"}},\"getMVUrl\":{\"module\":\"gosrf.Stream.MvUrlProxy\",\"method\":\"GetMvUrls\",\"param\":{\"vids\":[\"";
        String c = "\"],\"from\":\"h5.mvplay\"},\"request_typet\":10001}}";

         final String musiceurl = a + id + b + id + c;

        mOKHttp.mConfig(musiceurl).getRequest(new mCallback() {
            @Override
            public void onSuccess(String res) {
                List sites = new ArrayList();
                if(res != null)
                {
                  try {
                       JSONObject json = new JSONObject(res);
                      JSONObject getMVUrl = json.getJSONObject("getMVUrl");
                      JSONObject data = getMVUrl.getJSONObject("data");
                      JSONObject mvid = data.getJSONObject(id);

                      JSONArray json_list = mvid.getJSONArray("mp4");//获取到了list  ;[]集合json

                      for (int i = 0; i < json_list.length(); i++) {
                          Map<String ,Object> map=new HashMap<String, Object>();
                          Log.e("tty", String.valueOf(json_list.length()));
                          JSONObject json_listobj = json_list.getJSONObject(i);  //循环i
                          JSONArray freeflow_url =   json_listobj.getJSONArray("freeflow_url");//获取到了list  ;[]集合json

                          String cn = json_listobj.getString("cn"); //获取歌曲 title
                          String vkey = json_listobj.getString("vkey"); //获取歌曲 mid

                          if(cn.length() > 0)
                          {
                              map.put("url","http://223.111.252.17/mv.music.tc.qq.com/ADzzN8bY-jpncnh7YymlcSOmZ2yCF5mkAKF8F0TVZPdA/");
                              map.put("cn",cn);
                              map.put("vkey",vkey);

                              sites.add(map);
                          }
                      }
                      }

                    catch (JSONException e){ throw new RuntimeException(e);}
                }


                Log.e("tttt",sites.toString());
                mConfig.setMvurllist(sites);


            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }
}
