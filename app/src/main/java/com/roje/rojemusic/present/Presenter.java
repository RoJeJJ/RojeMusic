package com.roje.rojemusic.present;


import com.google.gson.JsonObject;
import com.roje.rojemusic.bean.Banner;
import com.roje.rojemusic.bean.detail.UserDetailBean;
import com.roje.rojemusic.bean.personfm.Data;
import com.roje.rojemusic.bean.playlist.Playlist;
import com.roje.rojemusic.bean.recommand.Result;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

public interface Presenter {
    void getRecommendRes(JsonObject object,MyObserver<List<Result>> observer);
    void getPersonalFM(MyObserver<List<Data>> observer);
    void getUserPlaylist(JsonObject object, MyObserver<List<Playlist>> observer);
    void getBanners(MyObserver<List<Banner>> observer);
    void login(Map<String,String> form, MyObserver<String> Observer);
    void userDetail(long id,MyObserver<UserDetailBean> observer);
    void getPrivateContent(Consumer<String> consumer);
}
