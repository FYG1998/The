package com.example.demo.model;

/**
 * UI显示相关
 */
public class ItemView {
    private ItemInfo item1;
    private ItemInfo item2;
    private ItemInfo item3;
    private ItemInfo item4;

    public ItemInfo getItem1() {
        return item1;
    }

    public void setItem1(ItemInfo item1) {
        this.item1 = item1;
    }

    public ItemInfo getItem2() {
        return item2;
    }

    public void setItem2(ItemInfo item2) {
        this.item2 = item2;
    }

    public ItemInfo getItem3() {
        return item3;
    }

    public void setItem3(ItemInfo item3) {
        this.item3 = item3;
    }

    public ItemInfo getItem4() {
        return item4;
    }

    public void setItem4(ItemInfo item4) {
        this.item4 = item4;
    }

    public ItemView(ItemInfo item1, ItemInfo item2, ItemInfo item3, ItemInfo item4) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
    }
}
