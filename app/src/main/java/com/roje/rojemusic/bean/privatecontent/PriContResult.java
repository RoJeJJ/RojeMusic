package com.roje.rojemusic.bean.privatecontent;


import com.google.gson.annotations.SerializedName;

public class PriContResult {
    @SerializedName("id")
    private long id;
    @SerializedName("url")
    private String url;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("sPicUrl")
    private String sPicUrl;
    @SerializedName("type")
    private int type;
    @SerializedName("copywriter")
    private String copywriter;
    @SerializedName("name")
    private String name;
    @SerializedName("eventUserId")
    private long eventUserId;
    @SerializedName("width")
    private int width;
    @SerializedName("videoId")
    private String videoId;
    @SerializedName("eventType")
    private int eventType;
    @SerializedName("alg")
    private String alg;
    @SerializedName("height")
    private int height;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setPicUrl(String picUrl) {
         this.picUrl = picUrl;
     }
     public String getPicUrl() {
         return picUrl;
     }

    public void setSPicUrl(String sPicUrl) {
         this.sPicUrl = sPicUrl;
     }
     public String getSPicUrl() {
         return sPicUrl;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setCopywriter(String copywriter) {
         this.copywriter = copywriter;
     }
     public String getCopywriter() {
         return copywriter;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setEventUserId(long eventUserId) {
         this.eventUserId = eventUserId;
     }
     public long getEventUserId() {
         return eventUserId;
     }

    public void setWidth(int width) {
         this.width = width;
     }
     public int getWidth() {
         return width;
     }

    public void setVideoId(String videoId) {
         this.videoId = videoId;
     }
     public String getVideoId() {
         return videoId;
     }

    public void setEventType(int eventType) {
         this.eventType = eventType;
     }
     public int getEventType() {
         return eventType;
     }

    public void setAlg(String alg) {
         this.alg = alg;
     }
     public String getAlg() {
         return alg;
     }

    public void setHeight(int height) {
         this.height = height;
     }
     public int getHeight() {
         return height;
     }

}