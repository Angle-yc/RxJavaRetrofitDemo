package com.angle.hshb.rxjavaretrofitdemo.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

/**
 * 工具类
 */
public class BaseAppUtil {
    private static Context sContext;
    private static Thread mUiThread;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * 在application的oncreate（）调用
     */
    public static void init(Context context) {
        sContext = context;
        mUiThread = Thread.currentThread();
    }

    public static Context getAppContext() {
        return sContext;
    }

    /**
     * 判断是否是主线程
     *
     * @return
     */
    public static boolean isUIThread() {
        return mUiThread == Thread.currentThread();
    }

    /**
     * 获取assets的资源
     *
     * @return
     */
    public static AssetManager getAsset() {
        return sContext.getAssets();
    }

    /**
     * 获取res资源
     *
     * @return
     */
    public static Resources getResources() {
        return sContext.getResources();
    }

    public static void runOnUI(Runnable r) {
        sHandler.post(r);
    }

    public static void runOnUIDelayed(long delayMills, Runnable r) {
        sHandler.postDelayed(r, delayMills);
    }

    public static void runOnUIDelayed(Runnable r, long delayMills) {
        sHandler.postDelayed(r, delayMills);
    }

    public static void removeRunOnUI(Runnable r) {
        if (r == null) {
            sHandler.removeCallbacksAndMessages(null);
        } else {
            sHandler.removeCallbacks(r);
        }
    }

}
