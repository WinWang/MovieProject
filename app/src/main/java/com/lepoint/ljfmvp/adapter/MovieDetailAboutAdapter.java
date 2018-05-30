package com.lepoint.ljfmvp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.model.VideoDetailBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

public class MovieDetailAboutAdapter extends BaseQuickAdapter<VideoDetailBean.RetBean.ListBean.ChildListBean, BaseViewHolder> {

    private Context context;

    public MovieDetailAboutAdapter(int layoutResId, @Nullable List<VideoDetailBean.RetBean.ListBean.ChildListBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, VideoDetailBean.RetBean.ListBean.ChildListBean item) {
        ImageView cover = (ImageView) helper.getView(R.id.iv_cover_video_about);
        ILFactory.getLoader().loadNet(cover, item.getPic(), null);
        helper.setText(R.id.tv_title_video_about, item.getTitle());
        helper.setText(R.id.tv_title_video_time, "时长: " + item.getDuration());
    }
}
