package com.lepoint.ljfmvp.present;

import android.view.View;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.RetryForToken;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.FindHeaderBean;
import com.lepoint.ljfmvp.model.FindNewsBean;
import com.lepoint.ljfmvp.ui.fragment.FindFragment;
import com.lepoint.ljfmvp.utils.ToastUtil;


import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class FindPresent extends BasePresent<FindFragment> {

    /**
     * 该方法用来测试token跟新
     */
    public void getHeaderData() {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getHeaderData()
                .compose(XApi.<FindHeaderBean>getApiTransformer())
                .compose(XApi.<FindHeaderBean>getScheduler())
                .compose(getV().<FindHeaderBean>bindToLifecycle())
                //此处对数据做预判断
//                .flatMap(new Function<FindHeaderBean, Flowable<? extends FindHeaderBean>>() {
//                    @Override
//                    public Flowable<? extends FindHeaderBean> apply(@NonNull FindHeaderBean response) throws Exception {
//                        // 判断token是否过期
//                        if (response.getCode() == 200) {
//                            return Flowable.error(new TokenError());
//                        }
//                        return Flowable.just(response);
//                    }
//                })
                /**
                 * 此种方法是写在单个请求里面
                 */
//                .retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() {
//                    @Override
//                    public Publisher<?> apply(Flowable<Throwable> throwableFlowable) throws Exception {
//                        return throwableFlowable.flatMap(new Function<Throwable, Publisher<?>>() {
//                            @Override
//                            public Publisher<?> apply(@NonNull Throwable throwable) throws Exception {
//                                if (throwable instanceof NetError && ((NetError) throwable).getType() == 2) {
//                                    // 如果上面检测到token过期就会进入到这里
//                                    // 然后下面的方法就是更新token
//                                    return RetrofitManager.getInstance().getApiService(URLConfig.BASE_API_URL).getToken("1", "234234")
//                                            .compose(XApi.<TokenBean>getScheduler())
//                                            .doOnNext(new Consumer<TokenBean>() {
//                                                @Override
//                                                public void accept(TokenBean tokenBean) throws Exception {
//                                                    getV().getvDelegate().toastShort(tokenBean.getAccessToken());
//                                                }
//                                            });
//                                }
//                                // 如果是其他错误则会调用到observer的onError方法中
//                                return Flowable.error(throwable);
//                            }
//                        });
//                    }
//                })
                .retryWhen(new RetryForToken(getV()))
                .subscribe(new ApiSubscriber<FindHeaderBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(error.getMessage());
                    }

                    @Override
                    protected void onSuccess(FindHeaderBean findHeaderBean) {
                        if (findHeaderBean.getCode() == 200) {
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
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(FindNewsBean findNewsBean) {
                        if (findNewsBean.getCode() == 200) {
                            getV().hideLoading();
                            getV().refreshFindLayout.setVisibility(View.VISIBLE);
                            List<FindNewsBean.RetBean.FindListBean> findList = findNewsBean.getRet().getFindList();
                            getV().setFindListData(findList);
                        }
                    }
                });
    }
}
