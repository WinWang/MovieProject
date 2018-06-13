package com.lepoint.ljfmvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.adapter.HistoryAdapter;
import com.lepoint.ljfmvp.adapter.SeachNetAdapter;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.model.RealmHistoryBean;
import com.lepoint.ljfmvp.model.SeachListBean;
import com.lepoint.ljfmvp.present.SeachPresent;
import com.lepoint.ljfmvp.utils.AppManager;
import com.lepoint.ljfmvp.utils.DialogUtil;
import com.lepoint.ljfmvp.widget.autolayout.AutoRoundRelativielayout;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import io.realm.Realm;
import io.realm.RealmResults;

public class SeachActivity extends BaseActivity<SeachPresent> {


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
    List<SeachListBean.RetBean.ListBean> dataList = new ArrayList<>();
    @BindView(R.id.iv_history_delete)
    ImageView ivHistoryDelete;
    @BindView(R.id.seach_del)
    ImageView seachDel;
    @BindView(R.id.ll_seach_head)
    LinearLayout llSeachHead;
    @BindView(R.id.rv_seach_net)
    RecyclerView rvSeachNet;
    private HistoryAdapter historyAdapter;
    private Realm realm;
    private SeachNetAdapter seachNetAdapter;
    public QMUITipDialog dialog;

    @Override
    public void initData(Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        initView();
        initListener();
        getNetData();
    }

    private void initListener() {
        editSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (TextUtils.isEmpty(s1)) {
                    seachDel.setVisibility(View.GONE);
                    llSeachHead.setVisibility(View.VISIBLE);
                } else {
                    seachDel.setVisibility(View.VISIBLE);
                }
            }
        });

        seachNetAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String dataId = dataList.get(position).getDataId();
                Router.newIntent(context)
                        .putString("mediaID", dataId)
                        .to(VideoDetailActivity.class)
                        .launch();
            }
        });

        historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String name = historyList.get(position).getName();
                editSeach.setText(name);
                editSeach.setSelection(name.length());
                llSeachHead.setVisibility(View.GONE);
                getP().getSeachData(name);
            }
        });


    }

    private void initView() {
        rvSeachHistory.setLayoutManager(new LinearLayoutManager(context));
        historyAdapter = new HistoryAdapter(R.layout.item_seach_history_layout, historyList);
        rvSeachHistory.setAdapter(historyAdapter);
        rvSeachNet.setLayoutManager(new GridLayoutManager(context, 3));
        seachNetAdapter = new SeachNetAdapter(R.layout.item_layout_intrest_layout, dataList);
        rvSeachNet.setAdapter(seachNetAdapter);
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
    public SeachPresent newP() {
        return new SeachPresent();
    }

    @Override
    public void getNetData() {
        List<RealmHistoryBean> realmData = getRealmData();
        addItem(realmData);
    }

    @OnClick({R.id.seach_back, R.id.tv_seach, R.id.iv_history_delete, R.id.seach_del})
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
            case R.id.seach_del:
                editSeach.setText("");
                break;
        }
    }

    /**
     * 插入数据
     */
    private void insertData() {
        String s = editSeach.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            if (dialog == null) {
                dialog = DialogUtil.showDialog(context);
            } else {
                dialog.show();
            }
            realm.beginTransaction();
            RealmHistoryBean realmHistoryBean = new RealmHistoryBean();
            realmHistoryBean.setId(s);
            realmHistoryBean.setName(s);
            realm.copyToRealmOrUpdate(realmHistoryBean);
            realm.commitTransaction();
            //刷新数据
            addItem(getRealmData());

            //请求网络数据
            llSeachHead.setVisibility(View.GONE);
            getP().getSeachData(s);

        } else {
            getvDelegate().toastShort("请输入查询内容");
        }
    }


    public void setData(List<SeachListBean.RetBean.ListBean> list) {
        dataList.clear();
        dataList.addAll(list);
        seachNetAdapter.notifyDataSetChanged();
        dialog.dismiss();
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


    /**
     * 删除数据
     */
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
