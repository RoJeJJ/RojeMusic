package com.roje.rojemusic.bean.personfm;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonFMBean {
    @SerializedName("popAdjust")
    private boolean popAdjust;
    @SerializedName("data")
    private List<Song> data;
    @SerializedName("code")
    private int code;

    public void setPopAdjust(boolean popAdjust) {
        this.popAdjust = popAdjust;
    }

    public boolean getPopAdjust() {
        return popAdjust;
    }

    public void setData(List<Song> data) {
        this.data = data;
    }

    public List<Song> getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}