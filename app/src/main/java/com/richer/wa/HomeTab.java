package com.richer.wa;

import com.richer.wa.base.BaseFragment;

/**
 * create by richer on 2021/10/14
 *
 */
public class HomeTab {

    private String name;
    private Class<? extends BaseFragment> fragmentClass;

    public HomeTab(String name, Class<? extends BaseFragment> fragmentClass) {
        this.name = name;
        this.fragmentClass = fragmentClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends BaseFragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setNewInstance(Class<? extends BaseFragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }
}
