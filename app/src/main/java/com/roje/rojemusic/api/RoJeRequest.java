package com.roje.rojemusic.api;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.roje.rojemusic.RjApplication;
import com.roje.rojemusic.cookie.CookieManger;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoJeRequest {
    private RoJeRequest() {
    }

    //    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static final Interceptor HEAD_INTERCEPTOR = new  Interceptor() {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Log.i("INTERCEPTOR",request.url().encodedPath());
            Request.Builder builder = request.newBuilder();
             builder.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
                    .header("Accept", "*/*")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,gl;q=0.6,zh-TW;q=0.4")
                    .header("Connection", "keep-alive")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Referer", "http://music.163.com")
                    .header("Host", "music.163.com")
                    .header("Accept-Language", "zh-CN,en-US;q=0.7,en;q=0.3");
             if ("weapi/login/cellphone".equals(request.url().encodedPath())){
             }
             return chain.proceed(builder.build());
        }
    };
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();//使用 gson coverter，统一日期请求格式
    private static File playListCacheDirectory = new File(RjApplication.application.get().getCacheDir(), "playlist_cache");
    private static int cacheSize = 10 * 1024 * 1024;
    private static Cache cache = new Cache(playListCacheDirectory, cacheSize);

    private static OkHttpClient client =  new OkHttpClient.Builder()
                .cookieJar(new CookieManger(RjApplication.application.get()))
//                .addInterceptor(logging)
                .addNetworkInterceptor(HEAD_INTERCEPTOR)
                .cache(cache)
                .build();
    private static RoJeApi roJeApi;
    private static final Object lock = new Object();

    public static RoJeApi getRoJeApi() {

        synchronized (lock) {
            if (roJeApi == null) {
                roJeApi = new Retrofit.Builder()
                        .baseUrl(RoJeApi.baseUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client)
                        .build()
                        .create(RoJeApi.class);
            }
            return roJeApi;
        }
    }
}
