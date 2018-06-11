package com.lepoint.ljfmvp.present;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.StoryBookList;
import com.lepoint.ljfmvp.model.StoryListBean;
import com.lepoint.ljfmvp.ui.activity.StoryListActivity;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoryListPresent extends BasePresent<StoryListActivity> {
    public void getBookList(String bookID) {
        RetrofitManager.getInstance()
                .getApiService(URLConfig.BASE_STORY_URL)
                .getBookList(bookID)
                .compose(XApi.<StoryBookList>getScheduler())
                .compose(XApi.<StoryBookList>getApiTransformer())
                .compose(getV().<StoryBookList>bindToLifecycle())
                .subscribe(new ApiSubscriber<StoryBookList>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(StoryBookList storyListBean) {
                        List<StoryBookList.PartListBean> partList = storyListBean.getPartList();
                        getV().setBookList(partList);
                    }
                });

    }
}
