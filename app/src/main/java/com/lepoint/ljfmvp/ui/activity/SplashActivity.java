package com.lepoint.ljfmvp.ui.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.TextView;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.utils.AppManager;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.ResourceSubscriber;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.tv_splash_second)
    TextView tvSplashSecond;
    @BindView(R.id.all_splash_button)
    AutoLinearLayout allSplashButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    public void getNetData() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getPerssion();
    }

    private void getPerssion() {
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            startJump();
                        } else {
                            getPerssion();
                            getvDelegate().toastShort("亲，同意了权限才能更好的使用软件哦");
                        }
                    }
                });
    }

    private void startJump() {
        Flowable.interval(1, 1, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Long>() {

                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return 4 - aLong;
                    }
                }).subscribe(new ResourceSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                tvSplashSecond.setText(aLong - 1 + "s");
                if (aLong == 1) {
                    Router.newIntent(SplashActivity.this)
                            .to(HomeActivity.class)
                            .launch();
                    finish();
                }

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public Object newP() {
        return null;
    }

    @OnClick(R.id.all_splash_button)
    public void onClick() {
        Router.newIntent(context).to(HomeActivity.class).launch();
        AppManager.getAppManager().finishActivity();
    }
}
