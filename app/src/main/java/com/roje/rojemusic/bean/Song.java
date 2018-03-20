package com.roje.rojemusic.bean;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Song {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private long id;
    @SerializedName("position")
    private int position;
    @SerializedName("alias")
    private String[] alias;
    @SerializedName("status")
    private int status;
    @SerializedName("fee")
    private int fee;
    @SerializedName("copyrightId")
    private int copyrightId;
    @SerializedName("disc")
    private String disc;
    @SerializedName("no")
    private int no;
    @SerializedName("artists")
    private ArrayList<Artists> artists;
    @SerializedName("album")
    private Album album;
    @SerializedName("starred")
    private boolean starred;
    @SerializedName("popularity")
    private int popularity;
    @SerializedName("score")
    private int score;
    @SerializedName("starredNum")
    private int starredNum;
    @SerializedName("duration")
    private long duration;
    @SerializedName("playedNum")
    private int playedNum;
    @SerializedName("dayPlays")
    private int dayPlays;
    @SerializedName("hearTime")
    private int hearTime;
    @SerializedName("ringtone")
    private String ringtone;
    @SerializedName("crbt")
    private String crbt;
    @SerializedName("audition")
    private String audition;
    @SerializedName("copyFrom")
    private String copyFrom;
    @SerializedName("commentThreadId")
    private String commentThreadId;
    @SerializedName("rtUrl")
    private String rtUrl;
    @SerializedName("ftype")
    private int ftype;
    @SerializedName("rtUrls")
    private String[] rtUrls;
    @SerializedName("copyright")
    private int copyright;
    @SerializedName("rurl")
    private String rurl;
    @SerializedName("mvid")
    private int mvid;
    @SerializedName("hMusic")
    private FormatMusic hMusic;
    @SerializedName("mMusic")
    private FormatMusic mMusic;
    @SerializedName("lMusic")
    private FormatMusic lMusic;
    @SerializedName("bMusic")
    private FormatMusic bMusic;
    @SerializedName("mp3Url")
    private String mp3Url;
    @SerializedName("rtype")
    private int rtype;
    @SerializedName("alg")
    private String alg;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public String[] getAlias() {
        return alias;
    }

    public int getStatus() {
        return status;
    }

    public int getFee() {
        return fee;
    }

    public int getCopyrightId() {
        return copyrightId;
    }

    public String getDisc() {
        return disc;
    }

    public int getNo() {
        return no;
    }

    public ArrayList<Artists> getArtists() {
        return artists;
    }

    public Album getAlbum() {
        return album;
    }

    public boolean isStarred() {
        return starred;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getScore() {
        return score;
    }

    public int getStarredNum() {
        return starredNum;
    }

    public long getDuration() {
        return duration;
    }

    public int getPlayedNum() {
        return playedNum;
    }

    public int getDayPlays() {
        return dayPlays;
    }

    public int getHearTime() {
        return hearTime;
    }

    public String getRingtone() {
        return ringtone;
    }

    public String getCrbt() {
        return crbt;
    }

    public String getAudition() {
        return audition;
    }

    public String getCopyFrom() {
        return copyFrom;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public String getRtUrl() {
        return rtUrl;
    }

    public int getFtype() {
        return ftype;
    }

    public String[] getRtUrls() {
        return rtUrls;
    }

    public int getCopyright() {
        return copyright;
    }

    public String getRurl() {
        return rurl;
    }

    public int getMvid() {
        return mvid;
    }

    public FormatMusic gethMusic() {
        return hMusic;
    }

    public FormatMusic getmMusic() {
        return mMusic;
    }

    public FormatMusic getlMusic() {
        return lMusic;
    }

    public FormatMusic getbMusic() {
        return bMusic;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public int getRtype() {
        return rtype;
    }

    public String getAlg() {
        return alg;
    }
}
