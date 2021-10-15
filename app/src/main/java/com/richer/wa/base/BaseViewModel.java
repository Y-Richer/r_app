package com.richer.wa.base;

import androidx.lifecycle.ViewModel;

import com.richer.wa.API;

/**
 * create by richer on 2021/10/14
 * ViewModel基类
 * 所有ViewModel继承此类
 * 可使用RWViewModelFactory创建ViewModel对象
 */
public class BaseViewModel extends ViewModel {

    protected API api;

    public BaseViewModel(API api) {
        this.api = api;
    }
}
