package com.roje.rojemusic.present;


import com.google.gson.JsonObject;
import com.roje.rojemusic.bean.Banner;
import com.roje.rojemusic.bean.detail.UserDetailBean;
import com.roje.rojemusic.bean.login.LoginRootBean;
import com.roje.rojemusic.bean.newsong.NewSongResult;
import com.roje.rojemusic.bean.personfm.Song;
import com.roje.rojemusic.bean.playlist.Playlist;
import com.roje.rojemusic.bean.privatecontent.PriContResult;
import com.roje.rojemusic.bean.recommand.RecPlResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;

public interface Presenter {
    void getRecommendRes(JsonObject object,MyObserver<List<RecPlResult>> observer);
    void getPersonalFM(MyObserver<List<Song>> observer);
    void getUserPlaylist(JsonObject object, MyObserver<List<Playlist>> observer);
    void getBanners(MyObserver<List<Banner>> observer);
    void login(Map<String,String> form, MyObserver<LoginRootBean> Observer);
    void userDetail(long id,MyObserver<UserDetailBean> observer);
    void getPrivateContent(Observer<List<PriContResult>> observer);
    void newSong(Observer<List<NewSongResult>> observer);
}
