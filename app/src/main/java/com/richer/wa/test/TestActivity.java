package com.richer.wa.test;

import androidx.databinding.DataBindingUtil;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityTestBinding;
import com.richer.wa.base.BaseActivity;

public class TestActivity extends BaseActivity {

    private ActivityTestBinding mBinding;

    @Override
    protected void initActivity() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        initView();
    }

    private void initView() {
        mBinding.headerTest.init("Test", null);
    }
}
