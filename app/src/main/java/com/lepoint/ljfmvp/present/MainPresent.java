package com.lepoint.ljfmvp.present;

import com.alibaba.fastjson.JSONObject;
import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.HttpUtils;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.BannerBean;
import com.lepoint.ljfmvp.model.UpdateBean;
import com.lepoint.ljfmvp.ui.activity.MainActivity;
import com.lepoint.ljfmvp.utils.SpUtils;
import com.lepoint.ljfmvp.utils.StringUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by admin on 2018/4/11.
 */

public class MainPresent extends BasePresent<MainActivity> {

    public void getHomeData() {
        //        HttpUtils.getGankService(URLConfig.BASE_API_URL).getToken("1", "111")
        //                .compose(XApi.<TokenBean>getApiTransformer())
        //                .compose(XApi.<TokenBean>getScheduler())
        //                .subscribe(new ApiSubscriber<TokenBean>() {
        //                    @Override
        //                    protected void onFail(NetError error) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onNext(TokenBean baseModel) {
        //                        if (baseModel.isAuthError()) {  //权限验证错误，token过期
        //                            getToken();
        //                        }
        //                    }
        //                });

        JSONObject json = new JSONObject();
        json.put("position", 1);
        String token = SpUtils.getString(getV(), "token", "accessToken");
        String key = SpUtils.getString(getV(), "token", "secretKey");
        long timeStamp = System.currentTimeMillis();
        String sign = StringUtils.encryptToSHA(token + URLConfig.QUERYADVERTISMENT + json.toJSONString() + timeStamp + key);
        HttpUtils.getInstance().getGankService(URLConfig.BASE_API_URL).queryHomeData(token, URLConfig.QUERYADVERTISMENT, json.toJSONString(), timeStamp, sign, "")
                .compose(XApi.<BannerBean>getApiTransformer())
                .compose(XApi.<BannerBean>getScheduler())
                .compose(getV().<BannerBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<BannerBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        if (error.getType() == 2) { //数据验证异常
                            //                            getToken();
                        }
                    }

                    @Override
                    public void onSuccess(BannerBean baseModel) {

                        System.out.println(">>>>>>>" + baseModel.getMessage());

                    }
                });


    }

    public void getUpdateData() {
        HttpUtils.getInstance().getGankService(URLConfig.BASE_API_URL).getUpdate("com.plyou.leintegration")
                .compose(XApi.<UpdateBean>getApiTransformer())
                .compose(XApi.<UpdateBean>getScheduler())
                .compose(getV().<UpdateBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<UpdateBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(UpdateBean updateBean) {
                        XLog.e(">>>>>" + updateBean.toString());
                        getV().updataApp(updateBean);
                    }
                });
    }


}
