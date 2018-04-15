package com.roje.rojemusic.info.musiclist;



public class MainItem extends BaseItem {
    private String name; //标题
    private int icn;//图标
    private int count;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getIcn() {
        return icn;
    }

    public void setIcn(int icn) {
        this.icn = icn;
    }

    public MainItem(String name, int count, int icn) {
        this.name = name;
        this.icn = icn;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
