package com.richer.wa;

import android.content.Intent;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityMainBinding;
import com.richer.wa.base.BaseActivity;
import com.richer.wa.base.BaseFragment;
import com.richer.wa.eventbus.event.DataChangeEvent;
import com.richer.wa.home.HomeFragment;
import com.richer.wa.search.model.HotSearchModel;
import com.richer.wa.search.view.SearchActivity;
import com.richer.wa.search.view_model.SearchViewModel;
import com.richer.wa.test.TestActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * create by richer on 2021/10/12
 * MainActivity
 */
public class MainActivity extends BaseActivity<SearchViewModel> {

    private final int TAB_INDEX_0 = 0;

    private ActivityMainBinding mBinding;

    private List<HomeTab> homeTabList = new ArrayList<>();

    @Override
    public void setClazz() {
        clazz = SearchViewModel.class;
    }

    private BaseFragment curFragment;

    @Override
    protected void initActivity() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initHomeTabs();
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void initData() {
        mViewModel.getHotSearchWord();
    }

    private void initView() {
        modifySelectedTab(TAB_INDEX_0);

        mViewModel.hotSearchModel().observe(this, hotSearchModel -> {
            if (hotSearchModel != null && hotSearchModel.getData() != null) {
                if (hotSearchModel.getData().size() > 0) {
                    int randomIndex = new Random().nextInt(hotSearchModel.getData().size());
                    HotSearchModel.HotSearchBean hotSearchBean = hotSearchModel.getData().get(randomIndex);
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

    public void goTest(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public void goSearch(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataChange(DataChangeEvent event) {
        initData();
    }


}