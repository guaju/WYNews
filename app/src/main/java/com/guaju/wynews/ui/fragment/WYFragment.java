package com.guaju.wynews.ui.fragment;

import android.view.View;

import com.guaju.wynews.R;
import com.guaju.wynews.base.BaseFragment;

/**
 * Created by root on 17-6-1.
 */

public class WYFragment extends BaseFragment {
    @Override
    public View initView() {
        View v = inflater.inflate(R.layout.fragment_wy, null, false);
        return v;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void showSuccessFragment(Object object) {

    }
}
