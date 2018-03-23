package com.roje.rojemusic.present.impl;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roje.rojemusic.api.RoJeRequest;
import com.roje.rojemusic.bean.Banner;
import com.roje.rojemusic.bean.detail.UserDetailBean;
import com.roje.rojemusic.bean.login.LoginRootBean;
import com.roje.rojemusic.bean.newsong.NewSongRespBean;
import com.roje.rojemusic.bean.newsong.NewSongResult;
import com.roje.rojemusic.bean.personfm.Song;
import com.roje.rojemusic.bean.personfm.PersonFMBean;
import com.roje.rojemusic.bean.playlist.Playlist;
import com.roje.rojemusic.bean.playlist.PlaylistRootBean;
import com.roje.rojemusic.bean.privatecontent.PriContResult;
import com.roje.rojemusic.bean.privatecontent.PriContentRootBean;
import com.roje.rojemusic.bean.recommand.RecPlResult;
import com.roje.rojemusic.bean.recommand.RecPlaylistRootBean;
import com.roje.rojemusic.bean.response.BannerResponse;
import com.roje.rojemusic.bean.topmv.MvBean;
import com.roje.rojemusic.bean.topmv.TopMvRespBean;
import com.roje.rojemusic.present.MyException;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.utils.EncryptUtils;
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
    public void getBanners(Observer<List<Banner>> observer){
        Map<String,String> form = new HashMap<>();
        form.put("timeStamp",new Date().toString());
        form.put("csrf_token","");
        RoJeRequest.getRoJeApi().getBanners(form)
                .map(new Function<BannerResponse, List<Banner>>() {
                    @Override
                    public List<Banner> apply(BannerResponse bannerResponse) throws Exception {
                        return bannerResponse.getBanners();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void login(Map<String, String> form, Observer<LoginRootBean> Observer) {
        RoJeRequest.getRoJeApi().login(form)
                .map(new Function<ResponseBody, LoginRootBean>() {
                    @Override
                    public LoginRootBean apply(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        JsonObject o = new JsonParser().parse(json).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200)
                            return gson.fromJson(json,LoginRootBean.class);
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
    public void getPrivateContent(Observer<List<PriContResult>> observer) {
        Map<String,String> form = EncryptUtils.encrypt("{}");
        RoJeRequest.getRoJeApi().getPrivateContent(form)
                .map(new Function<ResponseBody, List<PriContResult>>() {
                    @Override
                    public List<PriContResult> apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200) {
                            PriContentRootBean bean = gson.fromJson(s, PriContentRootBean.class);
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

}
