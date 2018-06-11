package com.lepoint.ljfmvp.http;

import android.net.Uri;

import com.lepoint.ljfmvp.model.BaseModel;

import org.reactivestreams.Publisher;

import cn.droidlover.xdroidmvp.net.IModel;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RetrofitManager {
    private static final String SOCKETTIMEOUTEXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECTEXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWNHOSTEXCEPTION = "网络异常，请检查您的网络状态";

    private ApiService apiService;

    public ApiService getApiService(String BASE_URL) {
        apiService = XApi.getInstance().getRetrofit(BASE_URL + "/", true).create(ApiService.class);
        return apiService;
    }

    /**
     * HttpUtil实例
     */
    private static RetrofitManager INSTANCE;

    /**
     * 获取HttpUtil实例 ,单例模式
     */
    public static RetrofitManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitManager();
                }
            }
        }
        return INSTANCE;
    }

    public <T extends IModel> FlowableTransformer<T, T> refreshToken() {

        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.flatMap(new Function<T, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(T model) throws Exception {

                        return null;
                    }
                });
            }
        };
    }

}
