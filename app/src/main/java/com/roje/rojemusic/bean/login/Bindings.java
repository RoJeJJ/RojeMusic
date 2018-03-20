
package com.roje.rojemusic.bean.login;


import com.google.gson.annotations.SerializedName;

public class Bindings {
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
    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setTokenJsonStr(String tokenJsonStr) {
         this.tokenJsonStr = tokenJsonStr;
     }
     public String getTokenJsonStr() {
         return tokenJsonStr;
     }

    public void setUserId(long userId) {
         this.userId = userId;
     }
     public long getUserId() {
         return userId;
     }

    public void setExpiresIn(long expiresIn) {
         this.expiresIn = expiresIn;
     }
     public long getExpiresIn() {
         return expiresIn;
     }

    public void setRefreshTime(long refreshTime) {
         this.refreshTime = refreshTime;
     }
     public long getRefreshTime() {
         return refreshTime;
     }

    public void setExpired(boolean expired) {
         this.expired = expired;
     }
     public boolean getExpired() {
         return expired;
     }

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

}