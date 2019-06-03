package com.example.tr534.wynews.news.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tr534.wynews.news.news.bean.FragmentInfo;

import java.util.ArrayList;

/**
 * Created by tr534 on 2018/12/21.
 */

public class NewsAdapter extends FragmentStatePagerAdapter {
    ArrayList<FragmentInfo> mFragments;

    public NewsAdapter(FragmentManager fm, ArrayList<FragmentInfo> fragments) {
        super(fm);
        this.mFragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
