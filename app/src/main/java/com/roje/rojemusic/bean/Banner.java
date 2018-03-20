package com.roje.rojemusic.bean;


import com.google.gson.annotations.SerializedName;

public class Banner {
    @SerializedName("targetType")
    private int targetType;
    @SerializedName("pic")
    private String pic;
    @SerializedName("adLocation")
    private String adLocation;
    @SerializedName("adid")
    private String adid;
    @SerializedName("titleColor")
    private String titleColor;
    @SerializedName("typeTitle")
    private String typeTitle;
    @SerializedName("encodeId")
    private String encodeId;
    @SerializedName("targetId")
    private long targetId;
    @SerializedName("adSource")
    private String adSource;
    @SerializedName("extMonitor")
    private String extMonitor;
    @SerializedName("extMonitorInfo")
    private String extMonitorInfo;
    @SerializedName("monitorClickList")
    private String[] monitorClickList;
    @SerializedName("monitorImpressList")
    private String[] monitorImpressList;
    @SerializedName("monitorClick")
    private String monitorClick;
    @SerializedName("monitorImpress")
    private String monitorImpress;
    @SerializedName("monitorType")
    private String monitorType;
    @SerializedName("monitorBlackList")
    private String monitorBlackList;
    @SerializedName("url")
    private String url;
    @SerializedName("exclusive")
    private boolean exclusive;

    public int getTargetType() {
        return targetType;
    }

    public String getPic() {
        return pic;
    }

    public String getAdLocation() {
        return adLocation;
    }

    public String getAdid() {
        return adid;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public String getEncodeId() {
        return encodeId;
    }

    public long getTargetId() {
        return targetId;
    }

    public String getAdSource() {
        return adSource;
    }

    public String getExtMonitor() {
        return extMonitor;
    }

    public String getExtMonitorInfo() {
        return extMonitorInfo;
    }

    public String[] getMonitorClickList() {
        return monitorClickList;
    }

    public String[] getMonitorImpressList() {
        return monitorImpressList;
    }

    public String getMonitorClick() {
        return monitorClick;
    }

    public String getMonitorImpress() {
        return monitorImpress;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public String getMonitorBlackList() {
        return monitorBlackList;
    }

    public String getUrl() {
        return url;
    }

    public boolean isExclusive() {
        return exclusive;
    }
}
