package com.roje.rojemusic.bean.response;


import com.google.gson.annotations.SerializedName;
import com.roje.rojemusic.bean.Song;

import java.util.ArrayList;

public class FmResponse {
    @SerializedName("data")
    private ArrayList<Song> data;

    public ArrayList<Song> getData() {
        return data;
    }
}
