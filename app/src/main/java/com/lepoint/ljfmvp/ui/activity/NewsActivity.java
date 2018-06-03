package com.lepoint.ljfmvp.ui.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity {


    @BindView(R.id.web_news)
    WebView webNews;
    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        String title = getIntent().getStringExtra("title");
        topBar.setTitle(title);
        WebSettings settings = webNews.getSettings();
//        settings.get
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public Object newP() {
        return null;
    }

}
