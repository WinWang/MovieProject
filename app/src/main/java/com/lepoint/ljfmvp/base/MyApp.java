package com.lepoint.ljfmvp.base;

import android.app.Application;
import android.content.Context;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.utils.CrashHandler;
import com.zhy.autolayout.config.AutoLayoutConifg;

import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by admin on 2018/4/12.
 */

public class MyApp extends Application {

    /**
     * 全局的上下文.
     */
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        AutoLayoutConifg.getInstance().useDeviceSize();
        registerProvider();
        //        LeakCanary.install(this);
        XDroidConf.devMode(false);
        XDroidConf.IL_ERROR_RES = R.mipmap.img_default;
        XDroidConf.IL_LOADING_RES = R.mipmap.img_default;
        XDroidConf.ROUTER_ANIM_ENTER = R.anim.slide_left_in;
        XDroidConf.ROUTER_ANIM_EXIT = R.anim.slide_left_out;
        //        XDroidConf.LOG = false;
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        CrashHandler.getInstance().init(this);

    }


    /**
     * 获取Context.
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 注册网络框架
     */
    private void registerProvider() {
        XApi.registerProvider(new NetProvider() {
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
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }

            @Override
            public boolean dispatchProgressEnable() {
                return false;
            }
        });
    }


}
