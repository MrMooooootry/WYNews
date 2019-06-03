package com.example.tr534.wynews.news.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tr534.wynews.R;

/**
 * Created by tr534 on 2018/12/20.
 */

public class EmptyFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_empty, container, false);
        return inflate;
    }
}
