package com.roje.rojemusic.bean.content;

import java.util.List;

public class ContentRespBean {
    private int code;
    private String name;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private long id;
        private String url;
        private String picUrl;
        private String sPicUrl;
        private int type;
        private String copywriter;
        private String name;
        private int eventUserId;
        private int width;
        private String videoId;
        private int eventType;
        private String alg;
        private int height;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getSPicUrl() {
            return sPicUrl;
        }

        public void setSPicUrl(String sPicUrl) {
            this.sPicUrl = sPicUrl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCopywriter() {
            return copywriter;
        }

        public void setCopywriter(String copywriter) {
            this.copywriter = copywriter;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getEventUserId() {
            return eventUserId;
        }

        public void setEventUserId(int eventUserId) {
            this.eventUserId = eventUserId;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public int getEventType() {
            return eventType;
        }

        public void setEventType(int eventType) {
            this.eventType = eventType;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
