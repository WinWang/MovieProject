package com.lepoint.ljfmvp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.model.StoryBookList;
import com.lepoint.ljfmvp.model.StoryListBean;

import java.util.List;

public class BookListAdapter extends BaseQuickAdapter<StoryBookList.PartListBean, BaseViewHolder> {

    public BookListAdapter(int layoutResId, @Nullable List<StoryBookList.PartListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoryBookList.PartListBean item) {
        helper.setText(R.id.tv_story_part_list, item.getPartTitle());
    }
}
