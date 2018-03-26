package com.roje.rojemusic.bean.banner;


import java.util.List;

public class BannerRespBean {
    private int code;
    private List<BannersBean> banners;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean {
        private Object monitorClick;
        private Object monitorImpress;
        private Object monitorType;
        private Object monitorBlackList;
        private Object adSource;
        private Object extMonitor;
        private Object extMonitorInfo;
        private String pic;
        private int targetType;
        private Object adLocation;
        private String titleColor;
        private String typeTitle;
        private String encodeId;
        private Object adid;
        private boolean exclusive;
        private int targetId;
        private String url;
        private List<?> monitorClickList;
        private List<?> monitorImpressList;

        public Object getMonitorClick() {
            return monitorClick;
        }

        public void setMonitorClick(Object monitorClick) {
            this.monitorClick = monitorClick;
        }

        public Object getMonitorImpress() {
            return monitorImpress;
        }

        public void setMonitorImpress(Object monitorImpress) {
            this.monitorImpress = monitorImpress;
        }

        public Object getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(Object monitorType) {
            this.monitorType = monitorType;
        }

        public Object getMonitorBlackList() {
            return monitorBlackList;
        }

        public void setMonitorBlackList(Object monitorBlackList) {
            this.monitorBlackList = monitorBlackList;
        }

        public Object getAdSource() {
            return adSource;
        }

        public void setAdSource(Object adSource) {
            this.adSource = adSource;
        }

        public Object getExtMonitor() {
            return extMonitor;
        }

        public void setExtMonitor(Object extMonitor) {
            this.extMonitor = extMonitor;
        }

        public Object getExtMonitorInfo() {
            return extMonitorInfo;
        }

        public void setExtMonitorInfo(Object extMonitorInfo) {
            this.extMonitorInfo = extMonitorInfo;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getTargetType() {
            return targetType;
        }

        public void setTargetType(int targetType) {
            this.targetType = targetType;
        }

        public Object getAdLocation() {
            return adLocation;
        }

        public void setAdLocation(Object adLocation) {
            this.adLocation = adLocation;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }

        public String getEncodeId() {
            return encodeId;
        }

        public void setEncodeId(String encodeId) {
            this.encodeId = encodeId;
        }

        public Object getAdid() {
            return adid;
        }

        public void setAdid(Object adid) {
            this.adid = adid;
        }

        public boolean isExclusive() {
            return exclusive;
        }

        public void setExclusive(boolean exclusive) {
            this.exclusive = exclusive;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<?> getMonitorClickList() {
            return monitorClickList;
        }

        public void setMonitorClickList(List<?> monitorClickList) {
            this.monitorClickList = monitorClickList;
        }

        public List<?> getMonitorImpressList() {
            return monitorImpressList;
        }

        public void setMonitorImpressList(List<?> monitorImpressList) {
            this.monitorImpressList = monitorImpressList;
        }
    }
}
