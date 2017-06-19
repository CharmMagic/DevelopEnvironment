package com.dx.bilibili.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.dx.bilibili.di.component.DaggerActivityComponent;
import com.dx.bilibili.di.module.ActivityModule;
import com.dx.bilibili.app.App;
import com.dx.bilibili.di.component.ActivityComponent;
import com.dx.bilibili.di.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by jiayiyang on 17/3/22.
 */

public abstract class BaseMvpActivity<T extends AbsBasePresenter> extends SupportActivity implements BaseView {

    @Inject
    protected T mPresenter;
    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null));
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        initViewAndEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.releaseData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        mUnBinder.unbind();
    }

    private AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 设置布局
     *
     * @return 布局Rid
     */
    protected abstract int getLayoutId();

    /**
     * 初始化dagger注入
     */
    protected abstract void initInject();

    /**
     * 初始化页面
     */
    protected abstract void initViewAndEvent();

}
