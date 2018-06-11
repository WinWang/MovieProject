package com.lepoint.ljfmvp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.present.NewsPresent;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity<NewsPresent> {


    @BindView(R.id.web_news)
    WebView webNews;
    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;
    private String dataID;
    private String downURL;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        if (TextUtils.isEmpty(downURL)) {
            getP().getNetData(dataID);
        } else {
            webNews.loadUrl(downURL);
        }
    }

    private void initView() {
        String title = getIntent().getStringExtra("title");
        dataID = getIntent().getStringExtra("dataID");
        downURL = getIntent().getStringExtra("downURL");
        topBar.setTitle(title);
        WebSettings settings = webNews.getSettings();
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
        } catch (Exception e) {

        }
        webNews.setWebViewClient(new HelloWebViewClient());
        webNews.setWebChromeClient(new WebChromeClient());

    }

    public void setWebNews(String url) {
        webNews.loadUrl(url);
    }

    private class HelloWebViewClient extends WebViewClient {

        private String startUrl;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            startUrl = url;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //            view.loadUrl(url);
            if (!url.startsWith("http")) {
                try {
                    // 以下固定写法
                    final Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                } catch (Exception e) {
                    // 防止没有安装的情况
                    e.printStackTrace();
                }
                return true;
            } else {
                if (startUrl != null && startUrl.equals(url)) {
                    view.loadUrl(url);
                    return true;
                } else {
                    //交给系统处理
                    return false;
                }
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressbar.setVisibility(View.GONE);
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public NewsPresent newP() {
        return new NewsPresent();
    }

}
