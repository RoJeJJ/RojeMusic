package com.roje.rojemusic.entities;


public class MusicPageItem {
    /**
     * 0 常规 1 歌单 2 推荐
     */
    private int type;
    private Object item;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
