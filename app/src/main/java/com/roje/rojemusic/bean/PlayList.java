package com.roje.rojemusic.bean;

import com.google.gson.annotations.SerializedName;
import com.roje.rojemusic.info.musiclist.BaseItem;

import java.util.List;

public class PlayList extends BaseItem {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private long id;
    @SerializedName("trackNumberUpdateTime")
    private long trackNumberUpdateTime;
    @SerializedName("status")
    private int status;
    @SerializedName("userId")
    private int userId;
    @SerializedName("createTime")
    private long createTime;
    @SerializedName("updateTime")
    private long updateTime;
    @SerializedName("subscribedCount")
    private int subscribedCount;
    @SerializedName("trackCount")
    private int trackCount;
    @SerializedName("cloudTrackCount")
    private int cloudTrackCount;
    @SerializedName("coverImgUrl")
    private String coverImgUrl;
    @SerializedName("coverImgId")
    private long coverImgId;
    @SerializedName("description")
    private String description;
    @SerializedName("tags")
    private String[] tags;
    @SerializedName("playCount")
    private int playCount;
    @SerializedName("trackUpdateTime")
    private long trackUpdateTime;
    @SerializedName("specialType")
    private int specialType;
    @SerializedName("totalDuration")
    private int totalDuration;
    @SerializedName("shareCount")
    private int shareCount;
    @SerializedName("commentCount")
    private int commentCount;
    @SerializedName("coverImgId_str")
    private String coverImgId_str;
    @SerializedName("subscribers")
    private List subscribers;


    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public long getTrackNumberUpdateTime() {
        return trackNumberUpdateTime;
    }

    public int getStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public int getSubscribedCount() {
        return subscribedCount;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public int getCloudTrackCount() {
        return cloudTrackCount;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public long getCoverImgId() {
        return coverImgId;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTags() {
        return tags;
    }

    public int getPlayCount() {
        return playCount;
    }

    public long getTrackUpdateTime() {
        return trackUpdateTime;
    }

    public int getSpecialType() {
        return specialType;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public int getShareCount() {
        return shareCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getCoverImgId_str() {
        return coverImgId_str;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
        this.trackNumberUpdateTime = trackNumberUpdateTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setSubscribedCount(int subscribedCount) {
        this.subscribedCount = subscribedCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public void setCloudTrackCount(int cloudTrackCount) {
        this.cloudTrackCount = cloudTrackCount;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public void setCoverImgId(long coverImgId) {
        this.coverImgId = coverImgId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public void setTrackUpdateTime(long trackUpdateTime) {
        this.trackUpdateTime = trackUpdateTime;
    }

    public void setSpecialType(int specialType) {
        this.specialType = specialType;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setCoverImgId_str(String coverImgId_str) {
        this.coverImgId_str = coverImgId_str;
    }

    public List getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List subscribers) {
        this.subscribers = subscribers;
    }
}
