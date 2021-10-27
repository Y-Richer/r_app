package com.richer.wa;

import android.content.Intent;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityMainBinding;
import com.richer.wa.base.BaseActivity;
import com.richer.wa.base.BaseFragment;
import com.richer.wa.home.model.HotSearchModel;
import com.richer.wa.home.view.HomeFragment;
import com.richer.wa.network.NetWorkUtil;
import com.richer.wa.test.TestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * create by richer on 2021/10/12
 * MainActivity
 */
public class MainActivity extends BaseActivity {

    private final int TAB_INDEX_0 = 0;

    private ActivityMainBinding mBinding;

    private List<HomeTab> homeTabList = new ArrayList<>();

    private MainViewModel mViewModel;

    private BaseFragment curFragment;

    @Override
    protected void initActivity() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new ViewModelProvider(getViewModelStore(),
                new RWViewModelFactory(NetWorkUtil.getAPI())).get(MainViewModel.class);

        initHomeTabs();
        initView();
        initData();
    }

    public void initData() {
        mViewModel.getHotSearchKey();
    }

    private void initView() {
        modifySelectedTab(TAB_INDEX_0);

        mViewModel.hotSearchKeys().observe(this, hotSearchModel -> {
            if (hotSearchModel != null && hotSearchModel.getData() != null) {
                if (hotSearchModel.getData().size() > 0) {
                    HotSearchModel.HotSearchBean hotSearchBean = hotSearchModel.getData().get(0);
                    mBinding.tvSearchHotMain.setText(hotSearchBean.getName());
                }
            }
        });
    }

    /**
     * 初始化首页tab fragment
     */
    private void initHomeTabs() {
        homeTabList.add(new HomeTab("home", HomeFragment.class));
    }

    private void modifySelectedTab(int tabPosition) {
        for (int i = 0; i < homeTabList.size(); i++) {
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

    public void test(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }


}