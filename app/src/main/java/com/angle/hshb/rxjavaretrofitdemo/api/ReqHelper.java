package com.angle.hshb.rxjavaretrofitdemo.api;

import com.angle.hshb.rxjavaretrofitdemo.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 接口工具
 */

public class ReqHelper {
    private static final int CONNECT_TIME=5;//连接超时  五秒
    public static final int REQUEST_TIME=5;//请求超时  五秒
    /**
     * 自定义日志输入
     */
    static class MyLog implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            LogUtils.i("angle", "res==" + message);
        }
    }

    public static OkHttpClient getOkHttpClient() {
        Interceptor interceptor = new TokenInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIME, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new HttpLoggingInterceptor(new MyLog()).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        return client;
    }
}
