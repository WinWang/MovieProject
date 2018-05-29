package com.lepoint.ljfmvp.http;

import com.lepoint.ljfmvp.model.BannerBean;
import com.lepoint.ljfmvp.model.HomeChannelBean;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.model.TokenBean;
import com.lepoint.ljfmvp.model.UpdateBean;

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


}
