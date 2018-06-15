package com.lepoint.ljfmvp.http;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.lepoint.ljfmvp.base.MyApp;

import cn.droidlover.xdroidmvp.cache.DiskCache;
import cn.droidlover.xdroidmvp.net.IModel;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by admin on 2018/6/15.
 */

public class RxUtils {

    /**
     * 从缓存取数据
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends IModel> Flowable rxCreateDiskObservable(final String key, final Class<T> clazz) {
        Flowable<T> on = Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> e) throws Exception {
                String cacheString = DiskCache.getInstance(MyApp.getContext()).get(key);
                if (!TextUtils.isEmpty(cacheString)) {
                    e.onNext(JSONObject.parseObject(cacheString, clazz));
                    e.onComplete();
                } else {
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.BUFFER);
        return on;
    }




}
