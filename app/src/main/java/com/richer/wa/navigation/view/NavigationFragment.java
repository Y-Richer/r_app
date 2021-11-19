package com.richer.wa.navigation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.FragmentNavigationBinding;
import com.richer.wa.base.BaseFragment;
import com.richer.wa.classify.ClassifyFragment;
import com.richer.wa.navigation.NavigationTitleAdapter;
import com.richer.wa.navigation.model.NavigationTab;
import com.richer.wa.navigation.view_model.NavigationViewModel;

import java.util.ArrayList;
import java.util.List;

public class NavigationFragment extends BaseFragment<NavigationViewModel> implements NavigationTitleAdapter.NavigationTitleListener {

    FragmentNavigationBinding mBinding;
    List<NavigationTab> fragmentTabList;
    NavigationTitleAdapter mTitleAdapter;

    @Override
    protected void setClazz() {
        clazz = NavigationViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_navigation, container, false);
        mBinding = DataBindingUtil.bind(root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        fragmentTabList = new ArrayList<>();
        fragmentTabList.add(new NavigationTab(new ClassifyFragment(), "导航"));

        mTitleAdapter = new NavigationTitleAdapter(getContext(), fragmentTabList, this);
        mBinding.rvTitleNavigation.setAdapter(mTitleAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBinding.rvTitleNavigation.setLayoutManager(manager);

        mBinding.viewpagerNavigation.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTitleAdapter.setSelectedPosition(position);
                mTitleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBinding.viewpagerNavigation.setAdapter(new NavigationPagerAdapter(getActivity().getSupportFragmentManager(), fragmentTabList));
    }

    @Override
    public void onTitleClick(String title, int position) {

    }


    static class NavigationPagerAdapter extends FragmentStatePagerAdapter {

        List<NavigationTab> fragmentList;

        public NavigationPagerAdapter(@NonNull FragmentManager fm, List<NavigationTab> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
