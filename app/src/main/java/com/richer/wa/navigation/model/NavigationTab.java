package com.richer.wa.navigation.model;

import androidx.fragment.app.Fragment;

public class NavigationTab {

    Fragment fragment;
    String title;

    public NavigationTab(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
