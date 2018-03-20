package com.roje.rojemusic.bean.personfm;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("picId")
    private int picId;
    @SerializedName("img1v1Id")
    private int img1v1Id;
    @SerializedName("briefDesc")
    private String briefDesc;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("img1v1Url")
    private String img1v1Url;
    @SerializedName("albumSize")
    private int albumSize;
    @SerializedName("alias")
    private List<String> alias;
    @SerializedName("trans")
    private String trans;
    @SerializedName("musicSize")
    private int musicSize;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setPicId(int picId) {
         this.picId = picId;
     }
     public int getPicId() {
         return picId;
     }

    public void setImg1v1Id(int img1v1Id) {
         this.img1v1Id = img1v1Id;
     }
     public int getImg1v1Id() {
         return img1v1Id;
     }

    public void setBriefDesc(String briefDesc) {
         this.briefDesc = briefDesc;
     }
     public String getBriefDesc() {
         return briefDesc;
     }

    public void setPicUrl(String picUrl) {
         this.picUrl = picUrl;
     }
     public String getPicUrl() {
         return picUrl;
     }

    public void setImg1v1Url(String img1v1Url) {
         this.img1v1Url = img1v1Url;
     }
     public String getImg1v1Url() {
         return img1v1Url;
     }

    public void setAlbumSize(int albumSize) {
         this.albumSize = albumSize;
     }
     public int getAlbumSize() {
         return albumSize;
     }

    public void setAlias(List<String> alias) {
         this.alias = alias;
     }
     public List<String> getAlias() {
         return alias;
     }

    public void setTrans(String trans) {
         this.trans = trans;
     }
     public String getTrans() {
         return trans;
     }

    public void setMusicSize(int musicSize) {
         this.musicSize = musicSize;
     }
     public int getMusicSize() {
         return musicSize;
     }

}