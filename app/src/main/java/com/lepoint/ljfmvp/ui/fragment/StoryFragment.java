package com.lepoint.ljfmvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.StoryAdapterHead;
import com.lepoint.ljfmvp.adapter.StoryAdapterOuter;
import com.lepoint.ljfmvp.base.BaseLazyFragment;
import com.lepoint.ljfmvp.model.StoryListBean;
import com.lepoint.ljfmvp.present.StoryPresent;
import com.lepoint.ljfmvp.ui.activity.StoryListActivity;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Administrator on 2018/6/3 0003.
 */

public class StoryFragment extends BaseLazyFragment<StoryPresent> {
    //    @BindView(R.id.empty_loading_layout)
//    public QMUIEmptyView emptyLoadingLayout;
    @BindView(R.id.rv_story)
    RecyclerView rvStory;
    @BindView(R.id.refresh_story)
    public SmartRefreshLayout refreshStory;
    ArrayList<StoryListBean.BooklistBean> dataList = new ArrayList<>();
    ArrayList<StoryListBean.BooklistBean> HeadList = new ArrayList<>();
    StoryAdapterOuter storyAdapterOuter = null;
    StoryAdapterHead headAdapter;
    private int pageNum = 1;
    private boolean tag = true;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initListener();
        getNetData();
    }

    @Override
    public void getNetData() {
        getP().getStoryList(pageNum);
        getP().getStoryListHead();
    }

    private void initListener() {
        refreshStory.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                tag = false;
                pageNum++;
                getP().getStoryList(pageNum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                tag = true;
                pageNum = 1;
                getP().getStoryList(pageNum);
                getP().getStoryListHead();
            }
        });

        headAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Router.newIntent(context)
                        .putInt("bookID", HeadList.get(position).getBookId())
                        .putString("title", HeadList.get(position).getBookName())
                        .to(StoryListActivity.class)
                        .launch();
            }
        });


        storyAdapterOuter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Router.newIntent(context)
                        .putInt("bookID", dataList.get(position).getBookId())
                        .putString("title", dataList.get(position).getBookName())
                        .to(StoryListActivity.class)
                        .launch();
            }
        });

    }

    private void initView() {
        refreshStory.setPrimaryColorsId(R.color.colorPrimary);
        rvStory.setLayoutManager(new LinearLayoutManager(context));
        storyAdapterOuter = new StoryAdapterOuter(R.layout.item_story_down_layout, dataList);
        View head = View.inflate(context, R.layout.header_story_up, null);
        RecyclerView rvHead = (RecyclerView) head.findViewById(R.id.rv_story_head);
        rvHead.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        headAdapter = new StoryAdapterHead(R.layout.item_story_up_layout, HeadList);
        rvHead.setAdapter(headAdapter);
        storyAdapterOuter.addHeaderView(head);
        rvStory.setAdapter(storyAdapterOuter);
    }

    public void setDataList(List<StoryListBean.BooklistBean> list) {
        if (tag) {
            refreshStory.finishRefresh(1000);
            dataList.clear();
        } else {
            refreshStory.finishLoadMore();
        }
        dataList.addAll(list);
        storyAdapterOuter.notifyDataSetChanged();
    }

    public void setHeadList(List<StoryListBean.BooklistBean> list) {
        HeadList.clear();
        HeadList.addAll(list);
        headAdapter.notifyDataSetChanged();

    }


    @Override
    public int getLayoutId() {
        return R.layout.frag_story_layout;
    }

    @Override
    public StoryPresent newP() {
        return new StoryPresent();
    }
}
