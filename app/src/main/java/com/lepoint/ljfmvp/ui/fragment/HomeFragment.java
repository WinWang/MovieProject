package com.lepoint.ljfmvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.BannerAdapter;
import com.lepoint.ljfmvp.adapter.HomeAdapter;
import com.lepoint.ljfmvp.adapter.HomeChannelAdapter;
import com.lepoint.ljfmvp.adapter.MovieAdapter;
import com.lepoint.ljfmvp.adapter.MovieNewsAdapter;
import com.lepoint.ljfmvp.model.HomeChannelBean;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.present.HomeFragPresent;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundRelativeLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;

/**
 * Created by admin on 2018/4/12.
 */

public class HomeFragment extends XLazyFragment<HomeFragPresent> implements OnRefreshListener {

    @BindView(R.id.rv_home_list)
    RecyclerView rvHomeList;
    @BindView(R.id.refresh_home)
    public SmartRefreshLayout refreshHome;
    @BindView(R.id.iv_home_seach)
    ImageView ivHomeSeach;
    @BindView(R.id.qmrl_home_seach)
    QMUIRoundRelativeLayout qmrlHomeSeach;
    @BindView(R.id.iv_home_history)
    ImageView ivHomeHistory;
    List dataList = new ArrayList<String>();
    List channelList = new ArrayList<HomeChannelBean.RetBean.ColumnListBean>();
    List recommendList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    List hotList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    List intrestList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    List dkList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    List movieNewsList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    private HomeAdapter homeAdapter;
    private Banner banner;
    private HomeChannelAdapter homeChannelAdapter;
    private MovieAdapter freeAdapter;
    private MovieAdapter hotNewsAdapter;
    private MovieAdapter intrestAdapter;
    private MovieAdapter dkAdapter;
    private MovieNewsAdapter movieNewsAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        getP().getHomeListData(context);
        getP().getHomeChannel(context);
    }

    private void initView() {
        refreshHome.setEnableLoadMore(false);
        refreshHome.setPrimaryColorsId(R.color.colorPrimary);
        refreshHome.setOnRefreshListener(this);
        rvHomeList.setLayoutManager(new LinearLayoutManager(context));
        homeAdapter = new HomeAdapter(R.layout.item_layout, dataList, context);
        rvHomeList.setAdapter(homeAdapter);

        //初始化头布局
        View header = View.inflate(context, R.layout.home_header_layout, null);
        homeAdapter.addHeaderView(header);
        //初始化banner
        banner = (Banner) header.findViewById(R.id.banner_home);
        banner.setImageLoader(new BannerAdapter());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //初始列表(频道)
        RecyclerView rvChannel = (RecyclerView) header.findViewById(R.id.rv_home_channel);
        rvChannel.setLayoutManager(new GridLayoutManager(context, 4));
        homeChannelAdapter = new HomeChannelAdapter(R.layout.item_home_channel_layout, channelList, context);
        rvChannel.setAdapter(homeChannelAdapter);

        //初始化免费推荐
        RecyclerView rvFreeRecommend = (RecyclerView) header.findViewById(R.id.rv_home_free_recommoned);
        rvFreeRecommend.setLayoutManager(new GridLayoutManager(context, 2));
        freeAdapter = new MovieAdapter(R.layout.item_layout, recommendList, null);
        rvFreeRecommend.setAdapter(freeAdapter);

        //初始化热点资讯
        RecyclerView rvHotNews = (RecyclerView) header.findViewById(R.id.rv_home_hot_news);
        rvHotNews.setLayoutManager(new GridLayoutManager(context, 2));
        hotNewsAdapter = new MovieAdapter(R.layout.item_layout, hotList, null);
        rvHotNews.setAdapter(hotNewsAdapter);


        //初始化精彩推荐
        RecyclerView rvIntrest = (RecyclerView) header.findViewById(R.id.rv_home_intrest_recommoned);
        rvIntrest.setLayoutManager(new GridLayoutManager(context, 3));
        intrestAdapter = new MovieAdapter(R.layout.item_layout_intrest_layout, intrestList, null);
        rvIntrest.setAdapter(intrestAdapter);

        //大咖
        RecyclerView rvDK = (RecyclerView) header.findViewById(R.id.rv_home_dk);
        rvDK.setLayoutManager(new GridLayoutManager(context, 3));
        dkAdapter = new MovieAdapter(R.layout.item_layout_intrest_layout, dkList, null);
        rvDK.setAdapter(dkAdapter);

        //电影资讯
        RecyclerView rvMovieNews = (RecyclerView) header.findViewById(R.id.rv_home_movie_news);
        rvMovieNews.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        movieNewsAdapter = new MovieNewsAdapter(R.layout.item_movie_news_layout, movieNewsList, context);
        rvMovieNews.setAdapter(movieNewsAdapter);
    }

    public void setBannerData(List<String> bannerData, List<String> titleData) {
        banner.setImages(bannerData);
        banner.setBannerTitles(titleData);
        banner.start();
    }

    public void setChannelListData(List<HomeChannelBean.RetBean.ColumnListBean> columnList) {
        channelList.clear();
        channelList.addAll(columnList);
        homeChannelAdapter.notifyDataSetChanged();
    }

    public void setFreeRecommonedData(List<HomeListBean.RetBean.ListBean.ChildListBean> freeList) {
        recommendList.clear();
        recommendList.addAll(freeList);
        freeAdapter.notifyDataSetChanged();
    }

    public void setHotList(List<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        hotList.clear();
        hotList.addAll(list);
        hotNewsAdapter.notifyDataSetChanged();
    }

    public void setIntrestList(List<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        intrestList.clear();
        intrestList.addAll(list);
        intrestAdapter.notifyDataSetChanged();
    }

    public void setDkList(List<HomeListBean.RetBean.ListBean.ChildListBean> list){
        dkList.clear();
        dkAdapter.removeAllHeaderView();
        View header = View.inflate(context, R.layout.header_movie_layout, null);
        ImageView bigImage = (ImageView) header.findViewById(R.id.iv_home_movie_header);
        TextView titleBig = (TextView) header.findViewById(R.id.tv_home_movie_title_header);
        ILFactory.getLoader().loadNet(bigImage,list.get(0).getPic(),null);
        titleBig.setText(list.get(0).getTitle());
        list.remove(0);
        dkAdapter.addHeaderView(header);
        dkList.addAll(list);
        dkAdapter.notifyDataSetChanged();

    }


    public void setMovieNewsList(List<HomeListBean.RetBean.ListBean.ChildListBean> list){
        movieNewsList.clear();
        movieNewsList.addAll(list);
        movieNewsAdapter.notifyDataSetChanged();
    }


    @Override
    public int getLayoutId() {
        return R.layout.frag_home_layout;
    }

    @Override
    public HomeFragPresent newP() {
        return new HomeFragPresent();
    }


    @OnClick({R.id.qmrl_home_seach, R.id.iv_home_history})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qmrl_home_seach://首页搜索

                break;
            case R.id.iv_home_history: //首页历史纪录

                break;
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getP().getHomeChannel(context);
        getP().getHomeListData(context);
    }
}
