package com.richer.wa;

import android.widget.ImageView;

import com.richer.wa.base.BaseFragment;

/**
 * create by richer on 2021/10/14
 *
 */
public class HomeTab {

    private String name;
    private Class<? extends BaseFragment> fragmentClass;
    private ImageView homeTabImg;

    public HomeTab(String name, Class<? extends BaseFragment> fragmentClass, ImageView homeTabImg) {
        this.name = name;
        this.fragmentClass = fragmentClass;
        this.homeTabImg = homeTabImg;
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

    public ImageView getHomeTabImg() {
        return homeTabImg;
    }

    public void setHomeTabImg(ImageView homeTabImg) {
        this.homeTabImg = homeTabImg;
    }
}
