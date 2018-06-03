package com.lepoint.ljfmvp.adapter;

import android.media.Image;
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

public class StoryAdapterOuter extends BaseQuickAdapter<StoryListBean.BooklistBean, BaseViewHolder> {

    public StoryAdapterOuter(int layoutResId, @Nullable List<StoryListBean.BooklistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoryListBean.BooklistBean item) {
        ImageView cover = (ImageView) helper.getView(R.id.iv_cover_story_down);
        ILFactory.getLoader().loadNet(cover,item.getBookImg(),null);
        helper.setText(R.id.tv_title_story_down,item.getBookName());
        helper.setText(R.id.tv_time_story_down,item.getBookDateUp());
        helper.setText(R.id.tv_persion_story_down,"已有"+item.getBookReader()+"人阅读");
        helper.setText(R.id.tv_desc_story_down,item.getBookAbout());
    }
}
