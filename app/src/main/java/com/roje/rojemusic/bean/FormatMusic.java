package com.roje.rojemusic.bean;


import com.google.gson.annotations.SerializedName;

public class FormatMusic {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private long id;
    @SerializedName("size")
    private long size;
    @SerializedName("extension")
    private String extension;
    @SerializedName("sr")
    private int sr;
    @SerializedName("dfsId")
    private int dfsId;
    @SerializedName("bitrate")
    private int bitrate;
    @SerializedName("playTime")
    private int playTime;
    @SerializedName("volumeDelta")
    private double volumeDelta;
    @SerializedName("dfsId_str")
    private String dfsId_str;
}
