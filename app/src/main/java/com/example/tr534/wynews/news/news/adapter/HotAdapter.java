package com.example.tr534.wynews.news.news.adapter;

import android.content.Context;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tr534.wynews.R;

import com.example.tr534.wynews.splash.bean.HotDetail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by tr534 on 2018/12/22.
 */

public class HotAdapter extends BaseAdapter {
    ArrayList<HotDetail> hotList;
    LayoutInflater mInflater;
    DisplayImageOptions mOptions;
    public HotAdapter(ArrayList<HotDetail> list, Context context){
        this.hotList=list;
        this.mInflater=LayoutInflater.from(context);
        //建造者模式 创建一个复杂的对象
        mOptions= new DisplayImageOptions.Builder().showImageForEmptyUri(android.R.mipmap.sym_def_app_icon)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();

    }
    @Override
    public int getCount() {
        return hotList.size();
    }

    @Override
    public HotDetail getItem(int position) {
        return hotList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_hot, null);
            viewHolder=new ViewHolder();
            viewHolder.icon =(ImageView) convertView.findViewById(R.id.tv_icon);
            viewHolder.title = (TextView)convertView.findViewById(R.id.tv_title);
            viewHolder.source = (TextView)convertView.findViewById(R.id.tv_source);
            viewHolder.reply_count= (TextView)convertView.findViewById(R.id.tv_reply_Count);
            viewHolder.special = (TextView)convertView.findViewById(R.id.tv_special);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        initViews(viewHolder,hotList.get(position));


        return convertView;
    }

    private void initViews(ViewHolder viewHolder,HotDetail hotDetail) {
        viewHolder.title.setText(hotDetail.getTitle());
        viewHolder.source.setText(hotDetail.getSource());
        viewHolder.reply_count.setText(hotDetail.getReplyCount()+"评论");
        ImageLoader.getInstance().displayImage(hotDetail.getImg(),viewHolder.icon,mOptions);



    }

    class  ViewHolder{
        ImageView icon;
        TextView title;
        TextView source;
        TextView reply_count;
        TextView special;
    }
}
