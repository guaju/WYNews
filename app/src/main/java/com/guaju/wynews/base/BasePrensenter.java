package com.guaju.wynews.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by root on 17-6-6.
 */

public class BasePrensenter  {

    private CompositeSubscription mCompositeSubscription;
    /**
     * 事件订阅
     * */
    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    public void unsubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        this.mCompositeSubscription=null;
    }
}


