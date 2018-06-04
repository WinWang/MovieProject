package com.lepoint.ljfmvp.http;

import com.lepoint.ljfmvp.model.BannerBean;
import com.lepoint.ljfmvp.model.BaseModel;
import com.lepoint.ljfmvp.model.FindHeaderBean;
import com.lepoint.ljfmvp.model.FindNewsBean;
import com.lepoint.ljfmvp.model.HistoryListBean;
import com.lepoint.ljfmvp.model.HomeChannelBean;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.model.MovieListBean;
import com.lepoint.ljfmvp.model.StoryListBean;
import com.lepoint.ljfmvp.model.TokenBean;
import com.lepoint.ljfmvp.model.UpdateBean;
import com.lepoint.ljfmvp.model.VideoDetailBean;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by admin on 2018/4/11.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("api")
    Flowable<BannerBean> queryHomeData(@Field("accessToken") String token,
                                       @Field("actionName") String actionName,
                                       @Field("payload") String payload,
                                       @Field("timestamp") long timaStamp,
                                       @Field("sign") String sign,
                                       @Field("from") String from);

    @FormUrlEncoded
    @POST("get-access-token")
    Flowable<TokenBean> getToken(@Field("mobileType") String type,
                                 @Field("mobileId") String mobileID);


    @FormUrlEncoded
    @POST("api")
    Flowable<ResponseBody> queryData(@Field("accessToken") String token,
                                     @Field("actionName") String actionName,
                                     @Field("payload") String payload,
                                     @Field("timestamp") long timaStamp,
                                     @Field("sign") String sign,
                                     @Field("from") String from);


    @GET("mobile/android/last-version")
    Flowable<UpdateBean> getUpdate(@Query("packageName") String name);

    //首页列表
    @GET("front/homePageApi/homePage.do?deviceId=863064010156927&appChannel=tengxun&userId=&appVersion=6.3.4&information=information")
    Flowable<HomeListBean> getHomeList();

    //获取首页分类列表
    @GET("front/homePage/channelList.do?information=information")
    Flowable<HomeChannelBean> getHomeChannel();

    //获取视频新详情列表
    @GET("front/videoDetailApi/videoDetail.do")
    Flowable<VideoDetailBean> getVideoDetail(@Query("mediaId") String mediaId, @Query("deviceId") String deviceId);

    //视频鉴权认证
    @GET("front/member/auth.do")
    Flowable<BaseModel> getVideoAuth(@Query("mediaId") String mediaId, @Query("deviceId") String deviceId);

    @FormUrlEncoded
    @POST("front/playRecordApi/add.do?deviceSysVersion=4.4.2&appVersion=6.3.4&deviceModel=Nexus%206&deviceManufacturer=motorola&deviceId=863064010156927&appId=shoujimovie&deviceSysType=ANDROID&appChannel=tengxun&appCode=634&userId=&locationId=11&mac=08:00:27:59:a5:f5&lat=&lng=")
    Flowable<BaseModel> addVideoList(@Field("mediaId") String mediaId);

    //获取发现的表头数据
    @GET("front/find/findMoviePage.do")
    Flowable<FindHeaderBean> getHeaderData();

    //获取发现的征文内容
    @GET("front/find/findPageInfoMsg.do")
    Flowable<FindNewsBean> getFindNews(@Query("pnum") int pnum);

    //获取视频列表
    @FormUrlEncoded
    @POST("front/columns/getVideoList.do?deviceSysVersion=4.4.2&appVersion=6.3.4&deviceModel=Nexus%206&deviceManufacturer=motorola&deviceId=863064010156927&appId=shoujimovie&deviceSysType=ANDROID&appChannel=tengxun&appCode=634&userId=&locationId=11&mac=08:00:27:59:a5:f5&lat=&lng=")
    Flowable<MovieListBean> getMovieList(@Field("catalogId") String dataId, @Field("information") String infomation, @Field("pnum") int pnum);


    //获取播放历史记录
    @FormUrlEncoded
    @POST("front/playRecordApi/loadRecordList.do")
    Flowable<HistoryListBean> getHistoryList(@Field("deviceId") String deviceId, @Field("pnum") int pageNum);


    //删除观影记录
    @FormUrlEncoded
    @POST("http://api.svipmovie.com/front/playRecordApi/deleteAll.do")
    Flowable<BaseModel> deleteHistory(@Field("deviceId") String deviceId);

    @GET("StoryBook/GetMagazine.ashx")
    Flowable<StoryListBean> getStoryList(@Query("MagazineType") int type, @Query("nowpage") int pnum);

}
