package com.lepoint.ljfmvp.adapter;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.model.SeachListBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Created by admin on 2018/6/13.
 */

public class SeachNetAdapter extends BaseQuickAdapter<SeachListBean.RetBean.ListBean, BaseViewHolder> {

    public SeachNetAdapter(int layoutResId, @Nullable List<SeachListBean.RetBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SeachListBean.RetBean.ListBean item) {
        ImageView cover = (ImageView) helper.getView(R.id.iv_cover_middle_home);
        ILFactory.getLoader().loadNet(cover, item.getPic(), null);
        helper.setText(R.id.tv_title_middle, item.getTitle());
    }
}
