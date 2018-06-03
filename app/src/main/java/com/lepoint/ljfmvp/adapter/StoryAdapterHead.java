package com.lepoint.ljfmvp.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.model.StoryListBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Created by Administrator on 2018/6/3 0003.
 */

public class StoryAdapterHead extends BaseQuickAdapter<StoryListBean.BooklistBean, BaseViewHolder> {

    public StoryAdapterHead(int layoutResId, @Nullable List<StoryListBean.BooklistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoryListBean.BooklistBean item) {
        helper.setText(R.id.tv_story_title_up, item.getBookName());
        ImageView cover = (ImageView) helper.getView(R.id.iv_story_cover_up);
        ILFactory.getLoader().loadNet(cover, item.getBookImg(), null);
    }
}
