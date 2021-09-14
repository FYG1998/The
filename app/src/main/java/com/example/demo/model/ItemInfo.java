package com.example.demo.model;


import com.example.demo.R;

/**
 * UI显示的元素
 */
public class ItemInfo {
    private String info;
    private int corlor;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCorlor() {
        return corlor;
    }

    public void setCorlor(int corlor) {
        this.corlor = corlor;
    }

    public ItemInfo(String info) {
        this.info = info;
        this.corlor= R.color.black;
    }

    public ItemInfo(String info, int corlor) {
        this.info = info;
        this.corlor = corlor;
    }
}
