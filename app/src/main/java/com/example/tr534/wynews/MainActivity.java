package com.example.tr534.wynews;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.tr534.wynews.news.news.fragment.MineFragment;
import com.example.tr534.wynews.news.news.fragment.NewsFragment;
import com.example.tr534.wynews.news.news.fragment.ReadingFragment;
import com.example.tr534.wynews.news.news.fragment.TopicFragment;
import com.example.tr534.wynews.news.news.fragment.VideoFragment;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        FragmentTabHost tbHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        String[] titles = getResources().getStringArray(R.array.tab_title);
        int[] icons=new int[]{R.drawable.news_selector,R.drawable.reading_selector,R.drawable.video_selector,R.drawable.topic_selector,
        R.drawable.mine_selector};
        //绑定
        tbHost.setup(this,getSupportFragmentManager(),R.id.content);
        Class[] classes=new Class[]{NewsFragment.class, ReadingFragment.class,
                VideoFragment.class, TopicFragment.class, MineFragment.class};
//        for (int i=0;i<titles.length;i++)
//        {
//            TabHost.TabSpec tmp=tbHost.newTabSpec(""+i);
//            tmp.setIndicator(getEveryView(this,titles,icons,i));
//            tbHost.addTab(tmp,classes[i],null);
//        }
        for (int i=0;i<titles.length;i++)
        {
            TabHost.TabSpec tabSpec = tbHost.newTabSpec(i + "");
            tabSpec.setIndicator(getEveryView(this,titles,icons,i));
            tbHost.addTab(tabSpec,classes[i],null);
        }

        tbHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("ljt","tabId"+tabId);
            }
        });
        tbHost.setCurrentTabByTag("0");





//
//        TabHost.TabSpec one = tbHost.newTabSpec("1");
//        one.setIndicator(getEveryView(this));
//        TabHost.TabSpec two = tbHost.newTabSpec("2");
//        two.setIndicator(getEveryView(this));
//        TabHost.TabSpec three = tbHost.newTabSpec("3");
//        three.setIndicator(getEveryView(this));

//        tbHost.addTab(one, NewsFragment.class,null);
//        tbHost.addTab(two, EmptyFragment.class,null);
//        tbHost.addTab(three, EmptyFragment.class,null);

    }
//    public  View getEveryView( Context context,String[] titles,int[] icons,int index)
//    {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View titleView = inflater.inflate(R.layout.item_title, null);
//        TextView title=(TextView)titleView.findViewById(R.id.title);
//        ImageView icon=(ImageView)titleView.findViewById(R.id.imageView);
//        title.setText(titles[index]);
//        icon.setImageResource(icons[index]);
//        return titleView;
//    }
    public View getEveryView(Context context,String[] titles,int[] icons,int index){
        LayoutInflater inflater=getLayoutInflater().from(context);
        View titleView = inflater.inflate(R.layout.item_title, null);
        TextView title = (TextView)titleView.findViewById(R.id.title);
        ImageView icon=(ImageView)titleView.findViewById(R.id.imageView);
        title.setText(titles[index]);
        icon.setImageResource(icons[index]);
        return titleView;

    }
}
