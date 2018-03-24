package com.roje.rojemusic.bean.newsong;

import com.roje.rojemusic.bean.personfm.Song;

public class NewSongResult {
    private long id;
    private int type;
    private String name;
    private String copywriter;
    private String picUrl;
    private boolean canDislike;
    private Song song;
    private String alg;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setCopywriter(String copywriter) {
         this.copywriter = copywriter;
     }
     public String getCopywriter() {
         return copywriter;
     }

    public void setPicUrl(String picUrl) {
         this.picUrl = picUrl;
     }
     public String getPicUrl() {
         return picUrl;
     }

    public void setCanDislike(boolean canDislike) {
         this.canDislike = canDislike;
     }
     public boolean getCanDislike() {
         return canDislike;
     }

    public void setSong(Song song) {
         this.song = song;
     }
     public Song getSong() {
         return song;
     }

    public void setAlg(String alg) {
         this.alg = alg;
     }
     public String getAlg() {
         return alg;
     }

}