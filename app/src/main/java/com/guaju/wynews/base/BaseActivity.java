package com.guaju.wynews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.guaju.wynews.R;
import com.guaju.wynews.utils.NetWorkUtils;
import com.guaju.wynews.utils.ToastUtils;
import com.guaju.wynews.view.BaseActivityViewI;
import com.guaju.wynews.widget.WYDialog;

/**
 * Created by root on 17-6-1.
 */

public abstract class BaseActivity extends FragmentActivity  implements BaseActivityViewI {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    public abstract void initView();

    public abstract void initData();

    @Override
    public void showWrongPage() {
        setContentView(R.layout.wrongpage);

    }

    @Override
    public void showEmptyPage() {
        setContentView(R.layout.emptypage);
    }


    @Override
    public void showProcessDialog() {
        WYDialog.showProcessDialog(this);

    }

    @Override
    public void hideProcessDialog() {
        WYDialog.hideProgressDialog();
    }

    @Override
    public void showErrorNetWorkInfo(final Activity context) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!NetWorkUtils.isNetworkAvailable(context)) {
                    ToastUtils.show(context, "your network cannot use ,please checkout!!!");

                }
            }
        });


    }
}
