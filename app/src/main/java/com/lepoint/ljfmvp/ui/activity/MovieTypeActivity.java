package com.lepoint.ljfmvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.MovieAdapter;
import com.lepoint.ljfmvp.adapter.MovieListAdapter;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.model.MovieListBean;
import com.lepoint.ljfmvp.present.MovieTypePresent;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieTypeActivity extends BaseActivity<MovieTypePresent> {

    @BindView(R.id.qm_topbar)
    QMUITopBar qmTopbar;
    @BindView(R.id.rv_movie_list)
    RecyclerView rvMovieList;
    @BindView(R.id.refresh_movie_list)
    SmartRefreshLayout refreshMovieList;
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
        getP().getMovieList(dataID, pnum);

    }

    private void initView() {
        qmTopbar.setTitle(title);
        refreshMovieList.setPrimaryColorsId(R.color.colorPrimary);
        rvMovieList.setLayoutManager(new GridLayoutManager(context, 3));
        movieAdapter = new MovieListAdapter(R.layout.item_layout_intrest_layout, dataList, context);
        rvMovieList.setAdapter(movieAdapter);
    }

    public void setMovieData(List<MovieListBean.RetBean.ListBean> list ){
        dataList.clear();
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

}
