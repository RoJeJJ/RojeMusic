package com.roje.rojemusic.bean.topmv;


import com.google.gson.annotations.SerializedName;

public class Artist {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

}