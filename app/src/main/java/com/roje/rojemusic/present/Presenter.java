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
import com.roje.rojemusic.bean.topmv.MvBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;

public interface Presenter {
    void getRecommendRes(JsonObject object,Observer<List<RecPlResult>> observer);
    void getPersonalFM(Observer<List<Song>> observer);
    void getUserPlaylist(JsonObject object, Observer<List<Playlist>> observer);
    void getBanners(Observer<List<Banner>> observer);
    void login(Map<String,String> form, Observer<LoginRootBean> Observer);
    void userDetail(long id,Observer<UserDetailBean> observer);
    void getPrivateContent(Observer<List<PriContResult>> observer);
    void newSong(Observer<List<NewSongResult>> observer);
    void topMV(JsonObject object, Observer<List<MvBean>> observer);
}
