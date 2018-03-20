package com.roje.rojemusic.bean.response;


import com.google.gson.annotations.SerializedName;
import com.roje.rojemusic.bean.PlayList;

import java.util.ArrayList;

public class UserPlayListResponse {
    @SerializedName("more")
    private boolean more;
    @SerializedName("playlist")
    private ArrayList<PlayList> playlist;

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public ArrayList<PlayList> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<PlayList> playlist) {
        this.playlist = playlist;
    }
}
