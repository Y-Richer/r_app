package com.richer.wa.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.richer.wa.utils.StatusBarUtil;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarTransparent(this);
        initActivity();
    }

    protected void initActivity() {
    }
}
