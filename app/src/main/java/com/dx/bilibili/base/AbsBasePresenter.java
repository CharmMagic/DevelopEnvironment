package com.dx.bilibili.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jiayiyang on 17/3/25.
 */

public abstract class AbsBasePresenter<T extends BaseView> {

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    public void attachView(T view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
}
