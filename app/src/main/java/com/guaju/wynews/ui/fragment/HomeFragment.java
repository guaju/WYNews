package com.guaju.wynews.ui.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.guaju.wynews.R;
import com.guaju.wynews.base.BaseFragment;
import com.guaju.wynews.presenter.impl.HomePresenter;
import com.guaju.wynews.ui.activity.MainActivity;
import com.guaju.wynews.ui.adapter.HomeRecAdapter;
import com.guaju.wynews.view.HomeViewI;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 17-6-1.
 */

public class HomeFragment extends BaseFragment implements HomeViewI
        , BaseSliderView.OnSliderClickListener
        , ViewPagerEx.OnPageChangeListener
        ,TabHost.TabContentFactory, GestureDetector.OnGestureListener {
    private static final String TAG = "HomeFragment";
    @Bind(R.id.slider)
    public SliderLayout slider;
    @Bind(R.id.custom_indicator)
    PagerIndicator customIndicator;
    HomePresenter homePresenter = new HomePresenter();
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    @Bind(R.id.swipe_target)
    ScrollView swipeTarget;
    private HashMap viewPagerMap;
    private View v;
    private int tempPosition = 0;
    // 滑动手势
    private GestureDetector detector;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View initView() {
        v = inflater.inflate(R.layout.fragment_home, null, false);
        ButterKnife.bind(this, v);
        //to-do
        final GestureDetector mGestureDetector = new GestureDetector(
                getActivity(), this);
        MainActivity.MyOnTouchListener myOnTouchListener = new MainActivity.MyOnTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                boolean result = mGestureDetector.onTouchEvent(ev);
                return result;
            }
        };

        ((MainActivity) getActivity())
                .registerMyOnTouchListener(myOnTouchListener);
        initSlider(slider);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        HorizontalDividerItemDecoration divider = new HorizontalDividerItemDecoration
                .Builder(getActivity())
                .size(3)
                .color(Color.parseColor("#6d6d6d"))
                .margin(5, 5)
                .build();
        rv.addItemDecoration(divider);
//        swipeToLoadLayout.requestDisallowInterceptTouchEvent(false);
//        swipeToLoadLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int downY = 0,upY = 0;
//                if (event.getAction()==MotionEvent.ACTION_DOWN){
//                    downY= (int) event.getY();
//                    Log.e(TAG, "onTouch: downY="+downY+"---upY----"+upY);
//                }if (event.getAction()==MotionEvent.ACTION_UP){
//                    upY= (int) event.getY();
//                    Log.e(TAG, "onTouch: downY="+downY+"---upY----"+upY);
//                }
//                if (upY-downY>20){
//                        slider.stopAutoCycle();
//                    }
//                else{
//                   slider.startAutoCycle();
//                    }
//                    return false;
//            }
//        });
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                slider.stopAutoCycle();
                SystemClock.sleep(1000);
                swipeToLoadLayout.setRefreshing(false);
                slider.startAutoCycle();

            }
        });
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                slider.stopAutoCycle();
                SystemClock.sleep(3000);
                swipeToLoadLayout.setLoadingMore(false);

            }
        });
        homePresenter.attachView(this, getActivity());
        return v;
    }

    private void initSlider(SliderLayout slider) {
        slider.setPresetTransformer(SliderLayout.Transformer.Default);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(5000);
        slider.addOnPageChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        slider.startAutoCycle();
    }

    @Override
    public void initData() {
        super.initData();
        viewPagerMap = homePresenter.getViewPagerData();
        homePresenter.getListData();
        swipeTarget.smoothScrollTo(0, 0);
    }
    @Override
    public void showSuccessFragment(Object bean) {

    }
    @Override
    public void onStop() {
        super.onStop();
        slider.stopAutoCycle();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showVIewPager(HashMap map) {
        HashMap<String, String> map1 = map;
        for (String name : map1.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(map1.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            slider.addSlider(textSliderView);

        }
    }

    @Override
    public void showList(HomeRecAdapter adapter) {
        rv.setAdapter(adapter);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tempPosition = position;
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e(TAG, "onScroll: "+distanceY );
        if (distanceY<-10){
            slider.stopAutoCycle();
        }else{
            slider.startAutoCycle();
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public View createTabContent(String tag) {
        return null;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_custom_indicator:
//                slider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
//                break;
//            case R.id.action_custom_child_animation:
//                slider.setCustomAnimation(new ChildAnimationExample());
//                break;
//            case R.id.action_restore_default:
//                slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//                slider.setCustomAnimation(new DescriptionAnimation());
//                break;
//            case R.id.action_github:
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia/AndroidImageSlider"));
//                startActivity(browserIntent);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
