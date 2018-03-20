package com.roje.rojemusic.bean.response;


import com.google.gson.annotations.SerializedName;
import com.roje.rojemusic.bean.Banner;

import java.util.ArrayList;

public class BannerResponse {
    @SerializedName("banners")
    private ArrayList<Banner> banners;

    public ArrayList<Banner> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<Banner> banners) {
        this.banners = banners;
    }
}
