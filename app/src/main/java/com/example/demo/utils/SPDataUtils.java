package com.example.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.demo.tools.spInfo;


/**
 * 2021/04/11
 * SharedPreferences 轻量级存储，保存一些S etting info 与 user token
 */


public class SPDataUtils {

    private final static String mFileName = "mydata";

    /**
     *  保存 SharedPreferences info
     * @param context  上下文
     * @param uname
     * @param upass
     * @return
     */
    public static boolean saveUserInfo(Context context, String uname, String upass){
        boolean flag= false;
        SharedPreferences sp =context.getSharedPreferences(mFileName ,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("uname",uname);
        editor.putString("upass",upass);

        editor.commit();
        flag =  true;
        return  flag;
    }


    /**
     * 通过实体类获取信息
     * 获取SharedPreferences info
     * @param context 调用上下文
     * @return
     */
    public static spInfo getspInfo(Context context) {
        spInfo info = null;
        SharedPreferences sp = context.getSharedPreferences(mFileName,context.MODE_PRIVATE);
        String uname = sp.getString("uname",null);
        String upass = sp.getString("upass","1000");

        info = new spInfo();
        info.setUname(uname);
        info.setUpass(upass);

        return info;
    }
}
