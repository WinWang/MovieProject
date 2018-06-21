package com.lepoint.ljfmvp.present;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.NewsDetailBean;
import com.lepoint.ljfmvp.ui.activity.NewsActivity;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class NewsPresent extends BasePresent<NewsActivity> {
    public void getNetData(String dataId) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL)
                .getNewsDetail(dataId)
                .compose(XApi.<NewsDetailBean>getApiTransformer())
                .compose(XApi.<NewsDetailBean>getScheduler())
                .compose(getV().<NewsDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<NewsDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(NewsDetailBean newsDetailBean) {
                        getV().setWebNews(newsDetailBean.getRet().getUrl());
                    }
                });
    }
}
