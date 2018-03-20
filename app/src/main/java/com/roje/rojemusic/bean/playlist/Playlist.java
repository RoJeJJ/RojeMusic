
package com.roje.rojemusic.bean.playlist;
import com.google.gson.annotations.SerializedName;
import com.roje.rojemusic.info.musiclist.BaseItem;

import java.util.List;

public class Playlist extends BaseItem{
    @SerializedName("subscribers")
    private List<String> subscribers;
    @SerializedName("subscribed")
    private boolean subscribed;
    @SerializedName("creator")
    private Creator creator;
    @SerializedName("artists")
    private String artists;
    @SerializedName("tracks")
    private String tracks;
    @SerializedName("trackCount")
    private int trackCount;
    @SerializedName("ordered")
    private boolean ordered;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("updateTime")
    private long updateTime;
    @SerializedName("commentThreadId")
    private String commentThreadId;
    @SerializedName("privacy")
    private int privacy;
    @SerializedName("newImported")
    private boolean newImported;
    @SerializedName("specialType")
    private int specialType;
    @SerializedName("anonimous")
    private boolean anonimous;
    @SerializedName("highQuality")
    private boolean highQuality;
    @SerializedName("adType")
    private int adType;
    @SerializedName("trackNumberUpdateTime")
    private long trackNumberUpdateTime;
    @SerializedName("trackUpdateTime")
    private long trackUpdateTime;
    @SerializedName("playCount")
    private int playCount;
    @SerializedName("coverImgUrl")
    private String coverImgUrl;
    @SerializedName("totalDuration")
    private int totalDuration;
    @SerializedName("cloudTrackCount")
    private int cloudTrackCount;
    @SerializedName("coverImgId")
    private long coverImgId;
    @SerializedName("createTime")
    private long createTime;
    @SerializedName("subscribedCount")
    private int subscribedCount;
    @SerializedName("userId")
    private long userId;
    @SerializedName("description")
    private String description;
    @SerializedName("status")
    private int status;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private long id;
    public void setSubscribers(List<String> subscribers) {
         this.subscribers = subscribers;
     }
     public List<String> getSubscribers() {
         return subscribers;
     }

    public void setSubscribed(boolean subscribed) {
         this.subscribed = subscribed;
     }
     public boolean getSubscribed() {
         return subscribed;
     }

    public void setCreator(Creator creator) {
         this.creator = creator;
     }
     public Creator getCreator() {
         return creator;
     }

    public void setArtists(String artists) {
         this.artists = artists;
     }
     public String getArtists() {
         return artists;
     }

    public void setTracks(String tracks) {
         this.tracks = tracks;
     }
     public String getTracks() {
         return tracks;
     }

    public void setTrackCount(int trackCount) {
         this.trackCount = trackCount;
     }
     public int getTrackCount() {
         return trackCount;
     }

    public void setOrdered(boolean ordered) {
         this.ordered = ordered;
     }
     public boolean getOrdered() {
         return ordered;
     }

    public void setTags(List<String> tags) {
         this.tags = tags;
     }
     public List<String> getTags() {
         return tags;
     }

    public void setUpdateTime(long updateTime) {
         this.updateTime = updateTime;
     }
     public long getUpdateTime() {
         return updateTime;
     }

    public void setCommentThreadId(String commentThreadId) {
         this.commentThreadId = commentThreadId;
     }
     public String getCommentThreadId() {
         return commentThreadId;
     }

    public void setPrivacy(int privacy) {
         this.privacy = privacy;
     }
     public int getPrivacy() {
         return privacy;
     }

    public void setNewImported(boolean newImported) {
         this.newImported = newImported;
     }
     public boolean getNewImported() {
         return newImported;
     }

    public void setSpecialType(int specialType) {
         this.specialType = specialType;
     }
     public int getSpecialType() {
         return specialType;
     }

    public void setAnonimous(boolean anonimous) {
         this.anonimous = anonimous;
     }
     public boolean getAnonimous() {
         return anonimous;
     }

    public void setHighQuality(boolean highQuality) {
         this.highQuality = highQuality;
     }
     public boolean getHighQuality() {
         return highQuality;
     }

    public void setAdType(int adType) {
         this.adType = adType;
     }
     public int getAdType() {
         return adType;
     }

    public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
         this.trackNumberUpdateTime = trackNumberUpdateTime;
     }
     public long getTrackNumberUpdateTime() {
         return trackNumberUpdateTime;
     }

    public void setTrackUpdateTime(long trackUpdateTime) {
         this.trackUpdateTime = trackUpdateTime;
     }
     public long getTrackUpdateTime() {
         return trackUpdateTime;
     }

    public void setPlayCount(int playCount) {
         this.playCount = playCount;
     }
     public int getPlayCount() {
         return playCount;
     }

    public void setCoverImgUrl(String coverImgUrl) {
         this.coverImgUrl = coverImgUrl;
     }
     public String getCoverImgUrl() {
         return coverImgUrl;
     }

    public void setTotalDuration(int totalDuration) {
         this.totalDuration = totalDuration;
     }
     public int getTotalDuration() {
         return totalDuration;
     }

    public void setCloudTrackCount(int cloudTrackCount) {
         this.cloudTrackCount = cloudTrackCount;
     }
     public int getCloudTrackCount() {
         return cloudTrackCount;
     }

    public void setCoverImgId(long coverImgId) {
         this.coverImgId = coverImgId;
     }
     public long getCoverImgId() {
         return coverImgId;
     }

    public void setCreateTime(long createTime) {
         this.createTime = createTime;
     }
     public long getCreateTime() {
         return createTime;
     }

    public void setSubscribedCount(int subscribedCount) {
         this.subscribedCount = subscribedCount;
     }
     public int getSubscribedCount() {
         return subscribedCount;
     }

    public void setUserId(long userId) {
         this.userId = userId;
     }
     public long getUserId() {
         return userId;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

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

}