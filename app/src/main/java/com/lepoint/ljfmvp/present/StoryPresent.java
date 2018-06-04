package com.lepoint.ljfmvp.present;

import android.view.View;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.StoryListBean;
import com.lepoint.ljfmvp.ui.fragment.StoryFragment;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Administrator on 2018/6/3 0003.
 */

public class StoryPresent extends BasePresent<StoryFragment> {
    public void getStoryList(int pageNum) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_STORY_URL).getStoryList(1, pageNum)
                .compose(XApi.<StoryListBean>getApiTransformer())
                .compose(XApi.<StoryListBean>getScheduler())
                .compose(getV().<StoryListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<StoryListBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(StoryListBean storyListBean) {
                        if (storyListBean.getErrcode() == 0) {
                            getV().hideLoading();
                            getV().refreshStory.setVisibility(View.VISIBLE);
                            List<StoryListBean.BooklistBean> booklist = storyListBean.getBooklist();
                            getV().setDataList(booklist);
                        }
                    }
                });
    }

    public void getStoryListHead() {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_STORY_URL).getStoryList(2, 1)
                .compose(XApi.<StoryListBean>getApiTransformer())
                .compose(XApi.<StoryListBean>getScheduler())
                .compose(getV().<StoryListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<StoryListBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(StoryListBean storyListBean) {
                        if (storyListBean.getErrcode() == 0) {
                            List<StoryListBean.BooklistBean> booklist = storyListBean.getBooklist();
                            getV().setHeadList(booklist);
                        }
                    }
                });
    }
}
