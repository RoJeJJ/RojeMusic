package com.roje.rojemusic.present.impl;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roje.rojemusic.api.RoJeRequest;
import com.roje.rojemusic.bean.banner.BannerRespBean;
import com.roje.rojemusic.bean.comment.CommentResp;
import com.roje.rojemusic.bean.content.ContentRespBean;
import com.roje.rojemusic.bean.daily_song.RecDailySongRespBean;
import com.roje.rojemusic.bean.detail.UserDetailBean;
import com.roje.rojemusic.bean.event.EventRespBean;
import com.roje.rojemusic.bean.event.VideoUrl;
import com.roje.rojemusic.bean.login.LoginRespBean;
import com.roje.rojemusic.bean.newsong.NewSongRespBean;
import com.roje.rojemusic.bean.newsong.NewSongResult;
import com.roje.rojemusic.bean.personfm.PersonFMBean;
import com.roje.rojemusic.bean.personfm.Song;
import com.roje.rojemusic.bean.playlist.Playlist;
import com.roje.rojemusic.bean.playlist.PlaylistRootBean;
import com.roje.rojemusic.bean.recommand.RecPlResult;
import com.roje.rojemusic.bean.recommand.RecPlaylistRootBean;
import com.roje.rojemusic.bean.topmv.MvBean;
import com.roje.rojemusic.bean.topmv.TopMvRespBean;
import com.roje.rojemusic.present.MyException;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.utils.EncryptUtils;
import com.roje.rojemusic.utils.LogUtil;
import com.roje.rojemusic.utils.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class PresenterImpl implements Presenter{
    private Gson gson;
    public PresenterImpl(){
        gson = new Gson();
    }
    public void getRecommendRes(JsonObject object,Observer<List<RecPlResult>> observer) {
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().getRecommendRes(form)
                .map(new Function<ResponseBody, List<RecPlResult>>() {
                    @Override
                    public List<RecPlResult> apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200){
                            RecPlaylistRootBean rootBean =
                                    gson.fromJson(s, RecPlaylistRootBean.class);
                            return rootBean.getResult();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getPersonalFM(Observer<List<Song>> observer) {
        JsonObject object = new JsonObject();
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().getPersonalFM(form)
                .map(new Function<ResponseBody, List<Song>>() {
                    @Override
                    public List<Song> apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200){
                            PersonFMBean bean = gson.fromJson(s,PersonFMBean.class);
                            return bean.getData();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    public void getUserPlaylist(JsonObject object, Observer<List<Playlist>> observer) {
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().getUserPlayList(form)
                .map(new Function<ResponseBody, List<Playlist>>() {
                    @Override
                    public List<Playlist> apply(ResponseBody response) throws Exception {
                        String s = response.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200){
                            PlaylistRootBean bean = gson.fromJson(s,PlaylistRootBean.class);
                            return bean.getPlaylist();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    public void getBanners(Observer<List<BannerRespBean.BannersBean>> observer){
        Map<String,String> form = new HashMap<>();
        form.put("timeStamp",new Date().toString());
        form.put("csrf_token","");
        RoJeRequest.getRoJeApi().getBanners(form)
                .map(new Function<ResponseBody, List<BannerRespBean.BannersBean>>() {
                    @Override
                    public List<BannerRespBean.BannersBean> apply(ResponseBody responseBody) throws Exception {
                        String data = responseBody.string();
                        JsonObject o = new JsonParser().parse(data).getAsJsonObject();
                        int code = o.get("code").getAsInt();
                        switch (code){
                            case 200:
                                BannerRespBean bean = gson.fromJson(data,BannerRespBean.class);
                                return bean.getBanners();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void login(Map<String, String> form, Observer<LoginRespBean> Observer) {
        RoJeRequest.getRoJeApi().login(form)
                .map(new Function<ResponseBody, LoginRespBean>() {
                    @Override
                    public LoginRespBean apply(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        JsonObject o = new JsonParser().parse(json).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200)
                            return gson.fromJson(json,LoginRespBean.class);
                        else
                            throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Observer);
    }

    @Override
    public void userDetail(long id, Observer<UserDetailBean> observer) {
        JsonObject object = new JsonObject();
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().userDetail(id,form)
                .map(new Function<ResponseBody, UserDetailBean>() {
                    @Override
                    public UserDetailBean apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200)
                            return gson.fromJson(s,UserDetailBean.class);
                        else
                            throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getPrivateContent(Observer<List<ContentRespBean.ResultBean>> observer) {
        Map<String,String> form = EncryptUtils.encrypt("{}");
        RoJeRequest.getRoJeApi().getPrivateContent(form)
                .map(new Function<ResponseBody, List<ContentRespBean.ResultBean>>() {
                    @Override
                    public List<ContentRespBean.ResultBean> apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200) {
                            ContentRespBean bean = gson.fromJson(s, ContentRespBean.class);
                            return bean.getResult();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void newSong(Observer<List<NewSongResult>> observer) {
        JsonObject object = new JsonObject();
        object.addProperty("limit",6);
        object.addProperty("n",1000);
        object.addProperty("total",true);
        object.addProperty("type","recommend");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().newSong(form)
                .map(new Function<ResponseBody, List<NewSongResult>>() {
                    @Override
                    public List<NewSongResult> apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200){
                            NewSongRespBean bean = gson.fromJson(s,NewSongRespBean.class);
                            return bean.getResult();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void topMV(JsonObject object, Observer<List<MvBean>> observer) {
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().topMV(form)
                .map(new Function<ResponseBody, List<MvBean>>() {
                    @Override
                    public List<MvBean> apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200){
                            TopMvRespBean bean = gson.fromJson(s,TopMvRespBean.class);
                            return bean.getData();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void event(Observer<List<EventRespBean.EventBean>> observer) {
        JsonObject object = new JsonObject();
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().event(form)
                .map(new Function<ResponseBody, List<EventRespBean.EventBean>>() {
                    @Override
                    public List<EventRespBean.EventBean> apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        int code = o.get("code").getAsInt();
                        LogUtil.i(code+"");
                        if (code == 200){
                            EventRespBean bean = gson.fromJson(s, EventRespBean.class);
                            return bean.getEvent();
                        }
                        throw new MyException(code,o.get("msg") == JsonNull.INSTANCE ? "null":o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void recommendSong(JsonObject object,Observer<List<RecDailySongRespBean.RecommendBean>> observer) {
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().recommendSong(form)
                .map(new Function<ResponseBody, List<RecDailySongRespBean.RecommendBean>>() {
                    @Override
                    public List<RecDailySongRespBean.RecommendBean> apply(ResponseBody responseBody) throws Exception {
                        String data = responseBody.string();
                        Utils.write2File("song.json",data);
                        JsonObject o = new JsonParser().parse(data).getAsJsonObject();
                        int code = o.get("code").getAsInt();
                        if (code == 200){
                            RecDailySongRespBean bean = gson.fromJson(data,RecDailySongRespBean.class);
                            return bean.getRecommend();
                        }
                        throw new MyException(code,o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void comment(String threadId, int limit, int offset, Observer<CommentResp> observer) {
        RoJeRequest.getRoJeApi().comment(threadId,limit,offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void mvAddr(JsonObject object, Observer<VideoUrl> observer) {
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().mvAddr(form)
                .map(new Function<ResponseBody, VideoUrl>() {
                    @Override
                    public VideoUrl apply(ResponseBody responseBody) throws Exception {
                        String data = responseBody.string();
                        VideoUrl vurl = gson.fromJson(data,VideoUrl.class);
                        return vurl;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void plCount(Observer<String> observer) {
        JsonObject object = new JsonObject();
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().plCount(form)
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        String data = responseBody.string();
                        LogUtil.i(data);
                        return data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
