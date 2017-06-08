package com.guaju.wynews.ui.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.widget.RadioGroup;

import com.guaju.wynews.R;
import com.guaju.wynews.base.BaseActivity;
import com.guaju.wynews.ui.fragment.GANKFragment;
import com.guaju.wynews.ui.fragment.HomeFragment;
import com.guaju.wynews.ui.fragment.WYFragment;
import com.guaju.wynews.ui.fragment.WeChatFragment;
import com.guaju.wynews.view.SwitchMainViewI;

import java.util.ArrayList;


public class MainActivity extends BaseActivity implements SwitchMainViewI {
    private static final String TAG = "MainActivity";
    private String[] tags={"home","wechat","wy","gank"};
    private FragmentManager fm;
    private HomeFragment homeFragment;
    private WeChatFragment wechatFragment;
    private WYFragment wyFragment;
    private GANKFragment gankFragment;
    private RadioGroup rg;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.rg);
        swichPages();
        showFragment(fm, homeFragment, "home");



    }

    @Override
    public void initData() {

    }


    @Override
    public void swichPages() {
        fm = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        wechatFragment = new WeChatFragment();
        wyFragment = new WYFragment();
        gankFragment = new GANKFragment();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.home:
                        showFragment(fm, homeFragment, "home");
                        break;
                    case R.id.wechat:
                        showFragment(fm, wechatFragment, "wechat");
                        break;
                    case R.id.wy:
                        showFragment(fm, wyFragment, "wy");
                        break;
                    case R.id.gank:
                        showFragment(fm, gankFragment, "gank");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void showFragment(FragmentManager fm, Fragment fragment, String str) {
            FragmentTransaction ft;
            ft = fm.beginTransaction();
            for (String tag:tags){
                if (!tag.equals(str)){
                    Fragment fragmentTemp = fm.findFragmentByTag(tag);
                    if (fragmentTemp!=null){
                        ft.hide(fragmentTemp);
                    }
                }
            }
            if (fm.findFragmentByTag(str)==null){
                ft.add(R.id.fl, fragment, str);
            }
            ft.show(fragment);
            ft.commit();
    }


    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(
            10);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            listener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }

    public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener);
    }

    @Override
    public void showSuccessPage(Object... obj) {

    }

    public interface MyOnTouchListener {
        public boolean onTouch(MotionEvent ev);
    }

}
