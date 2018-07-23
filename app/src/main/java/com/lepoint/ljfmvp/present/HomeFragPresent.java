package com.lepoint.ljfmvp.present;

import android.view.View;

import com.alibaba.fastjson.JSON;
import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.base.MyApp;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.RxUtils;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.HomeChannelBean;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.cache.DiskCache;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2018/4/12.
 */

public class HomeFragPresent extends BasePresent<HomeFragment> {

    /**
     * 获取首页列表信息
     */
    public void getHomeListData() {
        Flowable<HomeListBean> fromCache = RxUtils.rxCreateDiskObservable("homeList", HomeListBean.class);
        Flowable<HomeListBean> fromNetWork = RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getHomeList();
        Flowable<HomeListBean> concatFloawable = Flowable.concat(fromCache, fromNetWork);
        concatFloawable.compose(XApi.<HomeListBean>getApiTransformer())
                .compose(XApi.<HomeListBean>getScheduler())
                .compose(getV().<HomeListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<HomeListBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().refreshHome.finishRefresh();
                        getV().qmuiEmpty.show(false, error.getMessage(), null, "点击重试", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getHomeListData();
                                getHomeChannel();
                            }
                        });
                    }

                    @Override
                    protected void onSuccess(final HomeListBean homeListBean) {
                        getV().refreshHome.finishRefresh(1000);
                        if (homeListBean.getCode() == 200) {
                            Schedulers.io().createWorker().schedule(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        DiskCache.getInstance(MyApp.getContext()).put("homeList", JSON.toJSONString(homeListBean));
                                    } catch (Exception e) {

                                    }
                                }
                            });
                            getV().qmuiEmpty.show();
                            getV().refreshHome.setVisibility(View.VISIBLE);
                            HomeListBean.RetBean ret = homeListBean.getRet();
                            List<HomeListBean.RetBean.HotSearchListBean> hotSearchList = ret.getHotSearchList();
                            List<HomeListBean.RetBean.ListBean> list = ret.getList();
                            if (list != null && list.size() > 0) {
                                HomeListBean.RetBean.ListBean listBean = list.get(0); //banner
                                List<HomeListBean.RetBean.ListBean.ChildListBean> childList = listBean.getChildList();
                                ArrayList<String> bannerList = new ArrayList<>();
                                ArrayList<String> titleList = new ArrayList<>();
                                for (HomeListBean.RetBean.ListBean.ChildListBean bean : childList) {
                                    bannerList.add(bean.getPic());
                                    titleList.add(bean.getTitle());
                                }
                                getV().setBannerData(bannerList, titleList, childList);

                                for (HomeListBean.RetBean.ListBean bean : list) {
                                    switch (bean.getTitle()) {
                                        case "免费推荐":
                                            getV().setFreeRecommonedData(bean.getChildList());
                                            break;

                                        case "热点资讯":
                                            getV().setHotList(bean.getChildList());
                                            break;

                                        case "精彩推荐":
                                            getV().setIntrestList(bean.getChildList());
                                            break;

                                        case "大咖剧场":
                                            getV().setDkList(bean.getChildList());
                                            break;

                                        case "电影资讯":
                                            getV().setMovieNewsList(bean.getChildList());
                                            break;

                                        case "大片抢先看":
                                            getV().setDPList(bean.getChildList());
                                            break;

                                        case "微电影":
                                            getV().setNetList(bean.getChildList());
                                            break;

                                        case "香港映象":
                                            getV().setHKList(bean.getChildList());
                                            break;

                                        case "好莱坞":
                                            getV().setHlwList(bean.getChildList());
                                            break;
                                    }
                                }
                            }
                        }
                    }
                });
    }


    /**
     * 获取首页分类列表
     */
    public void getHomeChannel() {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getHomeChannel()
                .compose(XApi.<HomeChannelBean>getApiTransformer())
                .compose(XApi.<HomeChannelBean>getScheduler())
                .compose(getV().<HomeChannelBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<HomeChannelBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().refreshHome.finishRefresh();
                    }

                    @Override
                    protected void onSuccess(HomeChannelBean channelBean) {
                        //                        getV().refreshHome.finishRefresh();
                        if (channelBean.getCode() == 200) {
                            HomeChannelBean.RetBean ret = channelBean.getRet();
                            List<HomeChannelBean.RetBean.ColumnListBean> columnList = ret.getColumnList();
                            getV().setChannelListData(columnList);
                        }
                    }
                });
    }


}
