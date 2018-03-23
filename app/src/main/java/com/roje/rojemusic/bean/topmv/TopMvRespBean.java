package com.roje.rojemusic.bean.topmv;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopMvRespBean {
    @SerializedName("updateTime")
    private long updateTime;
    @SerializedName("data")
    private List<MvBean> data;
    @SerializedName("hasMore")
    private boolean hasMore;
    @SerializedName("code")
    private int code;
    public void setUpdateTime(long updateTime) {
         this.updateTime = updateTime;
     }
     public long getUpdateTime() {
         return updateTime;
     }

    public void setData(List<MvBean> data) {
         this.data = data;
     }
     public List<MvBean> getData() {
         return data;
     }

    public void setHasMore(boolean hasMore) {
         this.hasMore = hasMore;
     }
     public boolean getHasMore() {
         return hasMore;
     }

    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

}