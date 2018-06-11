package com.lepoint.ljfmvp.base;

import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.FindHeaderBean;

import cn.droidlover.xdroidmvp.mvp.IView;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by admin on 2018/4/24.
 */

public class BasePresent<V extends IView> extends XPresent<V> {

    public void refreshToken() {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getHeaderData()
                .compose(XApi.<FindHeaderBean>getApiTransformer())
                .compose(XApi.<FindHeaderBean>getScheduler())
                .subscribe(new ApiSubscriber<FindHeaderBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(FindHeaderBean findHeaderBean) {

                    }
                });
    }

}
