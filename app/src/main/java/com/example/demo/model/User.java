package com.example.demo.model;

/**
 * Created by gpk on 2019/7/17.
 */
public class User {
    //用户账号
    private String code;
    //用户名称
    private String name;
    //用户密码
    private String password;

    public User(String code, String name) {
        this.code = code;
        this.name = name;
        this.password="";
    }

    public User(String code, String name, String password) {
        this.code = code;
        this.name = name;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        if(name.isEmpty()){
            return code;
        }
        else
        {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
