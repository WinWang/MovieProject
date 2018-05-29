package com.lepoint.ljfmvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.manager.DownloadManager;
import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.event.HomeFragEvent;
import com.lepoint.ljfmvp.model.UpdateBean;
import com.lepoint.ljfmvp.present.MainPresent;
import com.lepoint.ljfmvp.ui.fragment.HomeFragment;
import com.lepoint.ljfmvp.utils.AppManager;
import com.lepoint.ljfmvp.utils.BottomNavigationViewHelper;
import com.lepoint.ljfmvp.utils.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainPresent> {


    @BindView(R.id.vp_main_home)
    ViewPager vpMainHome;
    @BindView(R.id.buttonPanel)
    Button buttonPanel;
    @BindView(R.id.qm_topbar)
    QMUITopBar qmTopbar;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationView bottomNavigationBar;
    @BindView(R.id.home_navigation)
    BottomNavigationBar homeNavigation;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {


        BottomNavigationViewHelper.disableShiftMode(bottomNavigationBar);

        homeNavigation.addItem(new BottomNavigationItem(R.mipmap.btn_home_nor, "首页")
                .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.btn_wp_nor, "发现")
                        .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.btn_user_nor, "我的")
                        .setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)//默认选择索引为0的菜单
                .initialise();


        HomeFragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
        XFragmentAdapter xFragmentAdapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, null);
        vpMainHome.setAdapter(xFragmentAdapter);
        //        getP().getHomeData();
        qmTopbar.setTitle("首页");
        qmTopbar.setSubTitle("我是副标题");
        qmTopbar.addLeftTextButton("返回", R.id.topbar_left_text_button);
        qmTopbar.addRightImageButton(R.mipmap.ic_launcher, R.id.topbar_right_about_button);
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            ToastUtil.showToast("权限成功");
                        } else {
                            ToastUtil.showToast("权限失败");
                        }
                    }
                });
        //        getRxPermissions().requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
        //                Manifest.permission.READ_EXTERNAL_STORAGE,
        //                Manifest.permission.READ_PHONE_STATE,
        //                Manifest.permission.CAMERA)
        //                .subscribe(new Consumer<Permission>() {
        //                    @Override
        //                    public void accept(Permission permission) throws Exception {
        //                        //                        if (permission.granted) {
        //                        //                            getvDelegate().toastShort("权限成功");
        //                        //                        } else {
        //                        //                            getvDelegate().toastShort("权限失败");
        //                        //                        }
        //                    }
        //                });
    }

    @Override
    public boolean useEventBus() {
        return true;
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public MainPresent newP() {
        return new MainPresent();
    }


    public void updataApp(UpdateBean updateBean) {
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //支持断点下载
                .setBreakpointDownload(true)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置强制更新
                .setForcedUpgrade(false);
        //设置下载过程的监听
        //                .setOnDownloadListener(this);

        DownloadManager manager = DownloadManager.getInstance(this);
        manager.setApkName("appupdate.apk")
                .setApkUrl(updateBean.getDownloadUrl())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setConfiguration(configuration)
                .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                .setApkVersionCode(updateBean.getVersionNo())
                .setApkVersionName(updateBean.getVersion())
                .setApkDescription(updateBean.getNotice())
                .download();
    }


    @OnClick(R.id.buttonPanel)
    public void onViewClicked() {
        getP().getHomeData();
        Router.newIntent(context)
                .to(TestActivity.class)
                .requestCode(100)
                .launch();
//        getP().getUpdateData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().AppExit(this);
        XLog.e("执行这条语句");
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void notifyData(HomeFragEvent event) {
        System.out.println("测试>>>>>");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String mark = data.getStringExtra("mark");
        getvDelegate().toastShort("回调发生" + mark);
    }


}
