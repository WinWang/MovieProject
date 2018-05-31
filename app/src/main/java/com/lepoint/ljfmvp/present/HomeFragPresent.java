package com.lepoint.ljfmvp.present;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.HttpUtils;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.HomeChannelBean;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.model.UpdateBean;
import com.lepoint.ljfmvp.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by admin on 2018/4/12.
 */

public class HomeFragPresent extends BasePresent<HomeFragment> {

    /**
     * 获取首页列表信息
     *
     * @param context
     */
    public void getHomeListData(Context context) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getHomeList()
                .compose(XApi.<HomeListBean>getApiTransformer())
                .compose(XApi.<HomeListBean>getScheduler())
                .compose(getV().<HomeListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<HomeListBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().refreshHome.finishRefresh();
                    }

                    @Override
                    protected void onSuccess(HomeListBean homeListBean) {
                        getV().refreshHome.finishRefresh();
                        if (homeListBean.getCode() == 200) {
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
                                getV().setBannerData(bannerList, titleList,childList);
                                //免费推荐
                                List<HomeListBean.RetBean.ListBean.ChildListBean> freeList = list.get(1).getChildList();
                                getV().setFreeRecommonedData(freeList);

                                //热点资讯
                                List<HomeListBean.RetBean.ListBean.ChildListBean> hotList = list.get(2).getChildList();
                                getV().setHotList(hotList);

                                //精彩推荐
                                List<HomeListBean.RetBean.ListBean.ChildListBean> intrestList = list.get(3).getChildList();
                                getV().setIntrestList(intrestList);

                                //大咖剧场
                                List<HomeListBean.RetBean.ListBean.ChildListBean> dkList = list.get(5).getChildList();
                                getV().setDkList(dkList);

                                //电影资讯
                                List<HomeListBean.RetBean.ListBean.ChildListBean> movieNewsList = list.get(6).getChildList();
                                getV().setMovieNewsList(movieNewsList);

                                //大片抢先看
                                List<HomeListBean.RetBean.ListBean.ChildListBean> dpMovieList = list.get(7).getChildList();
                                getV().setDPList(dpMovieList);

                                //微电影
                                List<HomeListBean.RetBean.ListBean.ChildListBean> netList = list.get(8).getChildList();
                                getV().setNetList(netList);

                                //香港映像
                                List<HomeListBean.RetBean.ListBean.ChildListBean> hkList = list.get(9).getChildList();
                                getV().setHKList(hkList);

                                //好莱坞
                                List<HomeListBean.RetBean.ListBean.ChildListBean> hlwList = list.get(10).getChildList();
                                getV().setHlwList(hlwList);

                            }
                        }
                    }
                });
    }


    /**
     * 获取首页分类列表
     *
     * @param context
     */
    public void getHomeChannel(Context context) {
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
                        getV().refreshHome.finishRefresh();
                        if (channelBean.getCode() == 200) {
                            HomeChannelBean.RetBean ret = channelBean.getRet();
                            List<HomeChannelBean.RetBean.ColumnListBean> columnList = ret.getColumnList();
                            getV().setChannelListData(columnList);
                        }
                    }
                });
    }


}
