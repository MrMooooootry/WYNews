package com.example.tr534.wynews.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tr534 on 2018/12/19.
 */

public class SharePrenceUtil {
    public  static final String XML_FILE_NAME="cache";
    public static void  putString(Context context,String title,String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(title,content);
        edit.commit();
    }
    public static String  getString(Context context,String title)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(title, "");
        return string;

    }
    public static void  putInt(Context context,String title,int content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(title,content);
        edit.commit();
    }
    public static int  getInt(Context context,String title)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        int  num = sharedPreferences.getInt(title, 0);
        return num;

    }
    public static void  putLong(Context context,String title,long content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(title,content);
        edit.commit();
    }
    public static long  getLong(Context context,String title)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        long  time = sharedPreferences.getLong(title, 0);
        return time;

    }
}
