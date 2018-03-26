package com.roje.rojemusic.bean.event;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventDetailBean {
    private String msg;
    private VideoBean video;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }

    public static class VideoBean {
        private int vid;
        private String coverUrl;
        private int duration;
        private int playTime;
        private int height;
        private int width;
        private int size;
        private int state;
        private int coverType;
        private Object urlInfo;
        private String videoId;
        private String threadId;
        private String title;
        private Object description;
        private CreatorBean creator;
        private int videoStatus;
        private int durationms;
        private List<ResolutionsBean> resolutions;

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

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
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

        public List<ResolutionsBean> getResolutions() {
            return resolutions;
        }

        public void setResolutions(List<ResolutionsBean> resolutions) {
            this.resolutions = resolutions;
        }

        public static class CreatorBean {
            private int userId;
            private int userType;
            private String nickname;
            private long avatarImgId;
            private String avatarUrl;
            private long backgroundImgId;
            private String backgroundUrl;
            private String signature;
            private long createTime;
            private String userName;
            private int accountType;
            private String shortUserName;
            private long birthday;
            private int authority;
            private int gender;
            private int accountStatus;
            private int province;
            private int city;
            private int authStatus;
            private Object description;
            private Object detailDescription;
            private boolean defaultAvatar;
            private Object expertTags;
            private ExpertsBean experts;
            private int djStatus;
            private int locationStatus;
            private int vipType;
            private boolean followed;
            private boolean mutual;
            private boolean authenticated;
            private long lastLoginTime;
            private String lastLoginIP;
            private Object remarkName;
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

            public ExpertsBean getExperts() {
                return experts;
            }

            public void setExperts(ExpertsBean experts) {
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

            public static class ExpertsBean {
                /**
                 * 1 : 视频达人
                 */

                @SerializedName("1")
                private String _$1;

                public String get_$1() {
                    return _$1;
                }

                public void set_$1(String _$1) {
                    this._$1 = _$1;
                }
            }
        }

        public static class ResolutionsBean {

            private int resolution;
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
