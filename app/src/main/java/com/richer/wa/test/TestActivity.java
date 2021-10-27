package com.richer.wa.test;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityTestBinding;
import com.richer.wa.CommonHeaderView;
import com.richer.wa.base.BaseActivity;
import com.richer.wa.utils.RToast;

public class TestActivity extends BaseActivity {

    private ActivityTestBinding mBinding;

    @Override
    protected void initActivity() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        initView();
    }

    private void initView() {
        mBinding.headerTest.init("Test", new CommonHeaderView.OnHeaderClickListener() {
            @Override
            public void onClickBack(View view) {
                RToast.show("back");
            }

            @Override
            public void onClickMenu(View view) {

            }
        });
    }

}
