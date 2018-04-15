package com.roje.rojemusic.api;


import com.roje.rojemusic.bean.comment.CommentResp;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RoJeApi {
    String baseUrl = "http://music.163.com";

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/weapi/login/cellphone?csrf_token=")
    Observable<ResponseBody> login(@FieldMap Map<String,String> form);

    /**
     *用户详情
     * @param id userId
     */
    @FormUrlEncoded
    @POST("/weapi/v1/user/detail/{uid}")
    Observable<ResponseBody> userDetail(@Path("uid") long id, @FieldMap Map<String,String> token);

    /**
     * 推荐歌单
     */
    @FormUrlEncoded
    @POST("/weapi/personalized/playlist")
    Observable<ResponseBody> getRecommendRes(@FieldMap Map<String,String> form);

    /**
     * 私人fm
     */
    @FormUrlEncoded
    @POST("/weapi/v1/radio/get")
    Observable<ResponseBody> getPersonalFM(@FieldMap Map<String,String> form);

    /**
     * 用户歌单
     */
    @FormUrlEncoded
    @POST("/weapi/user/playlist")
    Observable<ResponseBody> getUserPlayList(@FieldMap Map<String,String> form);

    /**
     * 广告横幅
     */
    @FormUrlEncoded
    @POST("/weapi/banner/get/v3")
    Observable<ResponseBody> getBanners(@FieldMap Map<String,String> form);
    /**
     * 独家放送
     */
    @FormUrlEncoded
    @POST("/weapi/personalized/privatecontent")
    Observable<ResponseBody> getPrivateContent(@FieldMap Map<String,String> form);

    /**
     * 最新音乐
     */
    @FormUrlEncoded
    @POST("/weapi/personalized/newsong")
    Observable<ResponseBody> newSong(@FieldMap Map<String,String> form);
    /**
     * MV排行
     */
    @FormUrlEncoded
    @POST("/weapi/mv/toplist")
    Observable<ResponseBody> topMV(@FieldMap Map<String,String> form);
    /**
     * 动态消息
     */
    @FormUrlEncoded
    @POST("/weapi/v1/event/get")
    Observable<ResponseBody> event(@FieldMap Map<String,String> form);

    /**
     * 每日推荐歌曲
     */
    @FormUrlEncoded
    @POST("/weapi/v1/discovery/recommend/songs")
    Observable<ResponseBody> recommendSong(@FieldMap Map<String,String> form);

    /**
     * 获取评论
     * @param threadId threadid
     * @param limit 评论数目
     * @param offset 偏移量
     */
    @GET("/api/v1/resource/comments/{threadId}")
    Observable<CommentResp> comment(@Path("threadId") String threadId, @Query("limit") int limit, @Query("offset") int offset);
    /**
     * 视频地址
     */
    @FormUrlEncoded
    @POST("/weapi/cloudvideo/playurl")
    Observable<ResponseBody> mvAddr(@FieldMap Map<String,String> form);

    /**
     * 排行榜
     */
    @FormUrlEncoded
    @POST("/weapi/pl/count")
    Observable<ResponseBody> plCount(@FieldMap Map<String,String> form);
}
