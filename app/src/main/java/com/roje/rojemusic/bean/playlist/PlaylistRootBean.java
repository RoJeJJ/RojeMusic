package com.roje.rojemusic.bean.playlist;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PlaylistRootBean {
    @SerializedName("more")
    private boolean more;
    @SerializedName("playlist")
    private List<Playlist> playlist;
    @SerializedName("code")
    private int code;

    public void setMore(boolean more) {
        this.more = more;
    }

    public boolean getMore() {
        return this.more;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }

    public List<Playlist> getPlaylist() {
        return this.playlist;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}