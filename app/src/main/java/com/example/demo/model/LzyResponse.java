package com.example.demo.model;

import java.io.Serializable;

/**
 * OkGo相关
 * Created by gpk on 2019/6/19.
 */
public class LzyResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public int code;
    public String msg;
    public T result;

    @Override
    public String toString() {
        return "LzyResponse{\n" +//
                "\tcode=" + code + "\n" +//
                "\tmsg='" + msg + "\'\n" +//
                "\tresult=" + result + "\n" +//
                '}';
    }
}
