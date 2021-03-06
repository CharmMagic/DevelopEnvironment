package com.dx.bilibili.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dx.bilibili.di.component.DaggerFragmentComponent;
import com.dx.bilibili.di.component.FragmentComponent;
import com.dx.bilibili.app.App;
import com.dx.bilibili.di.component.AppComponent;
import com.dx.bilibili.di.module.FragmentModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by jiayiyang on 17/4/14.
 */

abstract public class BaseFragment extends SupportFragment {

    protected Context mContext;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        initInject();
        initViewAndEvent();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private AppComponent getAppComponent(){
        return ((App)_mActivity.getApplication()).getAppComponent();
    }

    protected FragmentComponent getFragmentComponent(){
        return  DaggerFragmentComponent.builder()
                .appComponent(getAppComponent())
                .fragmentModule(new FragmentModule(this))
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

    /**
     * 初始化数据
     */
    protected abstract void initData();
}
