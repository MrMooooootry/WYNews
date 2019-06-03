package com.example.tr534.wynews.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.tr534.wynews.news.news.bean.Person;

import java.io.File;

/**
 * Created by tr534 on 2018/12/19.
 */

public class  ImageUtil {
    public static boolean checkImageIsDownLoad(String image_name){
        File image = getFileByName(image_name);
        if (image.exists())
        {
            Bitmap imageBitMapByFile = getImageBitMapByFile(image);
            if (imageBitMapByFile!=null){
                return  true;
            }
        }
        return false;


    }
    public  static  File getFileByName(String imageName){
//        File SD = Environment.getExternalStorageDirectory();
//        File fileDir=new File(SD,Constant.CACHE);
//
//        File image=new File(fileDir,MD5_name+".jpg");
//        return image;
        File SD = Environment.getExternalStorageDirectory();
        File cacheFile = new File(SD, Constant.CACHE);
        File image = new File(cacheFile,imageName+".jpg");
        return  image;

    }
    public  static  Bitmap getImageBitMapByFile( File image ){

        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
       return bitmap;

    }

   Person builder=new Person.Builder().setAdddress(" ")
           .setAge(1)
           .setId(2)
           .setName("")
           .build();
    Person.Builder build=new Person.Builder();


}
