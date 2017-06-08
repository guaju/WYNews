package com.guaju.wynews.view;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.guaju.wynews.utils.NetWorkUtils;

/**
 * Created by root on 17-6-2.
 */

public class BaseFragmentView implements BaseFragmentViewI {
    @Override
    public void showWrongPage(FragmentManager fm) {

    }

    @Override
    public void showEmptyPage(FragmentManager fm) {

    }

    @Override
    public void showSuccessPage(Object bean) {

    }

    @Override
    public void showProcessDialog() {

    }

    @Override
    public void hideProcessDialog() {

    }

    @Override
    public void showErrorNetWorkInfo(final Activity context) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!NetWorkUtils.isNetworkAvailable(context)) {
                    Toast.makeText(context, "your network cannot use ,please checkout!!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
