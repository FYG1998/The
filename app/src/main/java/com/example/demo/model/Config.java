package com.example.demo.model;

import android.os.Environment;
import java.io.UnsupportedEncodingException;

public class Config {

    public static String mPath() {
        String mPath = Environment.getExternalStorageDirectory().getPath() + "/A_Test";
        return mPath;
    }

    public static String MinusUrl(String str) {
        //http://u6.y.qq.com/cgi-bin/musicu.fcg?data={"req":{"method":"DoSearchForQQMusicMobile","module":"music.search.SearchBrokerCgiServer","param":{"search_type":0,"query":"赵雷","page_num":1,"num_per_page":100,"grp":0}}}
        //Minus url 字符串拼接
        String minusUrl = String.format("http://u6.y.qq.com/cgi-bin/musicu.fcg?data={\"req\":{\"method\":\"DoSearchForQQMusicMobile\",\"module\":\"music.search.SearchBrokerCgiServer\",\"param\":{\"search_type\":0,\"query\":\"%s\",\"page_num\":1,\"num_per_page\":100,\"grp\":0}}}", str);
        return minusUrl;
    }

    public static String MinusUrl2(String str) {
        //http://c.y.qq.com/soso/fcgi-bin/client_search_cp?new_json=1&p=1&n=60&w=赵雷&format=json
        //Minus url 字符串拼接
        String minusUrl = String.format("http://c.y.qq.com/soso/fcgi-bin/client_search_cp?new_json=1&p=1&n=60&w=%s&format=json", str);
        return minusUrl;
    }

    public static String SongName(String str) {
        //http://msearchcdn.kugou.com/new/app/i/search.php?cmd=302&keyword=赵雷
        String Url= String.format("http://msearchcdn.kugou.com/new/app/i/search.php?cmd=302&keyword=%s",str);
        return Url;
    }

    public static String zplCode(String str) {
        String zpl="http://api.labelary.com/v1/printers/8dpmm/labels/4x6/0/";
        String Url= String.format("^XA\n" +
                "^FX BQ command parameters:\n" +
                "^FX - orientation (N/R/I/B)\n" +
                "^FX - model (number, always 2)\n" +
                "^FX - magnification (number)\n" +
                "^FX - error correction (character)\n" +
                "^FX - mask (number)\n" +
                "^FO50,50^BQN,2,4^FDQA,https://u.wechat.com/MPVhF35nfl3oMy-Z5nbGLEA ^FS\n" +
                "^FO25,25^GB 200,200,2^FS\n" +
                "^XZ");
        return Url;
    }

    public static byte[] mbyte(String str) {

        byte[] sInput = new byte[0];
        try {
            // 可以指定编码，默认也只UTF-8
            sInput = "Url".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sInput;
    }

    public static String MvUrl(String str) {

        //http://u.y.qq.com/cgi-bin/musicu.fcg?g_tk=5381&uin=2501256238&ct=23&cv=0&format=json&data={"getMVInfo":{"module":"video.VideoDataServer","method":"get_video_info_batch","param":{"vidlist":["m00244ktl0u"],"required":["vid","sid","gmid","type","name","cover_pic","video_switch","msg"],"from":"h5.mvplay"}},"getMVUrl":{"module":"gosrf.Stream.MvUrlProxy","method":"GetMvUrls","param":{"vids":["m00244ktl0u"],"from":"h5.mvplay"},"request_typet":10001}}
        String Url= String.format("http://u.y.qq.com/cgi-bin/musicu.fcg?g_tk=5381&uin=2501256238&ct=23&cv=0&format=json&data={\"getMVInfo\":{\"module\":\"video.VideoDataServer\",\"method\":\"get_video_info_batch\",\"param\":{\"vidlist\":[\"%s\"],\"required\":[\"vid\",\"sid\",\"gmid\",\"type\",\"name\",\"cover_pic\",\"video_switch\",\"msg\"],\"from\":\"h5.mvplay\"}},\"getMVUrl\":{\"module\":\"gosrf.Stream.MvUrlProxy\",\"method\":\"GetMvUrls\",\"param\":{\"vids\":[\"%s\"],\"from\":\"h5.mvplay\"},\"request_typet\":10001}}",str,str);
        return Url;
    }

    public static String aaa111aa(String str) {

        String Url= String.format("str %s",str);
        return Url;
    }


}
