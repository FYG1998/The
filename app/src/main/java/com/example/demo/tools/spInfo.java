package com.example.demo.tools;

/**
 *  SharedPrefenrences 实体类
 */

public class spInfo {
    private String uname;
    private String upass;


    public spInfo() {

    }

    public spInfo(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }



    //-------------------------------------------

    private Boolean mboolean;
    public Boolean getMboolean() {
        return mboolean;
    }

    public void setMboolean(Boolean mboolean) {
        this.mboolean = mboolean;
    }

    public spInfo(Boolean mboolean){
        this.mboolean=mboolean;
    }


}
