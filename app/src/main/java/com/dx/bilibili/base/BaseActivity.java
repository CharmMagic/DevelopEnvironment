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

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by jiayiyang on 17/3/22.
 */

public abstract class BaseActivity extends SwipeBackActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null));
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        initInject();
        initViewAndEvent();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
     * @return
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

    /**
     * 初始化数据
     */
    protected abstract void initData();


}
