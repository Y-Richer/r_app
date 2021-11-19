package com.richer.wa.navigation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.FragmentNavigationBinding;
import com.richer.wa.base.BaseFragment;
import com.richer.wa.classify.ClassifyActivity;
import com.richer.wa.navigation.view_model.NavigationViewModel;

public class NavigationFragment extends BaseFragment<NavigationViewModel> {

    FragmentNavigationBinding mBinding;

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
        mBinding.tvClassifyNavigation.setOnClickListener(this::goClassify);
    }

    public void goClassify(View view) {
        ClassifyActivity.start(getContext());
    }

}
