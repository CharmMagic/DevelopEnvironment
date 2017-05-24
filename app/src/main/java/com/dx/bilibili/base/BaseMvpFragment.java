package com.dx.bilibili.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dx.bilibili.app.App;
import com.dx.bilibili.di.component.AppComponent;
import com.dx.bilibili.di.component.DaggerFragmentComponent;
import com.dx.bilibili.di.component.FragmentComponent;
import com.dx.bilibili.di.module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by jiayiyang on 17/4/14.
 */

abstract public class BaseMvpFragment<T extends AbsBasePresenter> extends SupportFragment implements BaseView{

    @Inject
    protected T mPresenter;
    protected Context mContext;
    private Unbinder mUnbinder;
    private View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        initViewAndEvent();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.loadData();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.releaseData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
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

}
