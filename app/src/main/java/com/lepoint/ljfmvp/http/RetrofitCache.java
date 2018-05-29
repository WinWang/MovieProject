package com.lepoint.ljfmvp.http;

import android.content.Context;
import android.text.TextUtils;

import cn.droidlover.xdroidmvp.cache.DiskCache;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helin on 2016/11/10 10:41.
 */

public class RetrofitCache {
    /**
     * @param cacheKey     缓存的Key
     * @param fromNetwork
     * @param isSave       是否缓存
     * @return
     */
    public static Flowable<String> load(final Context context,
                                        final String cacheKey,
                                        Flowable<String> fromNetwork,
                                        boolean isSave
    ) {


        Flowable<String> fromCache = Flowable.create(new FlowableOnSubscribe<String>() {
                                                         @Override
                                                         public void subscribe(FlowableEmitter<String> e) throws Exception {
                                                             String cacheString = DiskCache.getInstance(context).get(cacheKey);
                                                             if (!TextUtils.isEmpty(cacheString)) {
                                                                 e.onNext(cacheString);
                                                                 e.onComplete();
                                                             } else {
                                                                 e.onComplete();
                                                             }
                                                         }
                                                     }, BackpressureStrategy.BUFFER
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        //强制刷新
        if (!isSave) {
            return fromNetwork;
        } else {
            //            return Observable.concat(fromCache, fromNetwork).takeFirst();
            Flowable<String> stringObservable = Flowable.concat(fromCache, fromNetwork);
            return stringObservable;
        }
    }


}
