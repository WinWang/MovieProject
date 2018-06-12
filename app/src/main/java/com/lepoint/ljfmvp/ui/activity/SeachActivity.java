package com.lepoint.ljfmvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.HistoryAdapter;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.model.RealmHistoryBean;
import com.lepoint.ljfmvp.utils.AppManager;
import com.lepoint.ljfmvp.widget.autolayout.AutoRoundRelativielayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class SeachActivity extends BaseActivity {


    @BindView(R.id.seach_back)
    ImageView seachBack;
    @BindView(R.id.iv_home_seach)
    ImageView ivHomeSeach;
    @BindView(R.id.edit_seach)
    EditText editSeach;
    @BindView(R.id.qmrl_home_seach)
    AutoRoundRelativielayout qmrlHomeSeach;
    @BindView(R.id.tv_seach)
    TextView tvSeach;
    @BindView(R.id.rv_seach_history)
    RecyclerView rvSeachHistory;

    ArrayList<RealmHistoryBean> historyList = new ArrayList<>();
    @BindView(R.id.iv_history_delete)
    ImageView ivHistoryDelete;
    private HistoryAdapter historyAdapter;
    private Realm realm;

    @Override
    public void initData(Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        initView();
        getNetData();
    }

    private void initView() {
        rvSeachHistory.setLayoutManager(new LinearLayoutManager(context));
        historyAdapter = new HistoryAdapter(R.layout.item_seach_history_layout, historyList);
        rvSeachHistory.setAdapter(historyAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_seach;
    }

    private void addItem(List<RealmHistoryBean> realmData) {
        historyList.clear();
        Collections.reverse(realmData);
        historyList.addAll(realmData);
        historyAdapter.notifyDataSetChanged();

    }


    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void getNetData() {
        List<RealmHistoryBean> realmData = getRealmData();
        addItem(realmData);
    }

    @OnClick({R.id.seach_back, R.id.tv_seach, R.id.iv_history_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seach_back:
                AppManager.getAppManager().finishActivity(this);
                break;
            case R.id.tv_seach:
                insertData();
                break;
            case R.id.iv_history_delete:
                deleteRealmData();
                historyList.clear();
                historyAdapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 插入数据
     */
    private void insertData() {
        String s = editSeach.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            realm.beginTransaction();
            RealmHistoryBean realmHistoryBean = new RealmHistoryBean();
            realmHistoryBean.setId(s);
            realmHistoryBean.setName(s);
            realm.copyToRealmOrUpdate(realmHistoryBean);
            realm.commitTransaction();
            //刷新数据
            addItem(getRealmData());
        }
    }

    /**
     * 查询数据库
     *
     * @return
     */
    private List<RealmHistoryBean> getRealmData() {
        RealmResults<RealmHistoryBean> historyBeans = realm.where(RealmHistoryBean.class).findAll();
        return realm.copyFromRealm(historyBeans);

    }


    private void deleteRealmData() {
        final RealmResults<RealmHistoryBean> students3 = realm.where(RealmHistoryBean.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                students3.deleteAllFromRealm();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
