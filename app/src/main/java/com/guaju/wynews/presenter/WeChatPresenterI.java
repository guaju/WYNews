package com.guaju.wynews.presenter;

import com.guaju.wynews.http.WeChatApis;

/**
 * Created by root on 17-6-2.
 */

public interface WeChatPresenterI {

     //get data and notify view to change
    void getData(WeChatApis weChatApis);

}
