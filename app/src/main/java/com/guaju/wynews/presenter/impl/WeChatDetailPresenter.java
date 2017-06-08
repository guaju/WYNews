package com.guaju.wynews.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.guaju.wynews.presenter.BasePresenterI;
import com.guaju.wynews.view.BaseActivityViewI;

/**
 * Created by root on 17-6-7.
 */

public class WeChatDetailPresenter implements BasePresenterI {

    BaseActivityViewI v;
    private String url;

    @Override
    public Object getData(Object o) {
        Activity act = (Activity) o;
        Intent intent = act.getIntent();
        if (intent!=null){
            url = intent.getStringExtra("url");
            if (TextUtils.isEmpty(url)){
                if (v!=null){
                    v.showEmptyPage();
                }
            }
            else{
                v.showSuccessPage(url);
            }
        }

        return null;
    }

    @Override
    public Object getData() {
        return null;
    }

    public void attachView(BaseActivityViewI v){
        this.v=v;
    }
}
