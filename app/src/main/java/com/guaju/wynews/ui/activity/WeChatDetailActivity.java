package com.guaju.wynews.ui.activity;

import android.app.ActionBar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.guaju.wynews.R;
import com.guaju.wynews.app.App;
import com.guaju.wynews.base.BaseActivity;
import com.guaju.wynews.presenter.impl.WeChatDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 17-6-5.
 */

public class WeChatDetailActivity extends BaseActivity {
    @Bind(R.id.wechatwebview)
    WebView wechatwebview;
    WeChatDetailPresenter weChatDetailPresenter =new WeChatDetailPresenter();


    @Override
    public void initView() {
        setContentView(R.layout.activity_wechatdetail);
        ButterKnife.bind(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        WebSettings settings = wechatwebview.getSettings();
        settings.setJavaScriptEnabled(true);

        weChatDetailPresenter.attachView(this);
    }
    @Override
    public void initData() {
        weChatDetailPresenter.getData(this);
    }
    @Override
    public void showSuccessPage(Object ...obj) {
        wechatwebview.loadUrl((String)obj[0]);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getRefWatcher(this).watch(this);
    }
}
