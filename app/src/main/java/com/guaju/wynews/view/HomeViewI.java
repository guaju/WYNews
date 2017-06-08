package com.guaju.wynews.view;

import com.guaju.wynews.ui.adapter.HomeRecAdapter;

import java.util.HashMap;

/**
 * Created by root on 17-6-6.
 */

public interface HomeViewI extends BaseFragmentViewI {

   void showVIewPager(HashMap map);
   void showList(HomeRecAdapter adapter);
}
