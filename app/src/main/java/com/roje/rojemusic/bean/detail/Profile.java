/**
  * Copyright 2018 bejson.com 
  */
package com.roje.rojemusic.bean.detail;

import com.google.gson.annotations.SerializedName;
import com.roje.rojemusic.bean.login.Experts;

public class Profile {
    @SerializedName("mutual")
    private boolean mutual;
    @SerializedName("accountStatus")
    private int accountStatus;
    @SerializedName("remarkName")
    private String remarkName;
    @SerializedName("experts")
    private Experts experts;
    @SerializedName("defaultAvatar")
    private boolean defaultAvatar;
    @SerializedName("avatarUrl")
    private String avatarUrl;
    @SerializedName("gender")
    private int gender;
    @SerializedName("birthday")
    private long birthday;
    @SerializedName("city")
    private long city;
    @SerializedName("province")
    private long province;
    @SerializedName("expertTags")
    private String expertTags;
    @SerializedName("authStatus")
    private int authStatus;
    @SerializedName("vipType")
    private int vipType;
    @SerializedName("userId")
    private long userId;
    @SerializedName("avatarImgId")
    private long avatarImgId;
    @SerializedName("backgroundImgId")
    private long backgroundImgId;
    @SerializedName("userType")
    private int userType;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("backgroundUrl")
    private String backgroundUrl;
    @SerializedName("description")
    private String description;
    @SerializedName("detailDescription")
    private String detailDescription;
    @SerializedName("djStatus")
    private int djStatus;
    @SerializedName("followed")
    private boolean followed;
    @SerializedName("avatarImgIdStr")
    private String avatarImgIdStr;
    @SerializedName("backgroundImgIdStr")
    private String backgroundImgIdStr;
    @SerializedName("signature")
    private String signature;
    @SerializedName("authority")
    private int authority;
    @SerializedName("avatarImgId_str")
    private String avatarImgId_str;
    @SerializedName("followeds")
    private int followeds;
    @SerializedName("follows")
    private int follows;
    @SerializedName("blacklist")
    private boolean blacklist;
    @SerializedName("eventCount")
    private int eventCount;
    @SerializedName("playlistCount")
    private int playlistCount;
    @SerializedName("playlistBeSubscribedCount")
    private int playlistBeSubscribedCount;
    public void setMutual(boolean mutual) {
         this.mutual = mutual;
     }
     public boolean getMutual() {
         return mutual;
     }

    public void setAccountStatus(int accountStatus) {
         this.accountStatus = accountStatus;
     }
     public int getAccountStatus() {
         return accountStatus;
     }

    public void setRemarkName(String remarkName) {
         this.remarkName = remarkName;
     }
     public String getRemarkName() {
         return remarkName;
     }

    public void setExperts(Experts experts) {
         this.experts = experts;
     }
     public Experts getExperts() {
         return experts;
     }

    public void setDefaultAvatar(boolean defaultAvatar) {
         this.defaultAvatar = defaultAvatar;
     }
     public boolean getDefaultAvatar() {
         return defaultAvatar;
     }

    public void setAvatarUrl(String avatarUrl) {
         this.avatarUrl = avatarUrl;
     }
     public String getAvatarUrl() {
         return avatarUrl;
     }

    public void setGender(int gender) {
         this.gender = gender;
     }
     public int getGender() {
         return gender;
     }

    public void setBirthday(long birthday) {
         this.birthday = birthday;
     }
     public long getBirthday() {
         return birthday;
     }

    public void setCity(long city) {
         this.city = city;
     }
     public long getCity() {
         return city;
     }

    public void setProvince(long province) {
         this.province = province;
     }
     public long getProvince() {
         return province;
     }

    public void setExpertTags(String expertTags) {
         this.expertTags = expertTags;
     }
     public String getExpertTags() {
         return expertTags;
     }

    public void setAuthStatus(int authStatus) {
         this.authStatus = authStatus;
     }
     public int getAuthStatus() {
         return authStatus;
     }

    public void setVipType(int vipType) {
         this.vipType = vipType;
     }
     public int getVipType() {
         return vipType;
     }

    public void setUserId(long userId) {
         this.userId = userId;
     }
     public long getUserId() {
         return userId;
     }

    public void setAvatarImgId(long avatarImgId) {
         this.avatarImgId = avatarImgId;
     }
     public long getAvatarImgId() {
         return avatarImgId;
     }

    public void setBackgroundImgId(long backgroundImgId) {
         this.backgroundImgId = backgroundImgId;
     }
     public long getBackgroundImgId() {
         return backgroundImgId;
     }

    public void setUserType(int userType) {
         this.userType = userType;
     }
     public int getUserType() {
         return userType;
     }

    public void setNickname(String nickname) {
         this.nickname = nickname;
     }
     public String getNickname() {
         return nickname;
     }

    public void setBackgroundUrl(String backgroundUrl) {
         this.backgroundUrl = backgroundUrl;
     }
     public String getBackgroundUrl() {
         return backgroundUrl;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setDetailDescription(String detailDescription) {
         this.detailDescription = detailDescription;
     }
     public String getDetailDescription() {
         return detailDescription;
     }

    public void setDjStatus(int djStatus) {
         this.djStatus = djStatus;
     }
     public int getDjStatus() {
         return djStatus;
     }

    public void setFollowed(boolean followed) {
         this.followed = followed;
     }
     public boolean getFollowed() {
         return followed;
     }

    public void setAvatarImgIdStr(String avatarImgIdStr) {
         this.avatarImgIdStr = avatarImgIdStr;
     }
     public String getAvatarImgIdStr() {
         return avatarImgIdStr;
     }

    public void setBackgroundImgIdStr(String backgroundImgIdStr) {
         this.backgroundImgIdStr = backgroundImgIdStr;
     }
     public String getBackgroundImgIdStr() {
         return backgroundImgIdStr;
     }

    public void setSignature(String signature) {
         this.signature = signature;
     }
     public String getSignature() {
         return signature;
     }

    public void setAuthority(int authority) {
         this.authority = authority;
     }
     public int getAuthority() {
         return authority;
     }

    public void setAvatarImgId_str(String avatarImgId_str) {
         this.avatarImgId_str = avatarImgId_str;
     }
     public String getAvatarImgId_str() {
         return avatarImgId_str;
     }

    public void setFolloweds(int followeds) {
         this.followeds = followeds;
     }
     public int getFolloweds() {
         return followeds;
     }

    public void setFollows(int follows) {
         this.follows = follows;
     }
     public int getFollows() {
         return follows;
     }

    public void setBlacklist(boolean blacklist) {
         this.blacklist = blacklist;
     }
     public boolean getBlacklist() {
         return blacklist;
     }

    public void setEventCount(int eventCount) {
         this.eventCount = eventCount;
     }
     public int getEventCount() {
         return eventCount;
     }

    public void setPlaylistCount(int playlistCount) {
         this.playlistCount = playlistCount;
     }
     public int getPlaylistCount() {
         return playlistCount;
     }

    public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
         this.playlistBeSubscribedCount = playlistBeSubscribedCount;
     }
     public int getPlaylistBeSubscribedCount() {
         return playlistBeSubscribedCount;
     }

}