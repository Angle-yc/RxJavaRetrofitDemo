package com.angle.hshb.rxjavaretrofitdemo.utils;

import android.util.Log;

import com.angle.hshb.rxjavaretrofitdemo.BuildConfig;

/**
 * 打印日志
 */

public class LogUtils {

    public static void d(String tag,String content){
        if (BuildConfig.DEBUG){
            Log.d(tag,content);
        }
    }
    public static void e(String tag,String content){
        if (BuildConfig.DEBUG){
            Log.e(tag,content);
        }
    }
    public static void i(String tag,String content){
        if (BuildConfig.DEBUG){
            Log.i(tag,content);
        }
    }

}
