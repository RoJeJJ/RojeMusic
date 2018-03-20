
package com.roje.rojemusic.bean.playlist;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Creator {
    @SerializedName("defaultAvatar")
    private boolean defaultAvatar;
    @SerializedName("province")
    private long province;
    @SerializedName("authStatus")
    private int authStatus;
    @SerializedName("followed")
    private boolean followed;
    @SerializedName("avatarUrl")
    private String avatarUrl;
    @SerializedName("accountStatus")
    private int accountStatus;
    @SerializedName("gender")
    private int gender;
    @SerializedName("city")
    private long city;
    @SerializedName("birthday")
    private long birthday;
    @SerializedName("userId")
    private long userId;
    @SerializedName("userType")
    private int userType;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("signature")
    private String signature;
    @SerializedName("description")
    private String description;
    @SerializedName("detailDescription")
    private String detailDescription;
    @SerializedName("avatarImgId")
    private long avatarImgId;
    @SerializedName("backgroundImgId")
    private long backgroundImgId;
    @SerializedName("backgroundUrl")
    private String backgroundUrl;
    @SerializedName("authority")
    private int authority;
    @SerializedName("mutual")
    private boolean mutual;
    @SerializedName("expertTags")
    private List<String> expertTags;
    @SerializedName("experts")
    private Object experts;
    @SerializedName("djStatus")
    private int djStatus;
    @SerializedName("vipType")
    private int vipType;
    @SerializedName("remarkName")
    private String remarkName;
    @SerializedName("avatarImgIdStr")
    private String avatarImgIdStr;
    @SerializedName("backgroundImgIdStr")
    private String backgroundImgIdStr;
    @SerializedName("avatarImgId_str")
    private String avatarImgId_str;
    public void setDefaultAvatar(boolean defaultAvatar) {
         this.defaultAvatar = defaultAvatar;
     }
     public boolean getDefaultAvatar() {
         return defaultAvatar;
     }

    public void setProvince(long province) {
         this.province = province;
     }
     public long getProvince() {
         return province;
     }

    public void setAuthStatus(int authStatus) {
         this.authStatus = authStatus;
     }
     public int getAuthStatus() {
         return authStatus;
     }

    public void setFollowed(boolean followed) {
         this.followed = followed;
     }
     public boolean getFollowed() {
         return followed;
     }

    public void setAvatarUrl(String avatarUrl) {
         this.avatarUrl = avatarUrl;
     }
     public String getAvatarUrl() {
         return avatarUrl;
     }

    public void setAccountStatus(int accountStatus) {
         this.accountStatus = accountStatus;
     }
     public int getAccountStatus() {
         return accountStatus;
     }

    public void setGender(int gender) {
         this.gender = gender;
     }
     public int getGender() {
         return gender;
     }

    public void setCity(long city) {
         this.city = city;
     }
     public long getCity() {
         return city;
     }

    public void setBirthday(long birthday) {
         this.birthday = birthday;
     }
     public long getBirthday() {
         return birthday;
     }

    public void setUserId(long userId) {
         this.userId = userId;
     }
     public long getUserId() {
         return userId;
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

    public void setSignature(String signature) {
         this.signature = signature;
     }
     public String getSignature() {
         return signature;
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

    public void setBackgroundUrl(String backgroundUrl) {
         this.backgroundUrl = backgroundUrl;
     }
     public String getBackgroundUrl() {
         return backgroundUrl;
     }

    public void setAuthority(int authority) {
         this.authority = authority;
     }
     public int getAuthority() {
         return authority;
     }

    public void setMutual(boolean mutual) {
         this.mutual = mutual;
     }
     public boolean getMutual() {
         return mutual;
     }

    public void setExpertTags(List<String> expertTags) {
         this.expertTags = expertTags;
     }
     public List<String> getExpertTags() {
         return expertTags;
     }

    public void setExperts(Object experts) {
         this.experts = experts;
     }
     public Object getExperts() {
         return experts;
     }

    public void setDjStatus(int djStatus) {
         this.djStatus = djStatus;
     }
     public int getDjStatus() {
         return djStatus;
     }

    public void setVipType(int vipType) {
         this.vipType = vipType;
     }
     public int getVipType() {
         return vipType;
     }

    public void setRemarkName(String remarkName) {
         this.remarkName = remarkName;
     }
     public String getRemarkName() {
         return remarkName;
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

    public void setAvatarImgId_str(String avatarImgId_str) {
         this.avatarImgId_str = avatarImgId_str;
     }
     public String getAvatarImgId_str() {
         return avatarImgId_str;
     }

}