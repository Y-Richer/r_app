package com.richer.wa.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.richer.wa.RViewModelFactory;
import com.richer.wa.network.NetWorkUtil;
import com.richer.wa.utils.StatusBarUtil;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

public abstract class BaseActivity<T extends BaseViewModel> extends RxAppCompatActivity {

    public static final String BUNDLE_KEY = "bundle";

    protected T mViewModel;

    protected Class<T> clazz;

    protected Intent intent;

    protected Bundle bundle;

    /**
     * 设置ViewModel Class
     */
    protected abstract void setClazz();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarTransparent(this);
        setClazz();
        mViewModel = new ViewModelProvider(getViewModelStore(),
                new RViewModelFactory(NetWorkUtil.getAPI())).get(clazz);
        intent = getIntent();
        if (intent != null) {
            bundle = intent.getBundleExtra(BUNDLE_KEY);
        }
        initActivity();
    }

    protected void initActivity() {
    }
}
