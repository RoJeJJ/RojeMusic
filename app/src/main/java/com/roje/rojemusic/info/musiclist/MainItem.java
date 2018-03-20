package com.roje.rojemusic.info.musiclist;



public class MainItem extends BaseItem {
    private String name; //标题
    private int count;//计数
    private int icn;//图标
    private int position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIcn() {
        return icn;
    }

    public void setIcn(int icn) {
        this.icn = icn;
    }

    public MainItem(String name, int count, int icn) {
        this.name = name;
        this.count = count;
        this.icn = icn;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
