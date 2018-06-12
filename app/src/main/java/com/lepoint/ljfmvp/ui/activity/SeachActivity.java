package com.lepoint.ljfmvp.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.model.RealmHistoryBean;
import com.lepoint.ljfmvp.utils.AppManager;
import com.lepoint.ljfmvp.widget.autolayout.AutoRoundRelativielayout;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;

import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.seach_float_layout)
    QMUIFloatLayout seachFloatLayout;

    @Override
    public void initData(Bundle savedInstanceState) {
        List<RealmHistoryBean> realmData = getRealmData();
        addItem(realmData);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_seach;
    }

    private void addItem(List<RealmHistoryBean> realmData) {
        for (RealmHistoryBean realmDatum : realmData) {
            TextView textView = new TextView(context);
            int textViewPadding = QMUIDisplayHelper.dp2px(context, 4);
            textView.setPadding(textViewPadding, textViewPadding, textViewPadding, textViewPadding);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView.setTextColor(ContextCompat.getColor(context, R.color.qmui_config_color_white));
            textView.setText(String.valueOf(realmDatum.getName()));
            int textViewSize = QMUIDisplayHelper.dpToPx(60);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(textViewSize, textViewSize);
            seachFloatLayout.addView(textView, lp);
        }

    }


    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void getNetData() {

    }

    @OnClick({R.id.seach_back, R.id.tv_seach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seach_back:
                AppManager.getAppManager().finishActivity(this);
                break;
            case R.id.tv_seach:
                insertData();
                break;
        }
    }

    /**
     *
     */
    private void insertData() {
        String s = editSeach.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            RealmHistoryBean realmHistoryBean = new RealmHistoryBean();
            realmHistoryBean.setId(s);
            realmHistoryBean.setName(s);
            realm.copyToRealmOrUpdate(realmHistoryBean);
            realm.commitTransaction();
        }
    }

    /**
     * 查询数据库
     *
     * @return
     */
    private List<RealmHistoryBean> getRealmData() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<RealmHistoryBean> historyBeans = mRealm.where(RealmHistoryBean.class).findAll();
        return mRealm.copyFromRealm(historyBeans);

    }


}
