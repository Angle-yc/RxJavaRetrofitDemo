package com.angle.hshb.rxjavaretrofitdemo.api;

import com.angle.hshb.rxjavaretrofitdemo.bean.MovieData;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author   :  angle
 * time     :  2017-3-8
 * desc     :  网络请求api
 * version  :  1.0
 */

public interface RxRfApi {

    @GET("top250")
    Observable<MovieData> getTopMovie(@Query("start") int start, @Query("count") int count);

}
