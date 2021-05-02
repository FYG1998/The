package com.example.demo.umodel;

import java.util.List;
import java.util.Map;

public class mConfig {


    public static List<Map<String, Object>> mvurllist;

    public static List<Map<String, Object>> getMvurllist() {
        return mvurllist;
    }

    public static void setMvurllist(List<Map<String, Object>> mvurllist) {
        mConfig.mvurllist = mvurllist;
    }


}
