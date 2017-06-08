package com.guaju.wynews.presenter.impl;


import android.content.Context;

import com.guaju.wynews.base.BasePrensenter;
import com.guaju.wynews.bean.WeChatNewsBean;
import com.guaju.wynews.http.WeChatApis;
import com.guaju.wynews.presenter.WeChatPresenterI;
import com.guaju.wynews.ui.fragment.WeChatFragment;
import com.guaju.wynews.view.BaseFragmentViewI;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by root on 17-6-2.
 *
 */

public class WeChatPresenter extends BasePrensenter implements WeChatPresenterI{
    private int pageId=1;
    private static final String TAG = "WeChatPresenter";
    private BaseFragmentViewI v;
    private Context mContext;
    private ArrayList<WeChatNewsBean.NewslistBean> lists=new ArrayList<>();
    @Override
    public void getData(WeChatApis weChatApis) {
        Observable<WeChatNewsBean> weChatNews = weChatApis.getWeChatNews(WeChatApis.MYKEY, pageId+"", "10");
//        weChatNews.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<WeChatNewsBean>() {
//                    @Override
//                    public void call(WeChatNewsBean weChatNewsBean) {
//                    v.showSuccessPage(weChatNewsBean.getNewslist());
//                    v.hideProcessDialog();
//                    WeChatFragment vv = (WeChatFragment)v;
//
//                    vv.wechatSrl.setRefreshing(false);
//                    }
//                });
        Subscription subscribe = weChatNews
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeChatNewsBean>() {
            @Override
            public void call(WeChatNewsBean weChatNewsBean) {
                lists.addAll(0,weChatNewsBean.getNewslist());
                v.showSuccessPage(lists);
                v.hideProcessDialog();
                WeChatFragment vv = (WeChatFragment) v;
                vv.wechatSrl.setRefreshing(false);
            }
        });
        addSubscription(subscribe);

    }

    public void attachView(BaseFragmentViewI v,Context context){
        this.v=v;
        this.mContext=context;
    }

    public  void setPageId(int pageid){
        this.pageId=pageid;
    }


}
