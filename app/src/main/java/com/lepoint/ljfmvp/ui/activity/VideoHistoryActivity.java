package com.lepoint.ljfmvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.MovieHistoryAdapter;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.model.HistoryListBean;
import com.lepoint.ljfmvp.present.VideoHistoryPresent;
import com.lepoint.ljfmvp.utils.DialogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

public class VideoHistoryActivity extends BaseActivity<VideoHistoryPresent> implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.refresh_history)
    SmartRefreshLayout refreshHistory;
    ArrayList<HistoryListBean.RetBean.ListBean> dataList = new ArrayList<>();
    private int pnum = 1;
    private MovieHistoryAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initListener();
    }

    private void initListener() {
        refreshHistory.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pnum++;
                getP().getHistoryData(pnum, context);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pnum = 1;
                getP().getHistoryData(pnum, context);
            }
        });
    }

    private void initView() {
        refreshHistory.setPrimaryColorsId(R.color.colorPrimary);
        refreshHistory.autoRefresh();
        topBar.setTitle("观看历史");
        topBar.addRightImageButton(R.mipmap.search_delete_nor, R.id.topbar_right_about_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showCustomDialog(context, "", "您确认删除观影记录？", new DialogUtil.DialogCallBack() {
                    @Override
                    public void onActionClick() {
                        getP().deleteHistory(context);
                    }
                });
            }
        });
        rvHistory.setLayoutManager(new GridLayoutManager(context, 3));
    }

    public void setHistoryData(List<HistoryListBean.RetBean.ListBean> list, boolean tag) {
        if (tag) {
            if (pnum == 1) {
                refreshHistory.finishRefresh();
                dataList.clear();
            } else {
                refreshHistory.finishLoadMore();
            }
            dataList.addAll(list);
            if (adapter == null) {
                adapter = new MovieHistoryAdapter(R.layout.item_layout_intrest_layout, dataList, context);
                View emptyView = View.inflate(context, R.layout.layout_empty_view, null);
                adapter.setEmptyView(emptyView);
                rvHistory.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
            } else {
                adapter.notifyDataSetChanged();
            }
        } else {
            refreshHistory.finishLoadMoreWithNoMoreData();
            getvDelegate().toastShort("没有更多数据了");
        }
    }


    public void deleteToast(String msg, boolean tag) {
        getvDelegate().toastShort(msg);
        if (tag) {
            dataList.clear();
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_video_history;
    }

    @Override
    public VideoHistoryPresent newP() {
        return new VideoHistoryPresent();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String dataId = dataList.get(position).getDataId();
        Router.newIntent(context).to(VideoDetailActivity.class).putString("mediaID", dataId).launch();
    }

    @Override
    public void getNetData() {
        getP().getHistoryData(pnum, context);
    }
}
