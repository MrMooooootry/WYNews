package com.example.tr534.wynews.service;

import android.app.IntentService;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.example.tr534.wynews.splash.bean.Ads;
import com.example.tr534.wynews.splash.bean.AdsDetail;
import com.example.tr534.wynews.util.Constant;
import com.example.tr534.wynews.util.ImageUtil;
import com.example.tr534.wynews.util.Md5Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


/**
 * Created by tr534 on 2018/12/19.
 */

public class DownLoadImageService extends IntentService{
    public static final String ADS_DATE="ads";

    public DownLoadImageService() {
        super("DownLoadImageService");
    }


    //    public DownLoadImageService() {
//        super("DownLoadImageService");
//    }
    public  void  saveInSD( Bitmap bitmap,String MD5_name){
        if (bitmap==null)
        {
            return;
        }
        //判断手机内存卡
        Log.d("ljt",""+Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))

        {
            File SD = Environment.getExternalStorageDirectory();
            File cacheFile=new File(SD, Constant.CACHE);
            if (!cacheFile.exists())
            {
                cacheFile.mkdirs();
                Log.d("ljt","创建文件夹");
            }
            File imge=new File(cacheFile,MD5_name+".jpg");
//            File imge = ImageUtil.getFileByName(MD5_name);
            if (imge.exists())
            {
                Log.d("ljt","文件已存在");
                return;
            }
            try {
                FileOutputStream image_out=new FileOutputStream(imge);

                bitmap.compress(Bitmap.CompressFormat.JPEG,60,image_out);
                image_out.flush();
                image_out.close();
                Log.d("ljt","下载完成");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
public void  downLoadImage(String url,String MD5_name)
{
    try {
        URL uri=new URL(url);
        URLConnection urlConnection = uri.openConnection();
//        BitmapFactory.Options options=new BitmapFactory.Options();
//        options.inJustDecodeBounds=true;
        Bitmap bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
        saveInSD(bitmap,MD5_name);
        Log.d("ljt","存储完毕");
    } catch (Exception e) {
        e.printStackTrace();
    }


}


    @Override
    protected void onHandleIntent(Intent intent) {
        Ads ads = (Ads) intent.getSerializableExtra(ADS_DATE);
        List<AdsDetail> list = ads.getAds();
        for (int i=0;i<list.size();i++)
        {
            AdsDetail adsDetail = list.get(i);
            List<String> imgs = adsDetail.getRes_url();
            Log.d("ljt","url大小"+imgs.size()+"");
            Log.d(getClass().getSimpleName(),imgs.get(1));
            if (imgs!=null)
            {

                String imgUrl = imgs.get(1);
                Log.d("onHandleIntent","开始下载"+ !TextUtils.isEmpty(imgUrl));

                if (!TextUtils.isEmpty(imgUrl))
                {

                    //下载图片
                    Log.d("onHandleIntent","开始下载"+imgUrl);
                    //将图片地址转化成唯一MD5文件
                    String cache_name = Md5Helper.toMD5(imgUrl);
                    //判断是否存在

                    if (!ImageUtil.checkImageIsDownLoad(cache_name))
                    {
                        Log.d("ljt","开始下载");
                        downLoadImage(imgUrl,cache_name);
                    }else {Log.d("ljt","由于已存在所以没有下载;");}


                }
            }

        }
    }
}
