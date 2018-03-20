package com.roje.rojemusic.bean;


import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;


public class Profile {
    @SerializedName("mutual")
    private boolean mutual;
    @SerializedName("remarkName")
    private String remarkName;
    @SerializedName("defaultAvatar")
    private boolean defaultAvatar;
    @SerializedName("avatarUrl")
    private String avatarUrl;
    @SerializedName("gender")
    private int gender;
    @SerializedName("birthday")
    private long birthday;
    @SerializedName("city")
    private int city;
    @SerializedName("province")
    private int province;
    @SerializedName("backgroundUrl")
    private String backgroundUrl;
    @SerializedName("djStatus")
    private int djStatus;
    @SerializedName("accountStatus")
    private int accountStatus;
    @SerializedName("experts")
    private JsonObject experts;
    @SerializedName("detailDescription")
    private String detailDescription;
    @SerializedName("followed")
    private boolean followed;
    @SerializedName("description")
    private String description;
    @SerializedName("signature")
    private String signature;
    @SerializedName("authority")
    private int authority;
    @SerializedName("avatarImgId_str")
    private String avatarImgId_str;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("authStatus")
    private int authStatus;
    @SerializedName("userId")
    private long userId;
    @SerializedName("expertTags")
    private String expertTags;
    @SerializedName("avatarImgId")
    private String avatarImgId;
    @SerializedName("backgroundImgId")
    private String backgroundImgId;
    @SerializedName("userType")
    private int userType;
    @SerializedName("vipType")
    private int vipType;

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public int getDjStatus() {
        return djStatus;
    }

    public void setDjStatus(int djStatus) {
        this.djStatus = djStatus;
    }

    public JsonObject getExperts() {
        return experts;
    }

    public void setExperts(JsonObject experts) {
        this.experts = experts;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getAvatarImgId_str() {
        return avatarImgId_str;
    }

    public void setAvatarImgId_str(String avatarImgId_str) {
        this.avatarImgId_str = avatarImgId_str;
    }

    public boolean isMutual() {
        return mutual;
    }

    public void setMutual(boolean mutual) {
        this.mutual = mutual;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public boolean isDefaultAvatar() {
        return defaultAvatar;
    }

    public void setDefaultAvatar(boolean defaultAvatar) {
        this.defaultAvatar = defaultAvatar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getExpertTags() {
        return expertTags;
    }

    public void setExpertTags(String expertTags) {
        this.expertTags = expertTags;
    }

    public String getAvatarImgId() {
        return avatarImgId;
    }

    public void setAvatarImgId(String avatarImgId) {
        this.avatarImgId = avatarImgId;
    }

    public String getBackgroundImgId() {
        return backgroundImgId;
    }

    public void setBackgroundImgId(String backgroundImgId) {
        this.backgroundImgId = backgroundImgId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getVipType() {
        return vipType;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }
}
