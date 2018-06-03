package com.lepoint.ljfmvp.http;

import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/6/3 0003.
 */

public class NetProviderImpl implements NetProvider {
    @Override
    public Interceptor[] configInterceptors() {
        return new Interceptor[0];
    }

    @Override
    public void configHttps(OkHttpClient.Builder builder) {

    }

    @Override
    public CookieJar configCookie() {
        return null;
    }

    @Override
    public RequestHandler configHandler() {
        return null;
    }

    @Override
    public long configConnectTimeoutMills() {
        return 0;
    }

    @Override
    public long configReadTimeoutMills() {
        return 0;
    }

    @Override
    public boolean configLogEnable() {
        return false;
    }

    @Override
    public boolean handleError(NetError error) {
        return false;
    }

    @Override
    public boolean dispatchProgressEnable() {
        return false;
    }
}
