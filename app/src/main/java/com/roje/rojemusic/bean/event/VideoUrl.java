package com.roje.rojemusic.bean.event;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoUrl {

    /**
     * urls : [{"id":"0526E16D63328264774D4F649AB04D94","url":"http://vodkgeyttp8.vod.126.net/vodkgeyttp8/YhReXGSh_1336008193_shd.mp4?wsSecret=f2b8cdbbb2445b8aec93bbcd364a88f0&wsTime=1523776070","size":63677847,"validityTime":1200,"r":720}]
     * code : 200
     */

    @SerializedName("code")
    private int code;
    @SerializedName("urls")
    private List<Urls> urls;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Urls> getUrls() {
        return urls;
    }

    public void setUrls(List<Urls> urls) {
        this.urls = urls;
    }

    public static class Urls {
        /**
         * id : 0526E16D63328264774D4F649AB04D94
         * url : http://vodkgeyttp8.vod.126.net/vodkgeyttp8/YhReXGSh_1336008193_shd.mp4?wsSecret=f2b8cdbbb2445b8aec93bbcd364a88f0&wsTime=1523776070
         * size : 63677847
         * validityTime : 1200
         * r : 720
         */

        @SerializedName("id")
        private String id;
        @SerializedName("url")
        private String url;
        @SerializedName("size")
        private int size;
        @SerializedName("validityTime")
        private int validityTime;
        @SerializedName("r")
        private int r;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getValidityTime() {
            return validityTime;
        }

        public void setValidityTime(int validityTime) {
            this.validityTime = validityTime;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }
    }
}
