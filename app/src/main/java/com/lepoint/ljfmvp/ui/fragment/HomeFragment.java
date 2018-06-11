package com.lepoint.ljfmvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.BannerAdapter;
import com.lepoint.ljfmvp.adapter.HomeAdapter;
import com.lepoint.ljfmvp.adapter.HomeChannelAdapter;
import com.lepoint.ljfmvp.adapter.MovieAdapter;
import com.lepoint.ljfmvp.adapter.MovieNewsAdapter;
import com.lepoint.ljfmvp.model.HomeChannelBean;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.present.HomeFragPresent;
import com.lepoint.ljfmvp.ui.activity.MovieTypeActivity;
import com.lepoint.ljfmvp.ui.activity.NewsActivity;
import com.lepoint.ljfmvp.ui.activity.SeachActivity;
import com.lepoint.ljfmvp.ui.activity.VideoDetailActivity;
import com.lepoint.ljfmvp.ui.activity.VideoHistoryActivity;
import com.lepoint.ljfmvp.widget.autolayout.AutoRoundRelativielayout;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;
import cn.droidlover.xdroidmvp.router.Router;

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
    AutoRoundRelativielayout qmrlHomeSeach;
    @BindView(R.id.iv_home_history)
    ImageView ivHomeHistory;
    List dataList = new ArrayList<String>();
    ArrayList<HomeChannelBean.RetBean.ColumnListBean> channelList = new ArrayList<HomeChannelBean.RetBean.ColumnListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> recommendList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> hotList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> intrestList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> dkList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> movieNewsList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> movieDP = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> hkList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> hlwList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> netList = new ArrayList<HomeListBean.RetBean.ListBean.ChildListBean>();
    List<HomeListBean.RetBean.ListBean.ChildListBean> bannerList = null;
    @BindView(R.id.qmui_empty)
    public QMUIEmptyView qmuiEmpty;
    private HomeAdapter homeAdapter;
    private Banner banner;
    private HomeChannelAdapter homeChannelAdapter;
    private MovieAdapter freeAdapter;
    private MovieAdapter hotNewsAdapter;
    private MovieAdapter intrestAdapter;
    private MovieAdapter dkAdapter;
    private MovieNewsAdapter movieNewsAdapter;
    private MovieAdapter dpAdapter;
    private MovieAdapter hkAdapter;
    private MovieAdapter netAdapter;
    private MovieAdapter hlwAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initListener();
        getP().getHomeListData();
        getP().getHomeChannel();
    }

    private void initView() {
        refreshHome.setEnableLoadMore(false);
        refreshHome.setPrimaryColorsId(R.color.colorPrimary);
        refreshHome.setOnRefreshListener(this);
        rvHomeList.setLayoutManager(new LinearLayoutManager(context));
        homeAdapter = new HomeAdapter(R.layout.item_layout, dataList, context);
        rvHomeList.setAdapter(homeAdapter);
        rvHomeList.setFocusableInTouchMode(false);
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
        rvMovieNews.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        movieNewsAdapter = new MovieNewsAdapter(R.layout.item_movie_news_layout, movieNewsList, context);
        rvMovieNews.setAdapter(movieNewsAdapter);

        //大片抢先看
        RecyclerView rvDP = (RecyclerView) header.findViewById(R.id.rv_home_movie_qx);
        rvDP.setLayoutManager(new GridLayoutManager(context, 3));
        dpAdapter = new MovieAdapter(R.layout.item_layout_intrest_layout, movieDP, null);
        rvDP.setAdapter(dpAdapter);


        //微电影
        RecyclerView rvNet = (RecyclerView) header.findViewById(R.id.rv_home_movie_net);
        rvNet.setLayoutManager(new GridLayoutManager(context, 3));
        netAdapter = new MovieAdapter(R.layout.item_layout_intrest_layout, netList, null);
        rvNet.setAdapter(netAdapter);

        //香港映像
        RecyclerView rvHK = (RecyclerView) header.findViewById(R.id.rv_home_movie_hongkong);
        rvHK.setLayoutManager(new GridLayoutManager(context, 3));
        hkAdapter = new MovieAdapter(R.layout.item_layout_intrest_layout, hkList, null);
        rvHK.setAdapter(hkAdapter);

        //好莱坞
        RecyclerView rvHlw = (RecyclerView) header.findViewById(R.id.rv_home_movie_hlw);
        rvHlw.setLayoutManager(new GridLayoutManager(context, 3));
        hlwAdapter = new MovieAdapter(R.layout.item_layout_intrest_layout, hlwList, null);
        rvHlw.setAdapter(hlwAdapter);


    }

    private void initListener() {
        setonItemClick(freeAdapter, recommendList);
        setonItemClick(hotNewsAdapter, hotList);
        setonItemClick(intrestAdapter, intrestList);
        setonItemClick(dkAdapter, dkList);
        setonItemClick(dpAdapter, movieDP);
        setonItemClick(netAdapter, netList);
        setonItemClick(hkAdapter, hkList);
        setonItemClick(hlwAdapter, hlwList);
        movieNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String dataId = movieNewsList.get(position).getDataId();
                jumpMethod(dataId);
            }
        });

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String dataId = bannerList.get(position).getDataId();
                if (TextUtils.isEmpty(dataId)) {
                    getvDelegate().toastShort("暂不可用");
                } else {
                    jumpMethod(dataId);
                }
            }
        });

        homeChannelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String dataId = channelList.get(position).getDataId();
                String title = channelList.get(position).getTitle();
                if (!TextUtils.isEmpty(dataId)) {
                    Router.newIntent(context).to(MovieTypeActivity.class)
                            .putString("dataID", dataId)
                            .putString("title", title)
                            .launch();
                } else {
                    getvDelegate().toastShort("暂不支持，敬请期待");
                }
            }
        });

        movieNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String loadURL = movieNewsList.get(position).getDataId();
                String title = movieNewsList.get(position).getTitle();
                Router.newIntent(context).putString("title", title).putString("dataID", loadURL).to(NewsActivity.class).launch();
            }
        });


    }

    private void setonItemClick(MovieAdapter adapter, final ArrayList<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String dataId = list.get(position).getDataId();
                jumpMethod(dataId);
            }
        });
    }

    private void jumpMethod(String mediaID) {
        Router.newIntent(context)
                .to(VideoDetailActivity.class)
                .putString("mediaID", mediaID)
                .launch();
    }

    public void setBannerData(List<String> bannerData, List<String> titleData, List<HomeListBean.RetBean.ListBean.ChildListBean> bannerList) {
        this.bannerList = bannerList;
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

    public void setDkList(List<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        dkList.clear();
        if (list.size() >= 7) {
            dkAdapter.removeAllHeaderView();
            View header = View.inflate(context, R.layout.header_movie_layout, null);
            ImageView bigImage = (ImageView) header.findViewById(R.id.iv_home_movie_header);
            TextView titleBig = (TextView) header.findViewById(R.id.tv_home_movie_title_header);
            ILFactory.getLoader().loadNet(bigImage, list.get(0).getPic(), null);
            titleBig.setText(list.get(0).getTitle());
            setBigImgClick(bigImage, list.get(0).getDataId());
            list.remove(0);
            dkAdapter.addHeaderView(header);
        }
        dkList.addAll(list);
        dkAdapter.notifyDataSetChanged();

    }


    public void setMovieNewsList(List<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        movieNewsList.clear();
        movieNewsList.addAll(list);
        movieNewsAdapter.notifyDataSetChanged();
    }


    public void setDPList(List<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        movieDP.clear();
        if (list.size() >= 7) {
            dpAdapter.removeAllHeaderView();
            View header = View.inflate(context, R.layout.header_movie_layout, null);
            ImageView bigImage = (ImageView) header.findViewById(R.id.iv_home_movie_header);
            TextView titleBig = (TextView) header.findViewById(R.id.tv_home_movie_title_header);
            ILFactory.getLoader().loadNet(bigImage, list.get(0).getPic(), null);
            titleBig.setText(list.get(0).getTitle());
            setBigImgClick(bigImage, list.get(0).getDataId());
            list.remove(0);
            dpAdapter.addHeaderView(header);
        }
        movieDP.addAll(list);
        dpAdapter.notifyDataSetChanged();

    }


    public void setNetList(List<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        netList.clear();
        if (list.size() >= 7) {
            netAdapter.removeAllHeaderView();
            View header = View.inflate(context, R.layout.header_movie_layout, null);
            ImageView bigImage = (ImageView) header.findViewById(R.id.iv_home_movie_header);
            TextView titleBig = (TextView) header.findViewById(R.id.tv_home_movie_title_header);
            ILFactory.getLoader().loadNet(bigImage, list.get(0).getPic(), null);
            titleBig.setText(list.get(0).getTitle());
            setBigImgClick(bigImage, list.get(0).getDataId());
            list.remove(0);
            netAdapter.addHeaderView(header);
        }
        netList.addAll(list);
        netAdapter.notifyDataSetChanged();

    }

    public void setHKList(List<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        hkList.clear();
        if (list.size() >= 7) {
            hkAdapter.removeAllHeaderView();
            View header = View.inflate(context, R.layout.header_movie_layout, null);
            ImageView bigImage = (ImageView) header.findViewById(R.id.iv_home_movie_header);
            TextView titleBig = (TextView) header.findViewById(R.id.tv_home_movie_title_header);
            ILFactory.getLoader().loadNet(bigImage, list.get(0).getPic(), null);
            titleBig.setText(list.get(0).getTitle());
            setBigImgClick(bigImage, list.get(0).getDataId());
            list.remove(0);
            hkAdapter.addHeaderView(header);
        }
        hkList.addAll(list);
        hkAdapter.notifyDataSetChanged();
    }

    public void setHlwList(List<HomeListBean.RetBean.ListBean.ChildListBean> list) {
        hlwList.clear();
        if (list.size() >= 7) {
            hlwAdapter.removeAllHeaderView();
            View header = View.inflate(context, R.layout.header_movie_layout, null);
            ImageView bigImage = (ImageView) header.findViewById(R.id.iv_home_movie_header);
            TextView titleBig = (TextView) header.findViewById(R.id.tv_home_movie_title_header);
            ILFactory.getLoader().loadNet(bigImage, list.get(0).getPic(), null);
            titleBig.setText(list.get(0).getTitle());
            setBigImgClick(bigImage, list.get(0).getDataId());
            list.remove(0);
            hlwAdapter.addHeaderView(header);
        }
        hlwList.addAll(list);
        hlwAdapter.notifyDataSetChanged();

    }

    private void setBigImgClick(ImageView img, final String dataId) {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpMethod(dataId);
            }
        });
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
                Router.newIntent(context).to(SeachActivity.class).launch();
                break;
            case R.id.iv_home_history: //首页历史纪录
                Router.newIntent(context).to(VideoHistoryActivity.class).launch();
                break;
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getP().getHomeChannel();
        getP().getHomeListData();
    }

}
