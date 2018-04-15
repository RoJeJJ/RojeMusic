package com.roje.rojemusic.info.musiclist;

import com.roje.rojemusic.bean.playlist.Playlist;

import java.util.List;

public class SheetTabItem extends BaseItem {
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }



    public enum Type{
        title_type_create,
        title_type_collect
    }
    private Type type;
    private int position;

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    private boolean expand;
    public SheetTabItem(Type type,List<Playlist> data){
        this.type = type;
        this.playlist = data;
        expand = false;
    }

    public Type getType() {
        return type;
    }

    private List<Playlist> playlist;


    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylists(List<Playlist> playLists) {
        this.playlist = playLists;
    }
}
