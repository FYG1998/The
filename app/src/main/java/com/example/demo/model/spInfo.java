package com.example.demo.model;

/**
 *  SharedPrefenrences 实体类
 */

public class spInfo {



    public spInfo() {

    }


    public spInfo(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }
    private String uname;
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    private String upass;
    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }



    //-----------------导播图time
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    //------------------switch 开关


    private Boolean mboolean;


    public Boolean getMboolean() {
        return mboolean;
    }

    public void setMboolean(Boolean mboolean) {
        this.mboolean = mboolean;
    }





}
