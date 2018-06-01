package com.lepoint.ljfmvp.present;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.BaseModel;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.model.VideoDetailBean;
import com.lepoint.ljfmvp.ui.activity.VideoDetailActivity;
import com.lepoint.ljfmvp.utils.ToastUtil;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class VideoDetailPresent extends BasePresent<VideoDetailActivity> {

    /**
     * 获取数据详情
     *
     * @param mediaID
     */
    public void getVideoDetail(String mediaID) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL)
                .getVideoDetail(mediaID, "863064010156927")
                .compose(XApi.<VideoDetailBean>getApiTransformer())
                .compose(XApi.<VideoDetailBean>getScheduler())
                .compose(getV().<VideoDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<VideoDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(VideoDetailBean videoDetailBean) {
                        if (videoDetailBean.getCode() == 200) {
                            VideoDetailBean.RetBean ret = videoDetailBean.getRet();
                            getV().setPlayData(ret);
                        } else {
                            ToastUtil.showToast(videoDetailBean.getMsg());
                        }
                    }
                });
    }


    public void getVideoAuth(String dataId) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).addVideoList(dataId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(BaseModel historyListBean) {

                    }
                });
    }


    /**
     * 获取评论数据
     */
    public void getCommentData() {


    }


}
