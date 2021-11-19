package com.richer.wa.test;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityTestBinding;
import com.richer.wa.CommonHeaderView;
import com.richer.wa.base.BaseActivity;
import com.richer.wa.utils.RToast;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseActivity<TestViewModel> {

    private ActivityTestBinding mBinding;

    @Override
    public void setClazz() {
        clazz = TestViewModel.class;
    }

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

        List<String> list = new ArrayList<>();
        list.add("刺猬乐队");
        list.add("新裤子乐队");
        list.add("旅行团");
        list.add("面孔");
        list.add("苏打绿");
        list.add("花儿乐队");
        list.add("abcde");
        list.add("Click#15");
        list.add("斯斯与帆");

    }

}
