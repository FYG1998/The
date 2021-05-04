package com.example.demo.umodel;

public class URLinfo {
    // 公告地址
    public  static  final String NoticeUrl="http://note.youdao.com/yws/public/note/723e22f9069aa34fe0a1f0b552d7f172";

    //public  static String musiclisturl="http://msearchcdn.kugou.com/new/app/i/search.php?cmd=302&keyword=";

    // qq音乐搜索地址
    public  static String musiclisturl1="http://c.y.qq.com/soso/fcgi-bin/client_search_cp?new_json=1&p=1&n=60&w=";
    public  static String musiclisturl2="&format=json";

    // 封面导播图
    public static  String getimgUrl="http://note.youdao.com/yws/public/note/7cd42328eaf98e6c9412036056d8760d";

    //region  导播图网址
    public static String imgurl="http://note.youdao.com/yws/public/resource/7cd42328eaf98e6c9412036056d8760d/xmlnote/f7cd446579019edbad70e10bca133a46/189";

    public static String getImgurl() {
        return imgurl;
    }
    public static void setImgurl(String imgurl) {
        URLinfo.imgurl = imgurl;
    }
    //endregion



    public  static String mTextnotice=" My Text Notice";//公告语

    //region Notice 获取
    public static String getmTextnotice() {
        return mTextnotice;
    }
    public static void setmTextnotice(String mTextnotice) {
        URLinfo.mTextnotice = mTextnotice;
    }
    //endregion




}
