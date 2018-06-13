package com.lepoint.ljfmvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.MovieListAdapter;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.model.MovieListBean;
import com.lepoint.ljfmvp.present.MovieTypePresent;
import com.lepoint.ljfmvp.widget.autolayout.AutoRoundRelativielayout;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

public class MovieTypeActivity extends BaseActivity<MovieTypePresent> {

    @BindView(R.id.qm_topbar)
    QMUITopBar qmTopbar;
    @BindView(R.id.rv_movie_list)
    RecyclerView rvMovieList;
    @BindView(R.id.refresh_movie_list)
    SmartRefreshLayout refreshMovieList;
    @BindView(R.id.qmrl_home_seach)
    AutoRoundRelativielayout qmrlHomeSeach;
    private String dataID;
    private int pnum = 1;
    private String title;
    ArrayList<MovieListBean.RetBean.ListBean> dataList = new ArrayList();
    private MovieListAdapter movieAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        dataID = intent.getStringExtra("dataID");
        title = intent.getStringExtra("title");
        initView();
        initListener();
        getP().getMovieList(dataID, pnum);

    }

    private void initListener() {
        refreshMovieList.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pnum++;
                getP().getMovieList(dataID, pnum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pnum = 1;
                getP().getMovieList(dataID, pnum);
            }
        });

        movieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String Id = dataList.get(position).getDataId();
                Router.newIntent(context)
                        .to(VideoDetailActivity.class)
                        .putString("mediaID", Id)
                        .launch();
            }
        });


    }

    private void initView() {
        qmTopbar.setTitle(title);
        refreshMovieList.setPrimaryColorsId(R.color.colorPrimary);
        rvMovieList.setLayoutManager(new GridLayoutManager(context, 3));
        movieAdapter = new MovieListAdapter(R.layout.item_layout_intrest_layout, dataList, context);
        rvMovieList.setAdapter(movieAdapter);
    }

    public void setMovieData(List<MovieListBean.RetBean.ListBean> list) {
        refreshMovieList.finishLoadMore();
        refreshMovieList.finishRefresh();
        if (pnum == 1) {
            dataList.clear();
        }
        dataList.addAll(list);
        movieAdapter.notifyDataSetChanged();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_type;
    }

    @Override
    public MovieTypePresent newP() {
        return new MovieTypePresent();
    }

    @Override
    public void getNetData() {

    }


    @OnClick(R.id.qmrl_home_seach)
    public void onViewClicked() {
        Router.newIntent(context)
                .to(SeachActivity.class)
                .launch();
    }
}
