package com.guaju.wynews.view;

import android.app.Activity;

/**
 * Created by root on 17-6-2.
 */

public interface BaseActivityViewI {
   void showWrongPage();
   void showEmptyPage();
   void showSuccessPage(Object... obj);
   void showProcessDialog();
   void hideProcessDialog();
   void showErrorNetWorkInfo(Activity context);
}
