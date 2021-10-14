package com.richer.wa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityMainBinding;
import com.richer.wa.home.view.HomeFragment;
import com.richer.wa.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * create by richer on 2021/10/12
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {

    private final int TAB_INDEX_0 = 0;

    private ActivityMainBinding mBinding;

    private List<HomeTab> homeTabList = new ArrayList<>();

    private BaseFragment curFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarTransparent(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initHomeTabs();
        initView();

    }

    private void initView() {
        modifySelectedTab(TAB_INDEX_0);
    }

    /**
     * 初始化首页tab fragment
     */
    private void initHomeTabs() {
        homeTabList.add(new HomeTab("home", HomeFragment.class));
    }

    private void modifySelectedTab(int tabPosition) {
        for (int i=0;i<homeTabList.size();i++) {
            if (i == tabPosition) {
                showSelectedFragment(homeTabList.get(i));
            }
        }
    }

    private void showSelectedFragment(HomeTab homeTab) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment nextFragment = fragmentManager.findFragmentByTag(homeTab.getName());
        try {
            if (nextFragment == null) {
                nextFragment = homeTab.getFragmentClass().newInstance();
            }
            if (nextFragment != null) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (curFragment != null && curFragment != nextFragment) {
                    transaction.hide(curFragment);
                    transaction.setMaxLifecycle(curFragment, Lifecycle.State.STARTED);
                }
                if (nextFragment.isAdded()) {
                    transaction.show(nextFragment);
                    transaction.setMaxLifecycle(nextFragment, Lifecycle.State.RESUMED);
                } else {
                    transaction.add(R.id.fragment_containner, nextFragment, homeTab.getName());
                }
                transaction.commitAllowingStateLoss();
                curFragment = (BaseFragment) nextFragment;
            }
        } catch (IllegalAccessException e) {

        } catch (InstantiationException e) {

        }
    }


}