package com.dx.bilibili.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiayiyang on 17/3/25.
 */

public abstract class AbsBasePresenter<T extends BaseView> implements BasePresenter {

    protected T mView;
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void attachView(T view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
        unSubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void register(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }
}
