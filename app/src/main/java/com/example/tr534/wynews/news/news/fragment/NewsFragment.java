package com.example.tr534.wynews.news.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.tr534.wynews.R;
import com.example.tr534.wynews.news.news.adapter.NewsAdapter;
import com.example.tr534.wynews.news.news.bean.FragmentInfo;
import com.example.tr534.wynews.news.news.news_inner.HotFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

/**
 * Created by tr534 on 2018/12/20.
 */

public class NewsFragment extends android.support.v4.app.Fragment {
    ArrayList<FragmentInfo> pages;
    NewsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pages=new ArrayList<>();

//        FrameLayout frameLayout=(FrameLayout)getActivity().findViewById(R.id.taps);
//        frameLayout.addView(View.inflate(getActivity(),R.layout.include_tab,null));
//        SmartTabLayout smartTabLayout=(SmartTabLayout)getActivity().findViewById(R.id.smart_tab);
//        ViewPager viewPager=(ViewPager)getActivity().findViewById(R.id.viewPager);

//        FrameLayout frameLayout=(FrameLayout)getActivity().findViewById(R.id.taps);
//        frameLayout.addView(View.inflate(getActivity(),R.layout.include_tab,null));
//        SmartTabLayout smartTabLayout=(SmartTabLayout)getActivity().findViewById(R.id.smart_tab);
//        ViewPager viewPager=(ViewPager)getActivity().findViewById(R.id.viewPager);
        FrameLayout frameLayout=(FrameLayout) getActivity().findViewById(R.id.taps);
        frameLayout.addView(View.inflate(getActivity(),R.layout.include_tab,null));
        SmartTabLayout smartTabLayout = (SmartTabLayout) getActivity().findViewById(R.id.smart_tab);
        ViewPager viewPager=(ViewPager)getActivity().findViewById(R.id.viewPager);



        String[] titles = getResources().getStringArray(R.array.news_title);
        for (int i=0;i<titles.length;i++)
        {
            FragmentInfo fragmentInfo;
            if (i==0)
            {
                fragmentInfo =new FragmentInfo(new HotFragment(),titles[i]);
            }
            else {
                fragmentInfo=new FragmentInfo(new EmptyFragment(),titles[i]);
            }
                pages.add(fragmentInfo);
        }
        adapter=new NewsAdapter(getFragmentManager(),pages);
        viewPager.setAdapter(adapter);
//        smartTabLayout.setViewPager(viewPager);
        smartTabLayout.setViewPager(viewPager);

    }
}
