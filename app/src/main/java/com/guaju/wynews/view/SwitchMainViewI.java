package com.guaju.wynews.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by root on 17-6-1.
 */

public interface SwitchMainViewI {

    void swichPages();
    void showFragment(FragmentManager fm, Fragment fragment, String str);
}
