package com.lepoint.ljfmvp.present;

import android.view.View;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.FindHeaderBean;
import com.lepoint.ljfmvp.model.FindNewsBean;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.ui.fragment.FindFragment;

import org.reactivestreams.Publisher;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FindPresent extends BasePresent<FindFragment> {

    public void getHeaderData() {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getHeaderData()
                .compose(XApi.<FindHeaderBean>getApiTransformer())
                .compose(XApi.<FindHeaderBean>getScheduler())
                .compose(getV().<FindHeaderBean>bindToLifecycle())
                .retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(Flowable<Throwable> throwableFlowable) throws Exception {
                        return null;
                    }
                })
                .subscribe(new ApiSubscriber<FindHeaderBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(FindHeaderBean findHeaderBean) {
                        if (findHeaderBean.getCode() == 200) {
                            getV().emptyLoadingLayout.hide();
                            getV().refreshFindLayout.setVisibility(View.VISIBLE);
                            FindHeaderBean.RetBean ret = findHeaderBean.getRet();
                            List<FindHeaderBean.RetBean.BannerListBean> bannerList = ret.getBannerList();
                            if (bannerList != null && bannerList.size() > 0) {
                                getV().setBannerData(bannerList);
                            }
                        }
                    }
                });
    }

    public void getFindNewsData(int pageNum) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getFindNews(pageNum)
                .compose(XApi.<FindNewsBean>getApiTransformer())
                .compose(XApi.<FindNewsBean>getScheduler())
                .compose(getV().<FindNewsBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<FindNewsBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(FindNewsBean findNewsBean) {
                        if (findNewsBean.getCode() == 200) {
                            List<FindNewsBean.RetBean.FindListBean> findList = findNewsBean.getRet().getFindList();
                            getV().setFindListData(findList);
                        }
                    }
                });
    }


}
