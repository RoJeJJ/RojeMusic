package com.roje.rojemusic.bean;


import com.google.gson.annotations.SerializedName;

public class Binding {
    @SerializedName("url")
    private String url;
    @SerializedName("tokenJsonStr")
    private String tokenJsonStr;
    @SerializedName("userId")
    private long userId;
    @SerializedName("expiresIn")
    private long expiresIn;
    @SerializedName("refreshTime")
    private long refreshTime;
    @SerializedName("expired")
    private boolean expired;
    @SerializedName("id")
    private long id;
    @SerializedName("type")
    private int type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTokenJsonStr() {
        return tokenJsonStr;
    }

    public void setTokenJsonStr(String tokenJsonStr) {
        this.tokenJsonStr = tokenJsonStr;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
