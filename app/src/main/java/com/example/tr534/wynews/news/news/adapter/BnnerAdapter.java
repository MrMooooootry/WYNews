package com.example.tr534.wynews.news.news.adapter;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tr534.wynews.R;
import com.example.tr534.wynews.splash.bean.Banner;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/23.
 */

public class BnnerAdapter extends PagerAdapter {
    ArrayList<View> views;
    ArrayList<Banner> banners;
    DisplayImageOptions mOptions;
    public BnnerAdapter( ArrayList<View> views,ArrayList<Banner> banners) {
        this.views=views;
        this.banners=banners;
        mOptions= new DisplayImageOptions.Builder().showImageForEmptyUri(android.R.mipmap.sym_def_app_icon)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View tmp = views.get(position);
        ImageView image=(ImageView)tmp.findViewById(R.id.banner_img);
        TextView title=(TextView)tmp.findViewById(R.id.banner_title);
        container.addView(tmp);
        Banner banner=banners.get(position);
        title.setText(banner.getTitle());
        Log.d("ljt",banner.getImgsrc());
        ImageLoader.getInstance().displayImage(banner.getImgsrc(),image,mOptions);
        return tmp;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
