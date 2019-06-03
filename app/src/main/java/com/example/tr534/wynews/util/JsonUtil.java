package com.example.tr534.wynews.util;

import android.text.TextUtils;

import com.google.gson.Gson;


public class JsonUtil {
    static  Gson mGson;

    public static <T> T parseJson(String json ,Class<T> tClass){
      if(mGson == null){
          mGson = new Gson();
      }

        if(TextUtils.isEmpty(json)){
            return null ;
        }

        return mGson.fromJson(json,tClass);

    }
}
