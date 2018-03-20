package com.roje.rojemusic.bean.personfm;

import com.google.gson.annotations.SerializedName;

public class HMusic {
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
    private long bitrate;
    @SerializedName("playTime")
    private long playTime;
    @SerializedName("volumeDelta")
    private double volumeDelta;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setSize(long size) {
         this.size = size;
     }
     public long getSize() {
         return size;
     }

    public void setExtension(String extension) {
         this.extension = extension;
     }
     public String getExtension() {
         return extension;
     }

    public void setSr(int sr) {
         this.sr = sr;
     }
     public int getSr() {
         return sr;
     }

    public void setDfsId(int dfsId) {
         this.dfsId = dfsId;
     }
     public int getDfsId() {
         return dfsId;
     }

    public void setBitrate(long bitrate) {
         this.bitrate = bitrate;
     }
     public long getBitrate() {
         return bitrate;
     }

    public void setPlayTime(long playTime) {
         this.playTime = playTime;
     }
     public long getPlayTime() {
         return playTime;
     }

    public void setVolumeDelta(double volumeDelta) {
         this.volumeDelta = volumeDelta;
     }
     public double getVolumeDelta() {
         return volumeDelta;
     }

}