package com.lepoint.ljfmvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.lepoint.ljfmvp.R;
import com.lepoint.ljfmvp.base.BaseActivity;
import com.lepoint.ljfmvp.ui.fragment.BookFragment;
import com.lepoint.ljfmvp.ui.fragment.FindFragment;
import com.lepoint.ljfmvp.ui.fragment.HomeFragment;
import com.lepoint.ljfmvp.ui.fragment.StoryFragment;
import com.lepoint.ljfmvp.utils.AppManager;
import com.lepoint.ljfmvp.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_vp)
    ViewPager homeVp;
    @BindView(R.id.home_bottom_view)
    BottomNavigationView homeBottomView;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        setHomeVpAdapter();
    }

    private void initView() {
        BottomNavigationViewHelper.disableShiftMode(homeBottomView);
        homeVp.setOffscreenPageLimit(4);
        homeBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        homeVp.setCurrentItem(0);
                        break;

                    case R.id.bottom_ticket:
                        homeVp.setCurrentItem(1);
                        break;

                    case R.id.bottom_find:
                        homeVp.setCurrentItem(2);
                        break;

                    case R.id.bottom_mine:
                        homeVp.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });


        homeVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        homeBottomView.setSelectedItemId(R.id.bottom_home);
                        break;

                    case 1:
                        homeBottomView.setSelectedItemId(R.id.bottom_ticket);
                        break;

                    case 2:
                        homeBottomView.setSelectedItemId(R.id.bottom_find);
                        break;

                    case 3:
                        homeBottomView.setSelectedItemId(R.id.bottom_mine);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setHomeVpAdapter() {
        HomeFragment homeFragment = new HomeFragment();
        StoryFragment storyFragment = new StoryFragment();
        FindFragment findFragment1 = new FindFragment();
        BookFragment bookFragment = new BookFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(storyFragment);
        fragmentList.add(findFragment1);
        fragmentList.add(bookFragment);
        XFragmentAdapter xFragmentAdapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, null);
        homeVp.setAdapter(xFragmentAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public Object newP() {
        return null;
    }


    //退出时的时间
    private long mExitTime;
    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            getvDelegate().toastShort("再按一次退出电影无极限");
            mExitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit(context);
        }
    }


    @Override
    public void getNetData() {

    }
}
