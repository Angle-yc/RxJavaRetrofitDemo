package com.angle.hshb.rxjavaretrofitdemo;

import android.app.Application;

import com.angle.hshb.rxjavaretrofitdemo.utils.BaseAppUtil;

/**
 * Created by Administrator on 2017/9/30.
 */

public class DemoApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        BaseAppUtil.init(getApplicationContext());
    }
}
