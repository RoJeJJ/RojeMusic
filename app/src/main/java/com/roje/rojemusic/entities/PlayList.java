package com.roje.rojemusic.entities;


public class PlayList {

    private long id;
    private String name;
    private int songCount;
    private String albumArt;
    private String author;


    public PlayList(long _id, String _name, int _songCount, String _albumArt, String author) {
        this.id = _id;
        this.name = _name;
        this.songCount = _songCount;
        this.albumArt = _albumArt;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
