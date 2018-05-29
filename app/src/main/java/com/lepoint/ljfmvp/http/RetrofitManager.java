package com.lepoint.ljfmvp.http;

import cn.droidlover.xdroidmvp.net.XApi;

public class RetrofitManager {
    private static final String SOCKETTIMEOUTEXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECTEXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWNHOSTEXCEPTION = "网络异常，请检查您的网络状态";

    private ApiService apiService;

    public ApiService getApiService(String BASE_URL) {
        if (apiService == null) {
            synchronized (HttpUtils.class) {
                if (apiService == null) {
                    apiService = XApi.getInstance().getRetrofit(BASE_URL + "/", true).create(ApiService.class);
                }
            }
        }
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
}
