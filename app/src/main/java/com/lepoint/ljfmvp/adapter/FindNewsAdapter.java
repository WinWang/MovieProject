package com.lepoint.ljfmvp.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.model.FindNewsBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

public class FindNewsAdapter extends BaseQuickAdapter<FindNewsBean.RetBean.FindListBean, BaseViewHolder> {

    public FindNewsAdapter(int layoutResId, @Nullable List<FindNewsBean.RetBean.FindListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindNewsBean.RetBean.FindListBean item) {
        ImageView cover = (ImageView) helper.getView(R.id.iv_find_news_cover);
        ILFactory.getLoader().loadNet(cover, item.getPic(), null);
        helper.setText(R.id.tv_find_news_title, item.getTitle());
        helper.setText(R.id.tv_find_news_desc, item.getDes());
    }
}
