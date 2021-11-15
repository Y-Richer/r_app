package com.richer.wa.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.richer.wa.RViewModelFactory;
import com.richer.wa.network.NetWorkUtil;
import com.trello.rxlifecycle4.components.support.RxFragment;

/**
 * create by richer on 2021/10/14
 *
 */
public abstract class BaseFragment<T extends BaseViewModel> extends RxFragment {

    protected T mViewModel;

    protected Class<T> clazz;

    protected abstract void setClazz();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setClazz();
        mViewModel = new ViewModelProvider(getViewModelStore(),
                new RViewModelFactory(NetWorkUtil.getAPI())).get(clazz);
    }

}
