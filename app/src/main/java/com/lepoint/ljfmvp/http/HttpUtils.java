package com.lepoint.ljfmvp.http;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.lepoint.ljfmvp.model.BaseModel;
import com.lepoint.ljfmvp.model.TokenBean;
import com.lepoint.ljfmvp.utils.SpUtils;
import com.lepoint.ljfmvp.utils.StringUtils;
import com.lepoint.ljfmvp.utils.ToastUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.reactivestreams.Publisher;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import cn.droidlover.xdroidmvp.cache.DiskCache;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/11.
 */

public class HttpUtils {

    private static final String SOCKETTIMEOUTEXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECTEXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWNHOSTEXCEPTION = "网络异常，请检查您的网络状态";

    private ApiService apiService;

    public ApiService getGankService(String BASE_URL) {
        if (apiService == null) {
            synchronized (HttpUtils.class) {
                if (apiService == null) {
                    apiService = XApi.getInstance().getRetrofit(BASE_URL + "/", true).create(ApiService.class);
                }
            }
        }
        return apiService;
    }


    /**
     * HttpUtil实例
     */
    private static HttpUtils INSTANCE;

    /**
     * 获取HttpUtil实例 ,单例模式
     */
    public static HttpUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpUtils();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * @param context
     * @param showDialog
     * @param json
     * @param url
     * @param actionName
     * @param callBack
     */
    public void getNetData(Context context, boolean showDialog, JSONObject json, String url, String actionName, NetCallBack callBack) {
        if (showDialog) {
            queryNetDataDialog(context, false, json, url, actionName, callBack);
        } else {
            queryNetData(context, false, json, url, actionName, callBack);
        }
    }

    public void getNetDataCache(Context context, boolean showDialog, JSONObject json, String url, String actionName, NetCallBack callBack) {
        if (showDialog) {
            queryNetDataDialog(context, true, json, url, actionName, callBack);
        } else {
            queryNetData(context, true, json, url, actionName, callBack);
        }
    }


    /**
     * 查询网络
     */
    private void queryNetData(final Context context, final boolean isSave, final JSONObject json, final String url, final String actionName, final NetCallBack callBack) {
        String token = SpUtils.getString(context, "token", "accessToken");
        String key = SpUtils.getString(context, "token", "secretKey");
        final String jsonString = json.toJSONString();
        long timeStamp = System.currentTimeMillis();
        final String sign = StringUtils.encryptToSHA(token + actionName + jsonString + timeStamp + key);
        //生成网络观察者
        Flowable<String> netFlowable = getGankService(url).queryData(token, actionName, jsonString, timeStamp, sign, "")
                .retryWhen(new RetryWithDelay(3, 3000, context))
                .map(new Function<ResponseBody, String>() { //数据转换
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        String response = responseBody.string();
                        try {
                            BaseModel baseModel = JSONObject.parseObject(response, BaseModel.class);
                            if (isSave && baseModel != null && baseModel.getResultCode() == 0) { //此处存放缓存的时候需要做个验证，否则不正确的网络响应也会存进来
                                DiskCache.getInstance(context).put(actionName + jsonString, response);//缓存存放
                            }
                        } catch (Exception e) {

                        }

                        return response;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Flowable<String> loadDataFlowable = RetrofitCache.load(context, actionName + jsonString, netFlowable, isSave);
        loadDataFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(((LifecycleProvider) context).bindToLifecycle())
                .subscribe(new ResourceSubscriber<String>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onNext(String response) {
                        try {
                            XLog.e("网络响应RX", response);
                            try {
                                BaseModel baseModel = JSONObject.parseObject(response, BaseModel.class);
                                if (baseModel != null && baseModel.getResultCode() == 0) {
                                    callBack.onSuccess(response);
                                } else if (baseModel != null && baseModel.getResultCode() == -1) {
                                    getToken(context, json, url, actionName, callBack,isSave);
                                }
                            } catch (Exception e) {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        XLog.e("网络错误RX", t.toString());
                        if (t instanceof SocketTimeoutException) {
                            ToastUtil.showToast(SOCKETTIMEOUTEXCEPTION);
                        } else if (t instanceof ConnectException) {
                            ToastUtil.showToast(CONNECTEXCEPTION);
                        } else if (t instanceof UnknownHostException) {
                            ToastUtil.showToast(UNKNOWNHOSTEXCEPTION);
                        } else {
                            ToastUtil.showToast("网络数据异常error");
                        }
                        callBack.onFailed(t);
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }


    /**
     * 查询网络DIalog
     */
    private void queryNetDataDialog(final Context context, final boolean isSave, final JSONObject json, final String url, final String actionName, final NetCallBack callBack) {
        String token = SpUtils.getString(context, "token", "accessToken");
        String key = SpUtils.getString(context, "token", "secretKey");
        final String jsonString = json.toJSONString();
        long timeStamp = System.currentTimeMillis();
        final String sign = StringUtils.encryptToSHA(token + actionName + jsonString + timeStamp + key);
        final QMUITipDialog tipDialog = new QMUITipDialog.Builder(context)
                .setTipWord("加载中")
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .create();
        tipDialog.setCancelable(true);
        //生成网络观察者
        Flowable<String> netFlowable = getGankService(url).queryData(token, actionName, jsonString, timeStamp, sign, "")
                .retryWhen(new RetryWithDelay(3, 3000, context))
                .map(new Function<ResponseBody, String>() { //数据转换
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        String response = responseBody.string();
                        try {
                            BaseModel baseModel = JSONObject.parseObject(response, BaseModel.class);
                            if (isSave && baseModel != null && baseModel.getResultCode() == 0) { //此处存放缓存的时候需要做个验证，否则不正确的网络响应也会存进来
                                DiskCache.getInstance(context).put(actionName + jsonString, response);//缓存存放
                            }
                        } catch (Exception e) {
                            System.out.println("error"+e.toString());
                        }
                        return response;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Flowable<String> loadDataFlowable = RetrofitCache.load(context, actionName + jsonString, netFlowable, isSave);
        loadDataFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(((LifecycleProvider) context).bindToLifecycle())
                .subscribe(new ResourceSubscriber<String>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        if (tipDialog != null) {
                            tipDialog.show();
                        }
                    }

                    @Override
                    public void onNext(String response) {
                        try {
                            if (tipDialog != null) {
                                tipDialog.dismiss();
                            }
                            XLog.e("网络响应RX", response);
                            try {
                                BaseModel baseModel = JSONObject.parseObject(response, BaseModel.class);
                                if (baseModel != null && baseModel.getResultCode() == 0) {
                                    callBack.onSuccess(response);
                                } else if (baseModel != null && baseModel.getResultCode() == -1) {
                                    getToken(context, json, url, actionName, callBack,isSave);
                                }
                            } catch (Exception e) {

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (tipDialog != null) {
                            tipDialog.dismiss();
                        }
                        XLog.e("网络错误RX", t.toString());
                        if (t instanceof SocketTimeoutException) {
                            ToastUtil.showToast(SOCKETTIMEOUTEXCEPTION);
                        } else if (t instanceof ConnectException) {
                            ToastUtil.showToast(CONNECTEXCEPTION);
                        } else if (t instanceof UnknownHostException) {
                            ToastUtil.showToast(UNKNOWNHOSTEXCEPTION);
                        } else {
                            ToastUtil.showToast("网络数据异常error");
                        }
                        callBack.onFailed(t);
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }


    public void getToken(final Context context, final JSONObject json, final String url, final String actionName, final NetCallBack callBack, final boolean isSave) {
        getGankService(URLConfig.BASE_API_URL).getToken("1", "111")
                .compose(XApi.<TokenBean>getApiTransformer())
                .compose(XApi.<TokenBean>getScheduler())
                .compose(((LifecycleProvider) context).bindToLifecycle())
                .subscribe(new ApiSubscriber<TokenBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(TokenBean tokenBean) {
                        XLog.e("获取RxToken", tokenBean.getAccessToken() + ">>><<<" + tokenBean.getSecretKey());
                        SpUtils.setString(context, "token", "accessToken", tokenBean.getAccessToken());
                        SpUtils.setString(context, "token", "secretKey", tokenBean.getSecretKey());
                        if (isSave) {
                            queryNetData(context, true, json, url, actionName, callBack);
                            XLog.e("缓存Token");
                        } else {
                            queryNetData(context, false, json, url, actionName, callBack);
                            XLog.e("正常Token");
                        }
                    }
                });
    }


    public interface NetCallBack {
        void onSuccess(String msg);

        void onFailed(Throwable t);
    }


    /**
     * 错误重试机制
     */
    public class RetryWithDelay implements Function<Flowable<? extends Throwable>, Flowable<?>> {

        private final int maxRetries;
        private final int retryDelayMillis;
        private int retryCount;
        private Context context;

        public RetryWithDelay(int maxRetries, int retryDelayMillis, Context context) {
            this.maxRetries = maxRetries;
            this.retryDelayMillis = retryDelayMillis;
            this.context = context;
        }

        @Override
        public Flowable<?> apply(Flowable<? extends Throwable> flowable) throws Exception {
            return flowable
                    .flatMap(new Function<Throwable, Publisher<?>>() {
                        @Override
                        public Publisher<?> apply(Throwable throwable) throws Exception {
                            if (++retryCount <= maxRetries && Kits.NetWork.hasNetwork(context)) {
                                return Flowable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                            }
                            return Flowable.error(throwable);
                        }
                    });
        }
    }

    /***************新网络通信模式，主要用于电影模块****************************************************/





}
