package com.example.tr534.wynews.news.news.news_inner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ActivityChooserView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tr534.wynews.R;
import com.example.tr534.wynews.news.news.adapter.BnnerAdapter;
import com.example.tr534.wynews.news.news.adapter.HotAdapter;
import com.example.tr534.wynews.splash.bean.Banner;
import com.example.tr534.wynews.splash.bean.Hot;
import com.example.tr534.wynews.splash.bean.HotDetail;
import com.example.tr534.wynews.util.Constant;
import com.example.tr534.wynews.util.HttpRespon;
import com.example.tr534.wynews.util.HttpUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by tr534 on 2018/12/21.
 */

public class HotFragment extends Fragment {
    BnnerAdapter bnnerAdapter;
    public ListView listView;
    ArrayList<Banner> banners;
    ArrayList<View> views;
     ArrayList<HotDetail> mHotDetails;
    MyHandler myHandler;
    HotAdapter hotAdapter;
    LayoutInflater inflater;
    private static   final   int  INIT_SUCCESS=0;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot,container,false);
        listView = (ListView)view.findViewById(R.id.listView);
        return view;
    }
    public void initData(){
        hotAdapter=new HotAdapter(mHotDetails,getContext());
        listView.setAdapter(hotAdapter);
    }
    public void  initBanner(){
        if (null!=banners&&banners.size()>0)
        {
            Log.d("ljt",banners.size()+"banner不为空");
            Log.d("ljtbanners",banners.toString());
            for (int i=0;i<banners.size();i++)
            {
                View view=inflater.inflate(R.layout.inner_item,null);
                Log.d("ljt",view+"");
                views.add(view);
            }
        }

        bnnerAdapter=new BnnerAdapter(views,banners);
        viewPager.setAdapter(bnnerAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //轮播图
        views=new ArrayList<>();
        myHandler=new MyHandler(this);
        banners=new ArrayList<>();
        mHotDetails=new ArrayList<>();
        inflater=LayoutInflater.from(getActivity());
        View head = inflater.inflate(R.layout.include_banner, null);
        viewPager = head.findViewById(R.id.viewPager);
        listView.addHeaderView(head);
        HttpUtil httpUtil=HttpUtil.getInstance();
        httpUtil.getData(Constant.HOT_URL, new HttpRespon<Hot>(Hot.class) {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onSuccess(Hot hot) {
                // TODO: 2018/12/30  
                if (hot!=null&&hot.getT1348647909107()!=null)
                {
                    List<HotDetail> details = hot.getT1348647909107();
                    //取出包含轮播图的
                    HotDetail tmp_baner = details.get(0);
                    banners = (ArrayList<Banner>) tmp_baner.getAds();
                    banners.addAll(banners);
                    details.remove(0);
                    mHotDetails.addAll(details);
                    Log.d("ljt---",mHotDetails.toString());
                    myHandler.sendEmptyMessage(INIT_SUCCESS);



                }
            }
        });
    }

    static  class  MyHandler extends Handler{

        WeakReference<HotFragment> hotFragmentWeakReference;

        public MyHandler(HotFragment hotFragment) {
            this.hotFragmentWeakReference=new WeakReference(hotFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            HotFragment hotFragment = hotFragmentWeakReference.get();
            if (hotFragment==null)
            {
                return;
            }
            switch (msg.what)
            {
                case INIT_SUCCESS:
                    hotFragment.initData();
                    hotFragment.initBanner();
                    break;
            }
            super.handleMessage(msg);
        }


    }
}
