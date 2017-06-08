package com.guaju.wynews.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 17-6-2.
 */

public class HttpUtils {
    private static HttpUtils httpUtils;

    private String baseUrl="";
    private final WeChatApis weChatApis;

    private HttpUtils(){
    Retrofit weChatRetrofit= new Retrofit.Builder()
                .baseUrl(WeChatApis.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        weChatApis = weChatRetrofit.create(WeChatApis.class);
    }

    public static HttpUtils getInstance(){
        if (httpUtils==null){
            synchronized (HttpUtils.class){
            if (httpUtils==null){
            httpUtils=new HttpUtils();
                }
            }
        }
        return httpUtils;
    }
   public WeChatApis getWeChatApis(){
       return weChatApis;
   }

}
