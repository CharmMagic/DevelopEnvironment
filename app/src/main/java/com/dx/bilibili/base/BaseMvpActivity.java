package com.dx.bilibili.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dx.bilibili.R;
import com.dx.bilibili.di.component.DaggerActivityComponent;
import com.dx.bilibili.di.module.ActivityModule;
import com.dx.bilibili.app.App;
import com.dx.bilibili.di.component.ActivityComponent;
import com.dx.bilibili.di.component.AppComponent;
import com.dx.bilibili.util.StatusBarUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by jiayiyang on 17/3/22.
 */

public abstract class BaseMvpActivity<T extends AbsBasePresenter> extends SwipeBackActivity implements BaseView {

    @Inject
    protected T mPresenter;
    protected Activity mContext;
    private Unbinder mUnBinder;
    private View contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        //判断是否使用默认封装的状态栏样式
        setContentView(setContentLayout());
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        if(!setCustomStatusBar()){
            setTransparentStatusBar();
        }
        initViewAndEvent();
        initData();
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
     * 设置是否自定义statusbar
     * @return true自定义,false按照默认封装的状态栏样式
     */
    protected boolean setCustomStatusBar(){
        return false;
    }

    /**
     * 设置布局
     * @return
     */
    private View setContentLayout(){
        int layoutId = getLayoutId();
        contentView = LayoutInflater.from(this).inflate(layoutId, null);
        return contentView;
    }

    /**
     * 使用透明状态栏,在原来状态栏的位置添加一个大小相同的矩形View
     */
    private void setTransparentStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4及以上 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
            //生成一个状态栏大小的矩形
            View statusBarView = createStatusBarView(getResources().getColor(R.color.colorPrimaryDark));
            // 添加 statusBarView 到布局中
            content.addView(statusBarView, 0);
            getPaddingNeedView().setPadding(0, StatusBarUtils.getStatusBarHeight(this), 0, 0);
        }
    }

    /**
     * 获取需要设置padding的view,padding值为默认构造statusBar的高度
     * @return
     */
    protected View getPaddingNeedView(){
        return contentView;
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    protected View createStatusBarView(int color) {
        // 获得状态栏高度
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
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

    protected abstract void initViewAndEvent();

    protected abstract void initData();

}
