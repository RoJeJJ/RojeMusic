package com.roje.rojemusic.bean.event;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventRespBean {
    private int code;
    private boolean more;
    private long lasttime;
    private List<EventBean> event;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public long getLasttime() {
        return lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public List<EventBean> getEvent() {
        return event;
    }

    public void setEvent(List<EventBean> event) {
        this.event = event;
    }

    public static class EventBean {

        private Object actName;
        private int forwardCount;
        private int actId;
        private int tmplId;
        private String json;
        private UserBean user;
        private Object uuid;
        private long showTime;
        private long eventTime;
        private int expireTime;
        private RcmdInfoBean rcmdInfo;
        private long id;
        private int type;
        private boolean topEvent;
        private InfoBean info;
        private List<?> pics;

        public Object getActName() {
            return actName;
        }

        public void setActName(Object actName) {
            this.actName = actName;
        }

        public int getForwardCount() {
            return forwardCount;
        }

        public void setForwardCount(int forwardCount) {
            this.forwardCount = forwardCount;
        }

        public int getActId() {
            return actId;
        }

        public void setActId(int actId) {
            this.actId = actId;
        }

        public int getTmplId() {
            return tmplId;
        }

        public void setTmplId(int tmplId) {
            this.tmplId = tmplId;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public Object getUuid() {
            return uuid;
        }

        public void setUuid(Object uuid) {
            this.uuid = uuid;
        }

        public long getShowTime() {
            return showTime;
        }

        public void setShowTime(long showTime) {
            this.showTime = showTime;
        }

        public long getEventTime() {
            return eventTime;
        }

        public void setEventTime(long eventTime) {
            this.eventTime = eventTime;
        }

        public int getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(int expireTime) {
            this.expireTime = expireTime;
        }

        public RcmdInfoBean getRcmdInfo() {
            return rcmdInfo;
        }

        public void setRcmdInfo(RcmdInfoBean rcmdInfo) {
            this.rcmdInfo = rcmdInfo;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isTopEvent() {
            return topEvent;
        }

        public void setTopEvent(boolean topEvent) {
            this.topEvent = topEvent;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<?> getPics() {
            return pics;
        }

        public void setPics(List<?> pics) {
            this.pics = pics;
        }

        public static class UserBean {

            private boolean defaultAvatar;
            private int province;
            private int authStatus;
            private boolean followed;
            private String avatarUrl;
            private int accountStatus;
            private int gender;
            private int city;
            private long birthday;
            private int userId;
            private int userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private long avatarImgId;
            private long backgroundImgId;
            private String backgroundUrl;
            private int authority;
            private boolean mutual;
            private Object expertTags;
            private ExpertsBean experts;
            private int djStatus;
            private int vipType;
            private Object remarkName;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private boolean urlAnalyze;
            private String avatarImgId_str;

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

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

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public long getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(long avatarImgId) {
                this.avatarImgId = avatarImgId;
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

            public int getAuthority() {
                return authority;
            }

            public void setAuthority(int authority) {
                this.authority = authority;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
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

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public boolean isUrlAnalyze() {
                return urlAnalyze;
            }

            public void setUrlAnalyze(boolean urlAnalyze) {
                this.urlAnalyze = urlAnalyze;
            }

            public String getAvatarImgId_str() {
                return avatarImgId_str;
            }

            public void setAvatarImgId_str(String avatarImgId_str) {
                this.avatarImgId_str = avatarImgId_str;
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

        public static class RcmdInfoBean {
            /**
             * reason : 请随我深入明星们的小日常
             * alg : qrt_event
             * type : 1
             * scene : social
             */

            private String reason;
            private String alg;
            private int type;
            private String scene;

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getAlg() {
                return alg;
            }

            public void setAlg(String alg) {
                this.alg = alg;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getScene() {
                return scene;
            }

            public void setScene(String scene) {
                this.scene = scene;
            }
        }

        public static class InfoBean {
            /**
             * commentThread : {"id":"A_EV_2_3506237325_124948842","resourceInfo":null,"resourceType":2,"commentCount":218,"likedCount":1259,"shareCount":432,"hotCount":0,"latestLikedUsers":[{"s":1333415223,"t":1522034281442},{"s":317673614,"t":1522034268367},{"s":493623222,"t":1522034267158},{"s":1410287331,"t":1522034037459},{"s":1374521027,"t":1522033900716}],"resourceId":0,"resourceOwnerId":0,"resourceTitle":null}
             * latestLikedUsers : null
             * liked : false
             * comments : null
             * resourceType : 2
             * resourceId : 3506237325
             * commentCount : 218
             * likedCount : 1259
             * shareCount : 432
             * threadId : A_EV_2_3506237325_124948842
             */

            private CommentThreadBean commentThread;
            private Object latestLikedUsers;
            private boolean liked;
            private Object comments;
            private int resourceType;
            private long resourceId;
            private int commentCount;
            private int likedCount;
            private int shareCount;
            private String threadId;

            public CommentThreadBean getCommentThread() {
                return commentThread;
            }

            public void setCommentThread(CommentThreadBean commentThread) {
                this.commentThread = commentThread;
            }

            public Object getLatestLikedUsers() {
                return latestLikedUsers;
            }

            public void setLatestLikedUsers(Object latestLikedUsers) {
                this.latestLikedUsers = latestLikedUsers;
            }

            public boolean isLiked() {
                return liked;
            }

            public void setLiked(boolean liked) {
                this.liked = liked;
            }

            public Object getComments() {
                return comments;
            }

            public void setComments(Object comments) {
                this.comments = comments;
            }

            public int getResourceType() {
                return resourceType;
            }

            public void setResourceType(int resourceType) {
                this.resourceType = resourceType;
            }

            public long getResourceId() {
                return resourceId;
            }

            public void setResourceId(long resourceId) {
                this.resourceId = resourceId;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public int getLikedCount() {
                return likedCount;
            }

            public void setLikedCount(int likedCount) {
                this.likedCount = likedCount;
            }

            public int getShareCount() {
                return shareCount;
            }

            public void setShareCount(int shareCount) {
                this.shareCount = shareCount;
            }

            public String getThreadId() {
                return threadId;
            }

            public void setThreadId(String threadId) {
                this.threadId = threadId;
            }

            public static class CommentThreadBean {

                private String id;
                private Object resourceInfo;
                private int resourceType;
                private int commentCount;
                private int likedCount;
                private int shareCount;
                private int hotCount;
                private int resourceId;
                private int resourceOwnerId;
                private Object resourceTitle;
                private List<LatestLikedUsersBean> latestLikedUsers;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Object getResourceInfo() {
                    return resourceInfo;
                }

                public void setResourceInfo(Object resourceInfo) {
                    this.resourceInfo = resourceInfo;
                }

                public int getResourceType() {
                    return resourceType;
                }

                public void setResourceType(int resourceType) {
                    this.resourceType = resourceType;
                }

                public int getCommentCount() {
                    return commentCount;
                }

                public void setCommentCount(int commentCount) {
                    this.commentCount = commentCount;
                }

                public int getLikedCount() {
                    return likedCount;
                }

                public void setLikedCount(int likedCount) {
                    this.likedCount = likedCount;
                }

                public int getShareCount() {
                    return shareCount;
                }

                public void setShareCount(int shareCount) {
                    this.shareCount = shareCount;
                }

                public int getHotCount() {
                    return hotCount;
                }

                public void setHotCount(int hotCount) {
                    this.hotCount = hotCount;
                }

                public int getResourceId() {
                    return resourceId;
                }

                public void setResourceId(int resourceId) {
                    this.resourceId = resourceId;
                }

                public int getResourceOwnerId() {
                    return resourceOwnerId;
                }

                public void setResourceOwnerId(int resourceOwnerId) {
                    this.resourceOwnerId = resourceOwnerId;
                }

                public Object getResourceTitle() {
                    return resourceTitle;
                }

                public void setResourceTitle(Object resourceTitle) {
                    this.resourceTitle = resourceTitle;
                }

                public List<LatestLikedUsersBean> getLatestLikedUsers() {
                    return latestLikedUsers;
                }

                public void setLatestLikedUsers(List<LatestLikedUsersBean> latestLikedUsers) {
                    this.latestLikedUsers = latestLikedUsers;
                }

                public static class LatestLikedUsersBean {
                    /**
                     * s : 1333415223
                     * t : 1522034281442
                     */

                    private int s;
                    private long t;

                    public int getS() {
                        return s;
                    }

                    public void setS(int s) {
                        this.s = s;
                    }

                    public long getT() {
                        return t;
                    }

                    public void setT(long t) {
                        this.t = t;
                    }
                }
            }
        }
    }
}
