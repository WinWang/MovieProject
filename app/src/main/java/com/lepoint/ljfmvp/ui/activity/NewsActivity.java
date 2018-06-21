package com.lepoint.ljfmvp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.present.NewsPresent;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhouyou.view.seekbar.SignSeekBar;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.net.NetError;

public class NewsActivity extends BaseActivity<NewsPresent> {


    @BindView(R.id.web_news)
    WebView webNews;
    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;
    private String dataID;
    private String downURL;
    private WebSettings settings;
    private int textSize = 2;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        getNetData();
    }

    private void initView() {
        String title = getIntent().getStringExtra("title");
        dataID = getIntent().getStringExtra("dataID");
        downURL = getIntent().getStringExtra("downURL");
        topBar.setTitle(title);
        settings = webNews.getSettings();
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
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

        topBar.addRightTextButton("A", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeTextSizeDialog();
            }
        });
    }


    private void showChangeTextSizeDialog() {
        QMUIDialog qmuiDialog = new QMUIDialog.CustomDialogBuilder(context)
                .setLayout(R.layout.dialog_web_text_change)
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                }).create();
        qmuiDialog.show();
        SignSeekBar seekBar = (SignSeekBar) qmuiDialog.findViewById(R.id.demo_5_seek_bar_2);

        seekBar.getConfigBuilder()
                .min(0)
                .max(4)
                .progress(textSize)
                .sectionCount(4)
                //.trackColor(ContextCompat.getColor(getContext(), R.color.color_gray))
                //.secondTrackColor(ContextCompat.getColor(getContext(), R.color.color_blue))
                .thumbColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .sectionTextColor(ContextCompat.getColor(context, R.color.qmui_config_color_blue))
                //.thumbTextColor(ContextCompat.getColor(getContext(), R.color.color_red))
                //.thumbTextSize(18)
                //.signColor(ContextCompat.getColor(getContext(), R.color.color_green))
                //.signTextSize(18)
                .sectionTextPosition(SignSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();
        //signSeekBar.setEnabled(false);//设置不可以用的时候，可以设置ssb_unusable_color不可用颜色
        seekBar.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
            }

            @Override
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {
            }

            @Override
            public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                switch (progress) {
                    case 0:
                        settings.setTextSize(WebSettings.TextSize.SMALLEST);
                        break;

                    case 1:
                        settings.setTextSize(WebSettings.TextSize.SMALLER);
                        break;

                    case 2:
                        settings.setTextSize(WebSettings.TextSize.NORMAL);
                        break;

                    case 3:
                        settings.setTextSize(WebSettings.TextSize.LARGER);
                        break;
                    case 4:
                        settings.setTextSize(WebSettings.TextSize.LARGEST);
                        break;
                }
                textSize = progress;
            }
        });


    }


    public void setWebNews(String url) {
        webNews.loadUrl(url);
    }

    @Override
    public void getNetData() {
        if (TextUtils.isEmpty(downURL)) {
            getP().getNetData(dataID);
        } else {
            webNews.loadUrl(downURL);
        }
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
            hideLoading();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            NetError netError = new NetError("数据异常", NetError.OtherError);
            setRetryView(netError);
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
