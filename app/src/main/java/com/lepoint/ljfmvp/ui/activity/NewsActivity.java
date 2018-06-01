package com.lepoint.ljfmvp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.base.BaseActivity;

public class NewsActivity extends BaseActivity {


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        String title = getIntent().getStringExtra("title");
        topBar.setTitle(title);
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
