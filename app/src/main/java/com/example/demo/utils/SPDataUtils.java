package com.example.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.demo.model.spInfo;

/**
 * 2021/04/11
 * SharedPreferences 轻量级存储，保存一些Setting info 与 user token
 */

public class SPDataUtils {

   private static boolean flag= false;

    /**
     *  save user password
     * @param context 上下文
     * @param mfilename sp 文件名
     * @param uname 用户名
     * @param upass 密码
     * @return boolean
     */
    public static boolean saveUserInfo(Context context,String mfilename,String uname, String upass){
        String mFileName = mfilename;
        SharedPreferences sp =context.getSharedPreferences(mFileName ,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("uname",uname);
        editor.putString("upass",upass);
        flag =  editor.commit();
        return  flag;
    }


    /**
     *  保存 Switch
     * @param context
     * @param b boolean 类型
     * @return boolean
     */

    public static boolean saveUserInfo(Context context,String mfilename, boolean b){
        String mFileName = mfilename;
        SharedPreferences sp =context.getSharedPreferences(mFileName ,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("switch",b);
        flag =  editor.commit();
        return  flag;
    }


    /**
     *  保存导播图time
     * @param context
     * @param mfilename
     * @param time
     * @return
     */
    public static boolean saveUserInfo(Context context, String mfilename, String time){
        String mFileName = mfilename;
        SharedPreferences sp =context.getSharedPreferences(mFileName ,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("daobotutime",time);
        flag =  editor.commit(); //返回的Boolean值，是否保存成功
        return  flag;
    }

    public static boolean saveHslClientMqtt(Context context ,String mfilename,String ipaddress ,String port,String topic,String send){

        SharedPreferences sp =context.getSharedPreferences(mfilename ,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ipaddress",ipaddress);
        editor.putString("port",port);
        editor.putString("topic",topic);
        editor.putString("sendtext",send);
        flag =  editor.commit(); //返回的Boolean值，是否保存成功
        return flag;
    }




    public static spInfo getspInfo(Context context) {
        spInfo info = null;
        SharedPreferences sp_user_password = context.getSharedPreferences("userinfo",context.MODE_PRIVATE);
        SharedPreferences sp_switch = context.getSharedPreferences("switch",context.MODE_PRIVATE);
        SharedPreferences sp_daobotutime = context.getSharedPreferences("mtime",context.MODE_PRIVATE);
        SharedPreferences sp_hslmqtt = context.getSharedPreferences("savehslmqtt",context.MODE_PRIVATE);


        String uname = sp_user_password.getString("uname","admin");
        String upass = sp_user_password.getString("upass","123456");
        Boolean b =sp_switch.getBoolean("switch",true);
        String time = sp_daobotutime.getString("daobotutime","2000");

        String ipaddress = sp_hslmqtt.getString("ipaddress",null);
        String port = sp_hslmqtt.getString("port",null);
        String topic = sp_hslmqtt.getString("topic",null);
        String sendtext = sp_hslmqtt.getString("sendtext",null);



        info = new spInfo();
        info.setUname(uname);
        info.setUpass(upass);
        info.setMboolean(b);
        info.setTime(time);

        info.setIpaddress(ipaddress);
        info.setPort(port);
        info.setTopic(topic);
        info.setSendtext(sendtext);



        return info;
    }


}
