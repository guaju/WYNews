package com.guaju.wynews.presenter.impl;

import android.content.Context;

import com.guaju.wynews.presenter.HomePresenterI;
import com.guaju.wynews.ui.adapter.HomeRecAdapter;
import com.guaju.wynews.view.BaseFragmentViewI;
import com.guaju.wynews.view.HomeViewI;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 17-6-6.
 */

public class HomePresenter implements HomePresenterI {
    private BaseFragmentViewI v;
    private Context mContext;
    private HomeViewI vv;


    @Override
    public HashMap getViewPagerData() {
        String[] args=new String[]{"http://img0.pclady.com.cn/pclady/1607/19/1546921_23.jpg",
        "http://img5.duitang.com/uploads/item/201602/22/20160222162052_8jSfL.jpeg",
        "http://img5q.duitang.com/uploads/item/201504/13/20150413H2605_LYRWc.jpeg",
        "http://img3.duitang.com/uploads/item/201601/09/20160109100712_PayAn.thumb.700_0.jpeg",
        "http://img2.imgtn.bdimg.com/it/u=2893242806,1610137204&fm=23&gp=0.jpg"
        };

        HashMap<String,String> file_maps = new HashMap<String, String>();
        file_maps.put("Hannibal", args[0]);
        file_maps.put("Big Bang Theory",args[1]);
        file_maps.put("House of Cards",args[2]);
        file_maps.put("Game of Thrones", args[3]);
        file_maps.put("hehehe", args[4]);

        v.hideProcessDialog();
        if(vv!=null){
        vv.showVIewPager(file_maps);

        }
        return file_maps;
    }

    @Override
    public void getListData() {

        ArrayList<String> strings = new ArrayList<>();
        /**
         * 深入浅出RxJava(一：基础篇)
         深入浅出RxJava(二：操作符)
         深入浅出RxJava三–响应式的好处
         深入浅出RxJava四-在Android中使用响应式编程
         给 Android 开发者的 RxJava 详解
         Github项目：RxJava-Android-Samples
         101 Rx Samples
         RxJava操作符（一）Creating Observables
         知乎：谁来讲讲Rxjava、rxandroid中的操作符的作用?
         */
        strings.add("深入浅出RxJava(一：基础篇)");
        strings.add("深入浅出RxJava(二：操作符)");
        strings.add("深入浅出RxJava三–响应式的好处");
        strings.add("深入浅出RxJava四-在Android中使用响应式编程");
        strings.add("给 Android 开发者的 RxJava 详解");
        strings.add("Github项目：RxJava-Android-Samples101 Rx Samples");
        strings.add("101 Rx Samples");
        strings.add("RxJava操作符（一）Creating Observables ");
        strings.add("知乎：谁来讲讲Rxjava、rxandroid中的操作符的作用?");
        HomeRecAdapter homeRecAdapter = new HomeRecAdapter(mContext, strings);
        if(vv!=null){
            vv.showList(homeRecAdapter);
        }
    }
    public void attachView(BaseFragmentViewI v, Context context){
        this.v=v;
        this.mContext=context;
        vv = (HomeViewI) this.v;
    }
}
