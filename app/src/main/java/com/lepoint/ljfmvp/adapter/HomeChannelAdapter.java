package com.lepoint.ljfmvp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.model.HomeChannelBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

public class HomeChannelAdapter extends BaseQuickAdapter<HomeChannelBean.RetBean.ColumnListBean, BaseViewHolder> {

    private Context context;

    public HomeChannelAdapter(int layoutResId, @Nullable List<HomeChannelBean.RetBean.ColumnListBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeChannelBean.RetBean.ColumnListBean item) {
        ImageView cover = (ImageView) helper.getView(R.id.tv_home_channel_cover);
        ILFactory.getLoader().loadNet(cover, item.getScriptPic(), null);
        helper.setText(R.id.tv_home_channel_title, item.getTitle());
    }
}
