package com.roje.rojemusic.bean;


import com.google.gson.annotations.SerializedName;

public class Artists {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private long id;
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
    private String[] alias;
    @SerializedName("trans")
    private String trans;
    @SerializedName("musicSize")
    private int musicSize;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getPicId() {
        return picId;
    }

    public int getImg1v1Id() {
        return img1v1Id;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getImg1v1Url() {
        return img1v1Url;
    }

    public int getAlbumSize() {
        return albumSize;
    }

    public String[] getAlias() {
        return alias;
    }

    public String getTrans() {
        return trans;
    }

    public int getMusicSize() {
        return musicSize;
    }
}
