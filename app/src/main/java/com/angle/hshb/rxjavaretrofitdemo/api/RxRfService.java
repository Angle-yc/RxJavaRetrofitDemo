package com.angle.hshb.rxjavaretrofitdemo.api;

import com.angle.hshb.rxjavaretrofitdemo.bean.MovieData;
import com.angle.hshb.rxjavaretrofitdemo.constant.AppConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author   :  angle
 * time     :  2017-3-8
 * desc     :  网络请求的类
 * version  :  1.0
 */

public class RxRfService {
    private static RxRfService rxRfService;//私有属性
    private Retrofit retrofit;
    private RxRfApi api;
    /**
     * 构造函数
     */
    private RxRfService(){
        retrofit=new Retrofit.Builder()
                .client(ReqHelper.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConstants.BASE_URL)
                .build();
        api=retrofit.create(RxRfApi.class);
    }

    //单列
    public static RxRfService getInstance(){
        if (rxRfService==null){
            rxRfService=new RxRfService();
        }
        return rxRfService;
    }

    public void getTopMovie(Subscriber<MovieData>subscriber,int start,int count){
        api.getTopMovie(start,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
