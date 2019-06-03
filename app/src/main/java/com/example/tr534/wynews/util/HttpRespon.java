package com.example.tr534.wynews.util;

import android.text.TextUtils;

/**
 * Created by tr534 on 2018/12/21.
 */

public abstract class HttpRespon<T> {
    Class<T> tClass;
    public  HttpRespon(Class<T> cls){
        this.tClass=cls;
    }
    public abstract void onError(String msg);
    public abstract void onSuccess(T t);

    public void parse(String json){
        if (TextUtils.isEmpty(json))
        {
            //请求失败
            onError("连接失败");
            return;
        }
        if (tClass==String.class)
        {
            onSuccess((T)json);
            return;
        }
        T result = JsonUtil.parseJson(json,tClass);
        if (result!=null)
        {
            onSuccess(result);
            return;

        }

        else {
            onError("json解析失败");
        }
    }


}
