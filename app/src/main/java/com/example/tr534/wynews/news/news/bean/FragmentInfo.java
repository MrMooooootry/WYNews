package com.example.tr534.wynews.news.news.bean;

import android.support.v4.app.Fragment;

/**
 * Created by tr534 on 2018/12/21.
 */

public class FragmentInfo {
    Fragment fragment;
    String title;

    public FragmentInfo(Fragment fragment, String title) {
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
