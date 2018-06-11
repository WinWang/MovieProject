package com.lepoint.ljfmvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.BookItemAdapter;
import com.lepoint.ljfmvp.base.BaseLazyFragment;
import com.lepoint.ljfmvp.model.BookListBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookItemFragment extends BaseLazyFragment {

    @BindView(R.id.rv_book_item)
    RecyclerView rvBookItem;

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        ArrayList<BookListBean.DataBean.ListBean> bookList = arguments.getParcelableArrayList("bookList");
        rvBookItem.setLayoutManager(new GridLayoutManager(context, 3));
        BookItemAdapter bookItemAdapter = new BookItemAdapter(R.layout.item_layout_intrest_layout, bookList);
        rvBookItem.setAdapter(bookItemAdapter);
        emptyView.hide();
    }

    @Override
    public void getNetData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_frag_book_layout;
    }

    @Override
    public Object newP() {
        return null;
    }
}
