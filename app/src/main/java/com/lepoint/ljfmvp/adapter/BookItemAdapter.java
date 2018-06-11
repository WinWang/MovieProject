package com.lepoint.ljfmvp.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.model.BookListBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

public class BookItemAdapter extends BaseQuickAdapter<BookListBean.DataBean.ListBean, BaseViewHolder> {

    public BookItemAdapter(int layoutResId, @Nullable List<BookListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookListBean.DataBean.ListBean item) {
        ImageView view = (ImageView) helper.getView(R.id.iv_cover_middle_home);
        ILFactory.getLoader().loadNet(view, item.getCover(), null);
        helper.setText(R.id.tv_title_middle, item.getName());
    }
}
