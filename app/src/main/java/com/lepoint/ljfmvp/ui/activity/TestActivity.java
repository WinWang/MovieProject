package com.lepoint.ljfmvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.TestAdapter;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.http.HttpUtils;
import com.lepoint.ljfmvp.http.URLConfig;

import java.util.ArrayList;

import butterknife.BindView;

public class TestActivity extends BaseActivity {


    @BindView(R.id.rv_test)
    RecyclerView rvTest;

    @Override
    public void initData(Bundle savedInstanceState) {
        topBar.setTitle("测试界面");
        JSONObject json = new JSONObject();
        json.put("position", 3);
        HttpUtils.getInstance().getNetData(context, true, json, URLConfig.BASE_API_URL, URLConfig.QUERYADVERTISMENT, new HttpUtils.NetCallBack() {
            @Override
            public void onSuccess(String msg) {

            }

            @Override
            public void onFailed(Throwable t) {

            }
        });

        Intent intent = getIntent();
        intent.putExtra("mark", "A");
        setResult(200, intent);

        rvTest.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
                strings.add("sdf");
        }

        TestAdapter testAdapter = new TestAdapter(R.layout.item_layout, strings);
        rvTest.setAdapter(testAdapter);
        testAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getvDelegate().toastShort("sdfddd");
            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public Object newP() {
        return null;
    }

}
