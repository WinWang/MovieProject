package com.lepoint.ljfmvp.http;


import android.app.Activity;
import android.support.annotation.NonNull;

import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.base.BaseLazyFragment;
import com.lepoint.ljfmvp.model.TokenBean;

import org.reactivestreams.Publisher;

import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * TOken重试RetryWhen机制
 */
public class RetryForToken implements Function<Flowable<? extends Throwable>, Flowable<?>> {


    BaseLazyFragment lazyFragment;
    BaseActivity activity;

    public RetryForToken(BaseLazyFragment fragment) {
        this.lazyFragment = fragment;
    }

    public RetryForToken(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public Flowable<?> apply(Flowable<? extends Throwable> flowable) throws Exception {
        return flowable.flatMap(new Function<Throwable, Publisher<?>>() {
            @Override
            public Publisher<?> apply(@NonNull Throwable throwable) throws Exception {
                if (throwable instanceof NetError && ((NetError) throwable).getType() == 2) { //判断是否是token过期的错误
                    // 如果上面检测到token过期就会进入到这里
                    // 然后下面的方法就是更新token
                    return RetrofitManager.getInstance().getApiService(URLConfig.BASE_API_URL).getToken("1", "234234")
                            .compose(XApi.<TokenBean>getScheduler())
                            .compose(activity == null ? lazyFragment.<TokenBean>bindToLifecycle() : activity.<TokenBean>bindToLifecycle())
                            .doOnNext(new Consumer<TokenBean>() {
                                @Override
                                public void accept(TokenBean tokenBean) throws Exception {
                                    //存放token值
//                                    getV().getvDelegate().toastShort(tokenBean.getAccessToken());
////                                    ToastUtil.showToast(tokenBean.getAccessToken());
                                    System.out.println(tokenBean.getAccessToken());
                                }
                            });
                }
                // 如果是其他错误则会调用到observer的onError方法中
                return Flowable.error(throwable);
            }
        });
    }
}
