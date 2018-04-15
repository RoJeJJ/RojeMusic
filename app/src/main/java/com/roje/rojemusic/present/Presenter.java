package com.roje.rojemusic.present;


import com.google.gson.JsonObject;
import com.roje.rojemusic.bean.banner.BannerRespBean;
import com.roje.rojemusic.bean.comment.CommentResp;
import com.roje.rojemusic.bean.content.ContentRespBean;
import com.roje.rojemusic.bean.detail.UserDetailBean;
import com.roje.rojemusic.bean.event.EventRespBean;
import com.roje.rojemusic.bean.event.VideoUrl;
import com.roje.rojemusic.bean.login.LoginRespBean;
import com.roje.rojemusic.bean.newsong.NewSongResult;
import com.roje.rojemusic.bean.personfm.Song;
import com.roje.rojemusic.bean.playlist.Playlist;
import com.roje.rojemusic.bean.daily_song.RecDailySongRespBean;
import com.roje.rojemusic.bean.recommand.RecPlResult;
import com.roje.rojemusic.bean.topmv.MvBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;

public interface Presenter {
    void getRecommendRes(JsonObject object,Observer<List<RecPlResult>> observer);
    void getPersonalFM(Observer<List<Song>> observer);
    void getUserPlaylist(JsonObject object, Observer<List<Playlist>> observer);
    void getBanners(Observer<List<BannerRespBean.BannersBean>> observer);
    void login(Map<String,String> form, Observer<LoginRespBean> Observer);
    void userDetail(long id,Observer<UserDetailBean> observer);
    void getPrivateContent(Observer<List<ContentRespBean.ResultBean>> observer);
    void newSong(Observer<List<NewSongResult>> observer);
    void topMV(JsonObject object, Observer<List<MvBean>> observer);
    void event(Observer<List<EventRespBean.EventBean>> observer);
    void recommendSong(JsonObject object, Observer<List<RecDailySongRespBean.RecommendBean>> observer);
    void comment(String threadId, int limit, int offset, Observer<CommentResp> observer);
    void mvAddr(JsonObject object,Observer<VideoUrl> observer);
    void plCount(Observer<String> observer);
}
