package com.roje.rojemusic.entities;


public class LayMusicItem {
    //图片资源
    private int resId;
    //标题
    private String title;
    //音乐数目
    private int num;
    //正在播放
    private boolean playing;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
