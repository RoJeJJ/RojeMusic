package com.roje.rojemusic.bean.personfm;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private long id;
    @SerializedName("type")
    private String type;
    @SerializedName("size")
    private int size;
    @SerializedName("picId")
    private long picId;
    @SerializedName("blurPicUrl")
    private String blurPicUrl;
    @SerializedName("companyId")
    private int companyId;
    @SerializedName("pic")
    private long pic;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("publishTime")
    private long publishTime;
    @SerializedName("description")
    private String description;
    @SerializedName("tags")
    private String tags;
    @SerializedName("company")
    private String company;
    @SerializedName("briefDesc")
    private String briefDesc;
    @SerializedName("artist")
    private Artist artist;
    @SerializedName("songs")
    private List<String> songs;
    @SerializedName("alias")
    private List<String> alias;
    @SerializedName("status")
    private int status;
    @SerializedName("copyrightId")
    private int copyrightId;
    @SerializedName("commentThreadId")
    private String commentThreadId;
    @SerializedName("artists")
    private List<Artist> artists;
    @SerializedName("subType")
    private String subType;
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

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setSize(int size) {
         this.size = size;
     }
     public int getSize() {
         return size;
     }

    public void setPicId(long picId) {
         this.picId = picId;
     }
     public long getPicId() {
         return picId;
     }

    public void setBlurPicUrl(String blurPicUrl) {
         this.blurPicUrl = blurPicUrl;
     }
     public String getBlurPicUrl() {
         return blurPicUrl;
     }

    public void setCompanyId(int companyId) {
         this.companyId = companyId;
     }
     public int getCompanyId() {
         return companyId;
     }

    public void setPic(long pic) {
         this.pic = pic;
     }
     public long getPic() {
         return pic;
     }

    public void setPicUrl(String picUrl) {
         this.picUrl = picUrl;
     }
     public String getPicUrl() {
         return picUrl;
     }

    public void setPublishTime(long publishTime) {
         this.publishTime = publishTime;
     }
     public long getPublishTime() {
         return publishTime;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setTags(String tags) {
         this.tags = tags;
     }
     public String getTags() {
         return tags;
     }

    public void setCompany(String company) {
         this.company = company;
     }
     public String getCompany() {
         return company;
     }

    public void setBriefDesc(String briefDesc) {
         this.briefDesc = briefDesc;
     }
     public String getBriefDesc() {
         return briefDesc;
     }

    public void setArtist(Artist artist) {
         this.artist = artist;
     }
     public Artist getArtist() {
         return artist;
     }

    public void setSongs(List<String> songs) {
         this.songs = songs;
     }
     public List<String> getSongs() {
         return songs;
     }

    public void setAlias(List<String> alias) {
         this.alias = alias;
     }
     public List<String> getAlias() {
         return alias;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setCopyrightId(int copyrightId) {
         this.copyrightId = copyrightId;
     }
     public int getCopyrightId() {
         return copyrightId;
     }

    public void setCommentThreadId(String commentThreadId) {
         this.commentThreadId = commentThreadId;
     }
     public String getCommentThreadId() {
         return commentThreadId;
     }

    public void setArtists(List<Artist> artists) {
         this.artists = artists;
     }
     public List<Artist> getArtists() {
         return artists;
     }

    public void setSubType(String subType) {
         this.subType = subType;
     }
     public String getSubType() {
         return subType;
     }

}