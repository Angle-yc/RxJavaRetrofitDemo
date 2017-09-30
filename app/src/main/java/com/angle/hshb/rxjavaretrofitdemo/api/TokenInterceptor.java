package com.angle.hshb.rxjavaretrofitdemo.api;

import com.angle.hshb.rxjavaretrofitdemo.utils.DESUtil;
import com.angle.hshb.rxjavaretrofitdemo.utils.DataStorageUtils;
import com.angle.hshb.rxjavaretrofitdemo.utils.LogUtils;
import com.angle.hshb.rxjavaretrofitdemo.utils.PackagesUtils;
import com.angle.hshb.rxjavaretrofitdemo.utils.StringUtils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 自定义接口拦截器
 */
public class TokenInterceptor implements Interceptor {

    private final String TAG = "respond";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        String url = oldRequest.url().toString();
        Response response = null;

        if (!url.contains("hz/resource.ashx")
                && !url.contains("uuidinit.ashx")
                && !url.contains("verifybind.ashx")) { // 从解绑以后的接口才加token与lversion参数

            LogUtils.d(TAG, "添加公共参数");

            // 新的请求
            Request newRequest = addParam(oldRequest);
            response = chain.proceed(newRequest);

            ResponseBody value = response.body();
            byte[] resp=value.bytes();
            String json= new String(resp, "UTF-8");
            // 判断stateCode值
            try {
                JSONObject jsonObject = new JSONObject(json);
                int stateCode = jsonObject.optInt("stateCode");
                if (stateCode == 3) {
                    String token = jsonObject.optString("data");
                    LogUtils.d(TAG, "token失效，新的token：" + token);
                    DataStorageUtils.saveToken(token);
                    // token失效，重新执行请求
                    Request newTokenRequest = addParam(oldRequest);
                    response = chain.proceed(newTokenRequest);
                } else {
                    response = response.newBuilder()
                            .body(ResponseBody.create(null, resp))
                            .build();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response = chain.proceed(oldRequest);
        }

        return response;
    }

    /**
     * 添加公共参数
     *
     * @param oldRequest
     * @return
     */
    private Request addParam(Request oldRequest) {

        String encodeToken = DESUtil.encode("#JLi87Nnmlske0*d", DataStorageUtils.getToken());

        HttpUrl.Builder builder = oldRequest.url()
                .newBuilder()
                .setEncodedQueryParameter("lversion", PackagesUtils.getAppVersionName())
                .setEncodedQueryParameter("userid",DataStorageUtils.getUserId())
                .setEncodedQueryParameter("token", StringUtils.stringEncode(encodeToken.trim()));
        LogUtils.d(TAG, encodeToken);

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build();
        return newRequest;
    }
}
