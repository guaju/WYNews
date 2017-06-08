package com.guaju.wynews.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.guaju.wynews.R;
import com.guaju.wynews.base.BaseFragment;
import com.guaju.wynews.bean.WeChatNewsBean;
import com.guaju.wynews.http.HttpUtils;
import com.guaju.wynews.presenter.impl.WeChatPresenter;
import com.guaju.wynews.ui.adapter.WeChatRecAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 17-6-1.
 */

public class WeChatFragment extends BaseFragment {
    private static final String TAG = "WeChatFragment";
    @Bind(R.id.wechat_rc)
    RecyclerView wechatRc;
    @Bind(R.id.wechat_srl)
    public SwipeRefreshLayout wechatSrl;
    @Bind(R.id.refresh)
    ImageButton refresh;
    @Bind(R.id.top)
    ImageButton top;
    private WeChatPresenter wechatPresenter = new WeChatPresenter();
    int count = 0;
    int pageId = 1;
    private WeChatRecAdapter weChatRecAdapter;

    @Override
    public View initView() {
        View v = inflater.inflate(R.layout.fragment_wechat, null, false);
        ButterKnife.bind(this, v);
        wechatRc.setLayoutManager(new LinearLayoutManager(getActivity()));
        HorizontalDividerItemDecoration divider = new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(Color.GRAY)
                .size(5)
                .margin(10, 10)
                .build();
        wechatRc.addItemDecoration(divider);
        wechatPresenter.attachView(this, getActivity());

        wechatSrl.setColorSchemeColors(
                getActivity().getResources().getColor(R.color.gray),
                getActivity().getResources().getColor(R.color.orange),
                getActivity().getResources().getColor(R.color.purple),
                getActivity().getResources().getColor(R.color.pink)
                , getActivity().getResources().getColor(R.color.pink)
                , getActivity().getResources().getColor(R.color.pink)
                , getActivity().getResources().getColor(R.color.pink));
        wechatSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //SystemClock.sleep(3000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pageId += 1;
                                wechatPresenter.setPageId(pageId);
                                wechatPresenter.getData(HttpUtils.getInstance().getWeChatApis());
                            }
                        });

                    }
                }).start();


            }
        });
        return v;
    }

    @Override
    public void initData() {
        super.initData();
        setPresenter(wechatPresenter);
        wechatPresenter.getData(HttpUtils.getInstance().getWeChatApis());
    }


    @Override
    public void showSuccessFragment(Object bean) {
        ArrayList<WeChatNewsBean.NewslistBean> lists = (ArrayList<WeChatNewsBean.NewslistBean>) bean;
        if (weChatRecAdapter == null) {
            weChatRecAdapter = new WeChatRecAdapter(getActivity(), lists);
            wechatRc.setAdapter(weChatRecAdapter);
        } else {
            weChatRecAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.refresh, R.id.top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.refresh:
                wechatSrl.setRefreshing(true);
                pageId += 1;
                wechatPresenter.setPageId(pageId);
                wechatPresenter.getData(HttpUtils.getInstance().getWeChatApis());

                break;
            case R.id.top:
                wechatRc.smoothScrollToPosition(0);
                break;
        }
    }
}
