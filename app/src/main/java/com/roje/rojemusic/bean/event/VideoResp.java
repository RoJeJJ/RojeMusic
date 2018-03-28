package com.roje.rojemusic.bean.event;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResp {
    @SerializedName("msg")
    private String msg;
    @SerializedName("video")
    private Video video;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public static class Video {
        @SerializedName("vid")
        private int vid;
        @SerializedName("coverUrl")
        private String coverUrl;
        @SerializedName("duration")
        private int duration;
        @SerializedName("playTime")
        private int playTime;
        @SerializedName("height")
        private int height;
        @SerializedName("width")
        private int width;
        @SerializedName("size")
        private int size;
        @SerializedName("state")
        private int state;
        @SerializedName("coverType")
        private int coverType;
        @SerializedName("urlInfo")
        private Object urlInfo;
        @SerializedName("videoId")
        private String videoId;
        @SerializedName("threadId")
        private String threadId;
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private Object description;
        @SerializedName("creator")
        private Creator creator;
        @SerializedName("videoStatus")
        private int videoStatus;
        @SerializedName("durationms")
        private int durationms;
        @SerializedName("resolutions")
        private List<Resolutions> resolutions;

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getCoverType() {
            return coverType;
        }

        public void setCoverType(int coverType) {
            this.coverType = coverType;
        }

        public Object getUrlInfo() {
            return urlInfo;
        }

        public void setUrlInfo(Object urlInfo) {
            this.urlInfo = urlInfo;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getThreadId() {
            return threadId;
        }

        public void setThreadId(String threadId) {
            this.threadId = threadId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Creator getCreator() {
            return creator;
        }

        public void setCreator(Creator creator) {
            this.creator = creator;
        }

        public int getVideoStatus() {
            return videoStatus;
        }

        public void setVideoStatus(int videoStatus) {
            this.videoStatus = videoStatus;
        }

        public int getDurationms() {
            return durationms;
        }

        public void setDurationms(int durationms) {
            this.durationms = durationms;
        }

        public List<Resolutions> getResolutions() {
            return resolutions;
        }

        public void setResolutions(List<Resolutions> resolutions) {
            this.resolutions = resolutions;
        }

        public static class Creator {
            /**
             * userId : 124948842
             * userType : 0
             * nickname : BrokenHeartssss
             * avatarImgId : 109951163207124309
             * avatarUrl : http://p1.music.126.net/7aU1K2mmo3o3M2zlq74CGQ==/109951163207124309.jpg
             * backgroundImgId : 109951163202452055
             * backgroundUrl : http://p1.music.126.net/iaN9ajEFNgNEinTW-3Yc9A==/109951163202452055.jpg
             * signature : RIHANNA&DuaLipa&Bebe&Selena&Lana&MØ&Shakira&MGK&You……欢迎加入英倫危險甜心DUA LIPA，群号码：647712445
             * createTime : 1453103118718
             * userName : 10_oZoefuKATi3Nnq7awbPSQdAL9ZoI
             * accountType : 10
             * shortUserName : oZoefuKATi3Nnq7awbPSQdAL9ZoI
             * birthday : -1548150753897
             * authority : 0
             * gender : 1
             * accountStatus : 0
             * province : 500000
             * city : 500101
             * authStatus : 0
             * description : null
             * detailDescription : null
             * defaultAvatar : false
             * expertTags : null
             * experts : {"1":"视频达人"}
             * djStatus : 10
             * locationStatus : 30
             * vipType : 0
             * followed : false
             * mutual : false
             * authenticated : false
             * lastLoginTime : 1522032640318
             * lastLoginIP : 61.186.167.166
             * remarkName : null
             * viptypeVersion : 0
             */

            @SerializedName("userId")
            private int userId;
            @SerializedName("userType")
            private int userType;
            @SerializedName("nickname")
            private String nickname;
            @SerializedName("avatarImgId")
            private long avatarImgId;
            @SerializedName("avatarUrl")
            private String avatarUrl;
            @SerializedName("backgroundImgId")
            private long backgroundImgId;
            @SerializedName("backgroundUrl")
            private String backgroundUrl;
            @SerializedName("signature")
            private String signature;
            @SerializedName("createTime")
            private long createTime;
            @SerializedName("userName")
            private String userName;
            @SerializedName("accountType")
            private int accountType;
            @SerializedName("shortUserName")
            private String shortUserName;
            @SerializedName("birthday")
            private long birthday;
            @SerializedName("authority")
            private int authority;
            @SerializedName("gender")
            private int gender;
            @SerializedName("accountStatus")
            private int accountStatus;
            @SerializedName("province")
            private int province;
            @SerializedName("city")
            private int city;
            @SerializedName("authStatus")
            private int authStatus;
            @SerializedName("description")
            private Object description;
            @SerializedName("detailDescription")
            private Object detailDescription;
            @SerializedName("defaultAvatar")
            private boolean defaultAvatar;
            @SerializedName("expertTags")
            private Object expertTags;
            @SerializedName("experts")
            private Experts experts;
            @SerializedName("djStatus")
            private int djStatus;
            @SerializedName("locationStatus")
            private int locationStatus;
            @SerializedName("vipType")
            private int vipType;
            @SerializedName("followed")
            private boolean followed;
            @SerializedName("mutual")
            private boolean mutual;
            @SerializedName("authenticated")
            private boolean authenticated;
            @SerializedName("lastLoginTime")
            private long lastLoginTime;
            @SerializedName("lastLoginIP")
            private String lastLoginIP;
            @SerializedName("remarkName")
            private Object remarkName;
            @SerializedName("viptypeVersion")
            private int viptypeVersion;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public long getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(long avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public long getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(long backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getAccountType() {
                return accountType;
            }

            public void setAccountType(int accountType) {
                this.accountType = accountType;
            }

            public String getShortUserName() {
                return shortUserName;
            }

            public void setShortUserName(String shortUserName) {
                this.shortUserName = shortUserName;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public int getAuthority() {
                return authority;
            }

            public void setAuthority(int authority) {
                this.authority = authority;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(Object detailDescription) {
                this.detailDescription = detailDescription;
            }

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public Object getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(Object expertTags) {
                this.expertTags = expertTags;
            }

            public Experts getExperts() {
                return experts;
            }

            public void setExperts(Experts experts) {
                this.experts = experts;
            }

            public int getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(int djStatus) {
                this.djStatus = djStatus;
            }

            public int getLocationStatus() {
                return locationStatus;
            }

            public void setLocationStatus(int locationStatus) {
                this.locationStatus = locationStatus;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public boolean isAuthenticated() {
                return authenticated;
            }

            public void setAuthenticated(boolean authenticated) {
                this.authenticated = authenticated;
            }

            public long getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(long lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getLastLoginIP() {
                return lastLoginIP;
            }

            public void setLastLoginIP(String lastLoginIP) {
                this.lastLoginIP = lastLoginIP;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public int getViptypeVersion() {
                return viptypeVersion;
            }

            public void setViptypeVersion(int viptypeVersion) {
                this.viptypeVersion = viptypeVersion;
            }

            public static class Experts {
                /**
                 * 1 : 视频达人
                 */

                @SerializedName("1")
                private String $1;

                public String get$1() {
                    return $1;
                }

                public void set$1(String $1) {
                    this.$1 = $1;
                }
            }
        }

        public static class Resolutions {
            /**
             * resolution : 240
             * size : 2038979
             */

            @SerializedName("resolution")
            private int resolution;
            @SerializedName("size")
            private int size;

            public int getResolution() {
                return resolution;
            }

            public void setResolution(int resolution) {
                this.resolution = resolution;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }
    }
}
