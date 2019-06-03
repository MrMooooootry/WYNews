package com.example.tr534.wynews.service;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by tr534 on 2018/12/22.
 */

public class NetEaseApplication extends Application {
    //全局
    @Override
    public void onCreate() {
        super.onCreate();
        //ImageLoaderConfiguration的配置类 .createDefault 会创建一个默认的显示配置
//        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(this);
        File sd= Environment.getExternalStorageDirectory();
        File image_cache=new File(sd,"ljt");
        if (!image_cache.exists())
        {
            image_cache.mkdirs();
        }
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(image_cache))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        ImageLoader.getInstance().init(configuration);

    }
}
