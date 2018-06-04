package com.lepoint.ljfmvp.base;

import android.os.Bundle;
import android.view.View;

import com.lepoint.ljfmvp.R;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.IView;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;
import cn.droidlover.xdroidmvp.net.NetError;

/**
 * Created by Administrator on 2018/6/4 0004.
 */

public abstract class BaseLazyFragment<P extends IPresent> extends XLazyFragment<P> {
    protected QMUIEmptyView emptyView;

    @Override
    public void initData(Bundle savedInstanceState) {
        emptyView = (QMUIEmptyView) getRealRootView().findViewById(R.id.empty_loading_layout);
    }


    public void setRetryView(NetError error) {
        if (emptyView != null) {
            emptyView.show(false, error.getMessage(), null, "点击重试", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getNetData();
                }
            });
        }
    }


    public void hideLoading() {
        if (emptyView != null) {
            emptyView.hide();
        }
    }

    public abstract void getNetData();


}
