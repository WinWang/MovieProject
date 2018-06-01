package com.lepoint.ljfmvp.present;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.BaseModel;
import com.lepoint.ljfmvp.model.HistoryListBean;
import com.lepoint.ljfmvp.model.MovieListBean;
import com.lepoint.ljfmvp.ui.activity.VideoHistoryActivity;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class VideoHistoryPresent extends BasePresent<VideoHistoryActivity> {
    public void getHistoryData(final int pageNum) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getHistoryList("863064010156927", pageNum)
                .compose(XApi.<HistoryListBean>getApiTransformer())
                .compose(XApi.<HistoryListBean>getScheduler())
                .compose(getV().<HistoryListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<HistoryListBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(HistoryListBean historyListBean) {
                        if (historyListBean.getCode() == 200) {
                            HistoryListBean.RetBean ret = historyListBean.getRet();
                            List<HistoryListBean.RetBean.ListBean> list = ret.getList();
                            int pnum = ret.getPnum();
                            if (pnum != pageNum) {
                                getV().setHistoryData(list, false);
                            } else {
                                getV().setHistoryData(list, true);
                            }
                        }
                    }
                });
    }


    public void deleteHistory() {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).deleteHistory("863064010156927")
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(BaseModel historyListBean) {
                        if (historyListBean.getCode() == 200) {
                            getV().deleteToast(historyListBean.getMsg(), true);
                        } else {
                            getV().deleteToast(historyListBean.getMsg(), false);
                        }
                    }
                });
    }

}
