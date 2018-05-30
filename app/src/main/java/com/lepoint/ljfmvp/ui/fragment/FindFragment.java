package com.lepoint.ljfmvp.ui.fragment;

import android.os.Bundle;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.present.FindPresent;

import cn.droidlover.xdroidmvp.mvp.XLazyFragment;

public class FindFragment extends XLazyFragment<FindPresent> {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_find_layout;
    }

    @Override
    public FindPresent newP() {
        return new FindPresent();
    }
}
