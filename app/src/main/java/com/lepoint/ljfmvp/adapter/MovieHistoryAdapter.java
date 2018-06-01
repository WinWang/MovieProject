package com.lepoint.ljfmvp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.model.HistoryListBean;
import com.lepoint.ljfmvp.model.HomeListBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

public class MovieHistoryAdapter extends BaseQuickAdapter<HistoryListBean.RetBean.ListBean, BaseViewHolder> {

    private Context context;

    public MovieHistoryAdapter(int layoutResId, @Nullable List<HistoryListBean.RetBean.ListBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryListBean.RetBean.ListBean item) {
        ImageView cover = (ImageView) helper.getView(R.id.iv_cover_middle_home);
        ILFactory.getLoader().loadNet(cover, item.getPic(), null);
        helper.setText(R.id.tv_title_middle, item.getTitle());
    }
}
