package com.example.tr534.wynews.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by tr534 on 2018/12/21.
 */

public class HttpUtil {
    static HttpUtil httpUtil;
    OkHttpClient okHttpClient;
    private   HttpUtil(){
        okHttpClient=new OkHttpClient();
    }
    public static HttpUtil getInstance(){
        if (httpUtil==null)
        {
            synchronized (HttpUtil.class)
            {
                if (httpUtil==null)
                {
                    httpUtil=new HttpUtil();
                }
            }
        }
        return httpUtil;
    }
    public void getData(String url, final HttpRespon respon){
        Request request = new Request.Builder()
                .url(url)
                .build();
        //开启一个异步请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                respon.onError("请求服务器失败");
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                    {
                        //请求失败
                        respon.onError("请求服务器失败");
                        return;
                    }

//
                    String date=responseBody.string();
                 respon.parse(date);

                }
            }
        });

    }
}
