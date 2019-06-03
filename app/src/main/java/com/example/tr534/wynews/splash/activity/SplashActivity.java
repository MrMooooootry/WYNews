package com.example.tr534.wynews.splash.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.tr534.wynews.MainActivity;
import com.example.tr534.wynews.R;
import com.example.tr534.wynews.splash.OnTimeClickListener;
import com.example.tr534.wynews.service.DownLoadImageService;
import com.example.tr534.wynews.splash.TimeView;
import com.example.tr534.wynews.splash.bean.Action;
import com.example.tr534.wynews.splash.bean.Ads;
import com.example.tr534.wynews.splash.bean.AdsDetail;
import com.example.tr534.wynews.util.Constant;
import com.example.tr534.wynews.util.HttpRespon;
import com.example.tr534.wynews.util.HttpUtil;
import com.example.tr534.wynews.util.ImageUtil;
import com.example.tr534.wynews.util.JsonUtil;
import com.example.tr534.wynews.util.Md5Helper;
import com.example.tr534.wynews.util.SharePrenceUtil;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by tr534 on 2018/12/19.
 */

public class SplashActivity extends Activity {
    public int index=0;
    public ImageView ads_view;
    MyHandler mHandler;
    int length=600;
    int space=1000;
    int now=0;
    public int total;
    public TimeView timeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slpash);

       initView();

        getADs();


        showImage();

    }

    private void initView() {
        ads_view = (ImageView) findViewById(R.id.ads);
        timeView = (TimeView)findViewById(R.id.timer);
        timeView.setVisibility(View.GONE);
        timeView.setmListener(new OnTimeClickListener() {
            @Override
            public void onClickTime(View view) {
                mHandler.removeCallbacks(freshRing);
                GoToMain();
            }
        });

        total = length/space;
        mHandler=new MyHandler(this);

    }

    public  void GoToMain(){
        Intent intent=new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    Runnable freshRing=new Runnable() {
        @Override
        public void run() {
            Message message=mHandler.obtainMessage(0);
            message.arg1=now;

            mHandler.sendMessage(message);
            mHandler.postDelayed(this,space);
            now++;
        }
    };
    Runnable NPGoToMain=new Runnable() {
        @Override
        public void run() {
            Intent intent=new Intent();
            intent.setClass(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
    public  void showImage(){
        //读出缓存
        Log.d("ljt--Img","begin");
        String cache = SharePrenceUtil.getString(SplashActivity.this, Constant.JSON_CACHE);
        if (!TextUtils.isEmpty(cache))
        {
            timeView.setVisibility(View.VISIBLE);
            mHandler.post(freshRing);
            Ads ads=JsonUtil.parseJson(cache,Ads.class);
            index = SharePrenceUtil.getInt(SplashActivity.this, Constant.ADS_INDEX);
            if (null==ads)
            {
                return;

            }
            List<AdsDetail> list = ads.getAds();

            if (list!=null&&list.size()>0)
            {
                 total=list.size();
//                Log.d("ljt---",total+"");
                Random random=new Random();
                int i = random.nextInt(total);
                final AdsDetail adsDetail = list.get(i);
                List<String> res_url = adsDetail.getRes_url();
//                Log.d(getClass().getSimpleName(),"ljt "+res_url.get(1));
                if (res_url!=null&&!TextUtils.isEmpty(res_url.get(1)))
                {    //获取URL
                    String url=res_url.get(1);
                    //文件名
                    String imageNmae = Md5Helper.toMD5(url);
//                    Log.d("ljt---imageName",imageNmae);


                    File image = ImageUtil.getFileByName(imageNmae);

                    if (image.exists())
                    {
//                        Log.d("ljt---imageName","imageExists");
                        Bitmap bitmap=ImageUtil.getImageBitMapByFile(image);
                        ads_view.setImageBitmap(bitmap);
                        ads_view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Action action_params = adsDetail.getAction_params();
                                index++;
                                SharePrenceUtil.putInt(SplashActivity.this,Constant.ADS_INDEX,index);

                                if (action_params!=null&&!TextUtils.isEmpty(action_params.getLink_url()))
                                {
                                    Log.d("ljt--Img","getImage");
                                    Intent intent=new Intent();
                                    intent.setClass(SplashActivity.this,WebViewActivity.class);
                                    intent.putExtra(WebViewActivity.WER_ACTION,action_params);
                                    startActivity(intent);
                                    finish();
                                    mHandler.removeCallbacks(freshRing);
                                }

                            }
                        });

                    }
                }
            }

        }
        else {
            mHandler.postDelayed(NPGoToMain,3000);

        }





    }
    public void getADs(){


        Log.d("Http","getADs");


        String json_cache = SharePrenceUtil.getString(SplashActivity.this, Constant.JSON_CACHE);

        if (TextUtils.isEmpty(json_cache))
        {
            httpRequest();
        }
        else {

            int out_time = SharePrenceUtil.getInt(SplashActivity.this, Constant.JSON_CACHE_TIME_OUT);
            long lastTime = SharePrenceUtil.getLong(SplashActivity.this, Constant.JSON_CACHE_TIME_NOW);
            long nowTime=System.currentTimeMillis();
            if ((nowTime-lastTime)>out_time*60*1000)
            {
                httpRequest();
            }
        }
    }


    public void httpRequest() {
        Log.d("Http","HttpConnect");
         final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.SPLASH_URL)
                .build();
        //开启一个异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                    {
                        //请求失败
                        Log.d(getClass().getSimpleName(),"请求失败");
                    }

//                    Headers responseHeaders = response.headers();
//                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                    }
                    //请求成功

//                    Log.d("-----------",responseBody.string());
                    String date=responseBody.string();
                    Log.d("ljt59731",date);
                    Ads ads = JsonUtil.parseJson(date, Ads.class);
                    Log.d(getClass().getSimpleName(),"请求成功后"+date.toString());

                    if (null!=ads)
                    {
                        //json
                        SharePrenceUtil.putString(SplashActivity.this,Constant.JSON_CACHE,date);
                        // 超时时间
                        SharePrenceUtil.putInt(SplashActivity.this,Constant.JSON_CACHE_TIME_OUT,
                                ads.getNext_req());
                        //缓存上次请求成功的时间
                        SharePrenceUtil.putLong(SplashActivity.this,Constant.JSON_CACHE_TIME_NOW,
                              System.currentTimeMillis());

                        Log.d("---ljt",ads.toString());
                        Intent intent=new Intent();
                        intent.setClass(SplashActivity.this, DownLoadImageService.class);
                        intent.putExtra(DownLoadImageService.ADS_DATE,ads);
                        startService(intent);

                    }
                    else {
                        //请求失败
                        Log.d("---ljt","转换失败");
                    }
                }
            }
        });
//        HttpUtil util = HttpUtil.getInstance();
//        util.getData(Constant.SPLASH_URL, new HttpRespon<String>(String.class) {
//            @Override
//            public void onError(String msg) {
//                Log.i("ljt","error msg"+msg);
//            }
//
//            @Override
//            public void onSuccess(String json) {
//                Ads ads = JsonUtil.parseJson(json, Ads.class);
//
//                if (null != ads) {
//                    //请求成功
//                    Log.i("ljt", ads.toString());
//
//                    //http成功后,缓存json
//                    SharePrenceUtil.putString(SplashActivity.this, Constant.JSON_CACHE, json);
//                    //http成功后,缓存超时时间
//                    SharePrenceUtil.putInt(SplashActivity.this, Constant.JSON_CACHE_TIME_OUT, ads.getNext_req());
//                    //http成功后,缓存上次请求成功的时间
//                    SharePrenceUtil.putLong(SplashActivity.this, Constant.JSON_CACHE_TIME_NOW, System.currentTimeMillis());
//
//                    Intent intent = new Intent();
//                    intent.setClass(SplashActivity.this, DownLoadImageService.class);
//                    intent.putExtra(DownLoadImageService.ADS_DATE, ads);
//                    startService(intent);
//
//                }}
//        });
    }
    static class MyHandler extends Handler{
        WeakReference<SplashActivity> splashActivityWeakReference;
        public MyHandler(SplashActivity activity){
            this.splashActivityWeakReference=new WeakReference<SplashActivity>(activity);

        }
        @Override
        public void handleMessage(Message msg) {
            SplashActivity splashActivity = splashActivityWeakReference.get();
            if (splashActivity==null)
            {
                return;
            }

            switch (msg.what)
            {
                case 0:
                    int now = msg.arg1;
                    if (now<=splashActivity.total)
                    {
                        splashActivity.timeView.setProgress(splashActivity.total,now);

                    }
                    else {
                        this.removeCallbacks(splashActivity.freshRing);
                        splashActivity.GoToMain();

                    }

                    break;
            }

        }
    }
}
