package com.lepoint.ljfmvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.MovieAdapter;
import com.lepoint.ljfmvp.adapter.MovieDetailAboutAdapter;
import com.lepoint.ljfmvp.adapter.MovieDetailAdapter;
import com.lepoint.ljfmvp.adapter.VideoCommentAdapter;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.model.HomeListBean;
import com.lepoint.ljfmvp.model.VideoDetailBean;
import com.lepoint.ljfmvp.present.VideoDetailPresent;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoDetailActivity extends BaseActivity<VideoDetailPresent> {


    @BindView(R.id.video_player)
    JZVideoPlayerStandard videoPlayer;
    @BindView(R.id.tv_video_detail_title)
    TextView tvVideoDetailTitle;
    @BindView(R.id.iv_share_video_detail)
    ImageView ivShareVideoDetail;
    @BindView(R.id.iv_collect_video_detail)
    ImageView ivCollectVideoDetail;
    @BindView(R.id.rv_video_detail)
    RecyclerView rvVideoDetail;
    @BindView(R.id.refresh_video_detail)
    SmartRefreshLayout refreshVideoDetail;
    private String mediaID;
    ArrayList<String> dataList = new ArrayList<>();
    private VideoCommentAdapter videoCommentAdapter;
    private TextView videoTime;
    private TextView videoDesc;
    private RecyclerView likeRecycle;
    private ImageView descTag;
    private boolean tag = false;
    ArrayList<VideoDetailBean.RetBean.ListBean.ChildListBean> likeList = new ArrayList<>();
    ArrayList<VideoDetailBean.RetBean.ListBean.ChildListBean> aboutList = new ArrayList<>();
    private MovieDetailAdapter movieAdapter;
    private RecyclerView aboutRecycle;
    private MovieDetailAboutAdapter aboutAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        mediaID = getIntent().getStringExtra("mediaID");
        initView();
        getP().getVideoDetail(mediaID);
    }

    private void initView() {
        refreshVideoDetail.setEnableRefresh(false);
        refreshVideoDetail.setEnableAutoLoadMore(false);
        rvVideoDetail.setLayoutManager(new LinearLayoutManager(context));
        videoCommentAdapter = new VideoCommentAdapter(R.layout.item_video_commen_layout, dataList);
        View header = View.inflate(context, R.layout.header_video_detail_layout, null);
        videoTime = (TextView) header.findViewById(R.id.tv_video_detail_time);
        videoDesc = (TextView) header.findViewById(R.id.tv_movie_desc);
        descTag = (ImageView) header.findViewById(R.id.iv_video_detail_desc_tag);
        likeRecycle = (RecyclerView) header.findViewById(R.id.rv_video_detail_like);
        aboutRecycle = (RecyclerView) header.findViewById(R.id.rv_video_detail_about);
        aboutRecycle.setLayoutManager(new LinearLayoutManager(this));
        aboutAdapter = new MovieDetailAboutAdapter(R.layout.item_video_detail_about, aboutList, null);
        aboutRecycle.setAdapter(aboutAdapter);
        likeRecycle.setLayoutManager(new GridLayoutManager(context, 3));
        movieAdapter = new MovieDetailAdapter(R.layout.item_layout_intrest_layout, likeList, null);
        likeRecycle.setAdapter(movieAdapter);
        videoCommentAdapter.addHeaderView(header);
        rvVideoDetail.setAdapter(videoCommentAdapter);

        descTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tag) {
                    videoDesc.setSingleLine(false);
                    descTag.setImageResource(R.mipmap.btn_play_stop);
                } else {
                    videoDesc.setSingleLine(true);
                    descTag.setImageResource(R.mipmap.btn_play_spread);
                }
                tag = !tag;
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    public VideoDetailPresent newP() {
        return new VideoDetailPresent();
    }

    @OnClick({R.id.iv_share_video_detail, R.id.iv_collect_video_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_share_video_detail: //分享

                break;
            case R.id.iv_collect_video_detail:  //收藏

                break;
        }
    }

    public void setPlayData(VideoDetailBean.RetBean dataBean) {
        String smoothURL = dataBean.getSmoothURL();
        String sdurl = dataBean.getSDURL();
        String hdurl = dataBean.getHDURL();
        String ultraClearURL = dataBean.getUltraClearURL();
        String title = dataBean.getTitle();
        tvVideoDetailTitle.setText(title);
        //播放带清晰度的视频
        LinkedHashMap map = new LinkedHashMap();
        map.put("流畅", smoothURL);
        if (!TextUtils.isEmpty(sdurl)) {
            map.put("标清", sdurl);
        }
        if (!TextUtils.isEmpty(hdurl)) {
            map.put("高清", hdurl);
        }
        if (!TextUtils.isEmpty(ultraClearURL)) {
            map.put("超清", ultraClearURL);
        }
        Object[] objects = new Object[1];
        objects[0] = map;
        videoPlayer.setUp(objects, 0, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, title);
        videoPlayer.startVideo();
        if(!TextUtils.isEmpty(dataBean.getRegion())||!TextUtils.isEmpty(dataBean.getVideoType())){
            videoTime.setText(dataBean.getAirTime() + " 丨 " + dataBean.getRegion() + " 丨 " + dataBean.getVideoType());
        }else{
            videoTime.setText("");
        }
        videoDesc.setText("主演：" + dataBean.getActors() + "\n" +
                "导演：" + dataBean.getDirector() + "\n" +
                "时长：" + dataBean.getDuration() + "\n" +
                "剧情及简介：\n" +
                dataBean.getDescription());
        List<VideoDetailBean.RetBean.ListBean> list = dataBean.getList();
        if (list != null && list.size() > 0) {
            if (list.size() > 1) {
                VideoDetailBean.RetBean.ListBean listBean = list.get(0);
                List<VideoDetailBean.RetBean.ListBean.ChildListBean> listBeanChildList = listBean.getChildList();
                aboutList.clear();
                aboutList.addAll(listBeanChildList);
                aboutAdapter.notifyDataSetChanged();

                VideoDetailBean.RetBean.ListBean listBean1 = list.get(1);
                List<VideoDetailBean.RetBean.ListBean.ChildListBean> childList = listBean1.getChildList();
                likeList.clear();
                likeList.addAll(childList);
                movieAdapter.notifyDataSetChanged();
            } else {
                VideoDetailBean.RetBean.ListBean listBean = list.get(0);
                String titleHead = listBean.getTitle();
                if (TextUtils.equals(titleHead, "猜你喜欢")) {
                    List<VideoDetailBean.RetBean.ListBean.ChildListBean> childList = listBean.getChildList();
                    likeList.clear();
                    likeList.addAll(childList);
                    movieAdapter.notifyDataSetChanged();
                } else {
                    List<VideoDetailBean.RetBean.ListBean.ChildListBean> listBeanChildList = listBean.getChildList();
                    aboutList.clear();
                    aboutList.addAll(listBeanChildList);
                    aboutAdapter.notifyDataSetChanged();
                }
            }
        }


    }


    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
