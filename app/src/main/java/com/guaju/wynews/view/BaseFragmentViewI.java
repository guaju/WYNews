package com.guaju.wynews.view;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

/**
 * Created by root on 17-6-2.
 */

public interface BaseFragmentViewI {
   void showWrongPage(FragmentManager fm);
   void showEmptyPage(FragmentManager fm);
   void showSuccessPage(Object obj);
   void showProcessDialog();
   void hideProcessDialog();
   void showErrorNetWorkInfo(Activity context);
}
