package com.roje.rojemusic.entities;


public class MusicMainItemTitle {
    private int id;
    private String title;
    private int count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MusicMainItemTitle(int id, String title, int count) {
        this.id = id;
        this.title = title;
        this.count = count;
    }
}
