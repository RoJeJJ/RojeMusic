package com.roje.rojemusic.present.impl;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roje.rojemusic.api.RoJeRequest;
import com.roje.rojemusic.bean.Banner;
import com.roje.rojemusic.bean.detail.UserDetailBean;
import com.roje.rojemusic.bean.login.LoginRootBean;
import com.roje.rojemusic.bean.personfm.Data;
import com.roje.rojemusic.bean.personfm.PersonFMBean;
import com.roje.rojemusic.bean.playlist.Playlist;
import com.roje.rojemusic.bean.playlist.PlaylistRootBean;
import com.roje.rojemusic.bean.privatecontent.PriContResult;
import com.roje.rojemusic.bean.privatecontent.PriContentRootBean;
import com.roje.rojemusic.bean.recommand.RecPlResult;
import com.roje.rojemusic.bean.recommand.RecPlaylistRootBean;
import com.roje.rojemusic.bean.response.BannerResponse;
import com.roje.rojemusic.present.MyException;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.utils.EncryptUtils;
import com.roje.rojemusic.utils.LogUtil;

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
    public PresenterImpl(){
    }
    public void getRecommendRes(JsonObject object,MyObserver<List<RecPlResult>> observer) {
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
                                    new Gson().fromJson(s, RecPlaylistRootBean.class);
                            return rootBean.getResult();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getPersonalFM(MyObserver<List<Data>> observer) {
        JsonObject object = new JsonObject();
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().getPersonalFM(form)
                .map(new Function<ResponseBody, List<Data>>() {
                    @Override
                    public List<Data> apply(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200){
                            PersonFMBean bean = new Gson().fromJson(s,PersonFMBean.class);
                            return bean.getData();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    public void getUserPlaylist(JsonObject object, MyObserver<List<Playlist>> observer) {
        object.addProperty("csrf_token","");
        Map<String,String> form = EncryptUtils.encrypt(object.toString());
        RoJeRequest.getRoJeApi().getUserPlayList(form)
                .map(new Function<ResponseBody, List<Playlist>>() {
                    @Override
                    public List<Playlist> apply(ResponseBody response) throws Exception {
                        String s = response.string();
                        JsonObject o = new JsonParser().parse(s).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200){
                            PlaylistRootBean bean = new Gson().fromJson(s,PlaylistRootBean.class);
                            return bean.getPlaylist();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    public void getBanners(MyObserver<List<Banner>> observer){
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
    public void login(Map<String, String> form, MyObserver<LoginRootBean> Observer) {
        RoJeRequest.getRoJeApi().login(form)
                .map(new Function<ResponseBody, LoginRootBean>() {
                    @Override
                    public LoginRootBean apply(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        JsonObject o = new JsonParser().parse(json).getAsJsonObject();
                        if (o.get("code").getAsInt() == 200)
                            return new Gson().fromJson(json,LoginRootBean.class);
                        else
                            throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Observer);
    }

    @Override
    public void userDetail(long id, MyObserver<UserDetailBean> observer) {
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
                            return new Gson().fromJson(s,UserDetailBean.class);
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
                            PriContentRootBean bean = new Gson().fromJson(s, PriContentRootBean.class);
                            return bean.getResult();
                        }
                        throw new MyException(o.get("code").getAsInt(),o.get("msg").getAsString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
