package com.roje.rojemusic.bean.recommand;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("id")
    private long id;
    @SerializedName("type")
    private int type;
    @SerializedName("name")
    private String name;
    @SerializedName("copywriter")
    private String copywriter;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("canDislike")
    private boolean canDislike;
    @SerializedName("playCount")
    private double playCount;
    @SerializedName("trackCount")
    private int trackCount;
    @SerializedName("highQuality")
    private boolean highQuality;
    @SerializedName("alg")
    private String alg;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setCopywriter(String copywriter) {
         this.copywriter = copywriter;
     }
     public String getCopywriter() {
         return copywriter;
     }

    public void setPicUrl(String picUrl) {
         this.picUrl = picUrl;
     }
     public String getPicUrl() {
         return picUrl;
     }

    public void setCanDislike(boolean canDislike) {
         this.canDislike = canDislike;
     }
     public boolean getCanDislike() {
         return canDislike;
     }

    public void setPlayCount(double playCount) {
         this.playCount = playCount;
     }
     public double getPlayCount() {
         return playCount;
     }

    public void setTrackCount(int trackCount) {
         this.trackCount = trackCount;
     }
     public int getTrackCount() {
         return trackCount;
     }

    public void setHighQuality(boolean highQuality) {
         this.highQuality = highQuality;
     }
     public boolean getHighQuality() {
         return highQuality;
     }

    public void setAlg(String alg) {
         this.alg = alg;
     }
     public String getAlg() {
         return alg;
     }

}