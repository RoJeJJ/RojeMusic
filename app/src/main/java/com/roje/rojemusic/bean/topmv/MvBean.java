package com.roje.rojemusic.bean.topmv;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MvBean {
    @SerializedName("id")
    private long id;
    @SerializedName("cover")
    private String cover;
    @SerializedName("name")
    private String name;
    @SerializedName("playCount")
    private long playCount;
    @SerializedName("briefDesc")
    private String briefDesc;
    @SerializedName("desc")
    private String desc;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("artistId")
    private long artistId;
    @SerializedName("duration")
    private int duration;
    @SerializedName("mark")
    private int mark;
    @SerializedName("lastRank")
    private int lastRank;
    @SerializedName("score")
    private long score;
    @SerializedName("subed")
    private boolean subed;
    @SerializedName("artists")
    private List<Artist> artists;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setCover(String cover) {
         this.cover = cover;
     }
     public String getCover() {
         return cover;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setPlayCount(long playCount) {
         this.playCount = playCount;
     }
     public long getPlayCount() {
         return playCount;
     }

    public void setBriefDesc(String briefDesc) {
         this.briefDesc = briefDesc;
     }
     public String getBriefDesc() {
         return briefDesc;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setArtistName(String artistName) {
         this.artistName = artistName;
     }
     public String getArtistName() {
         return artistName;
     }

    public void setArtistId(long artistId) {
         this.artistId = artistId;
     }
     public long getArtistId() {
         return artistId;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setMark(int mark) {
         this.mark = mark;
     }
     public int getMark() {
         return mark;
     }

    public void setLastRank(int lastRank) {
         this.lastRank = lastRank;
     }
     public int getLastRank() {
         return lastRank;
     }

    public void setScore(long score) {
         this.score = score;
     }
     public long getScore() {
         return score;
     }

    public void setSubed(boolean subed) {
         this.subed = subed;
     }
     public boolean getSubed() {
         return subed;
     }

    public void setArtists(List<Artist> artists) {
         this.artists = artists;
     }
     public List<Artist> getArtists() {
         return artists;
     }

}