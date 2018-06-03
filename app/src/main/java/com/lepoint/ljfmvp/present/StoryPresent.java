package com.lepoint.ljfmvp.present;

import android.view.View;

import com.lepoint.ljfmvp.base.BasePresent;
import com.lepoint.ljfmvp.http.NetProviderImpl;
import com.lepoint.ljfmvp.http.RetrofitManager;
import com.lepoint.ljfmvp.http.URLConfig;
import com.lepoint.ljfmvp.model.MovieListBean;
import com.lepoint.ljfmvp.model.StoryListBean;
import com.lepoint.ljfmvp.ui.fragment.StoryFragment;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

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

                    }

                    @Override
                    protected void onSuccess(StoryListBean storyListBean) {
                        getV().refreshStory.finishRefresh(1000);
                        if (storyListBean.getErrcode() == 0) {
                            getV().emptyLoadingLayout.hide();
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
