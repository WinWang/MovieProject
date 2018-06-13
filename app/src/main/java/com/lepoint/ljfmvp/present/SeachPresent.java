package com.lepoint.ljfmvp.present;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.SeachListBean;
import com.lepoint.ljfmvp.ui.activity.SeachActivity;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by admin on 2018/6/13.
 */

public class SeachPresent extends BasePresent<SeachActivity> {

    public void getSeachData(String textData) {
        RetrofitManager.getInstance()
                .getApiService(URLConfig.BASE_MOVIE_URL)
                .querySeach(textData)
                .compose(XApi.<SeachListBean>getApiTransformer())
                .compose(XApi.<SeachListBean>getScheduler())
                .compose(getV().<SeachListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<SeachListBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(SeachListBean seachListBean) {
                        List<SeachListBean.RetBean.ListBean> list = seachListBean.getRet().getList();
                        if (list != null && list.size() > 0) {
                            getV().setData(list);
                        }
                    }
                });
    }

}
