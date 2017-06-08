package com.guaju.wynews.http;


import com.guaju.wynews.bean.WeChatNewsBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by root on 17-6-2.
 */

public interface WeChatApis {
    String HOST = "http://api.tianapi.com/";
    String MYKEY="a168a48492d240fae6fd2c6b6ea4e7b4";

    @GET("wxnew")
    Observable<WeChatNewsBean> getWeChatNews(@QueryMap HashMap<String,String> map);

    @GET("wxnew")
    Observable<WeChatNewsBean> getWeChatNews(@Query("key") String key,
                                             @Query("page") String pageindex,
                                             @Query("num") String num);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WeChatNewsBean> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
    @GET("wxnew")
    Observable<WeChatNewsBean> getWXHotSearch(@QueryMap Map<String,String> map);

}
