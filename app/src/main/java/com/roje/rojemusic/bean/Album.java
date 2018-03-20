package com.roje.rojemusic.bean;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
    private Artists artist;
    @SerializedName("songs")
    private String[] songs;
    @SerializedName("alias")
    private String[] alias;
    @SerializedName("status")
    private int status;
    @SerializedName("copyrightId")
    private int copyrightId;
    @SerializedName("commentThreadId")
    private String commentThreadId;
    @SerializedName("artists")
    private ArrayList<Artists> artists;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public long getPicId() {
        return picId;
    }

    public String getBlurPicUrl() {
        return blurPicUrl;
    }

    public int getCompanyId() {
        return companyId;
    }

    public long getPic() {
        return pic;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public String getCompany() {
        return company;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public Artists getArtist() {
        return artist;
    }

    public String[] getSongs() {
        return songs;
    }

    public String[] getAlias() {
        return alias;
    }

    public int getStatus() {
        return status;
    }

    public int getCopyrightId() {
        return copyrightId;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public ArrayList<Artists> getArtists() {
        return artists;
    }
}
