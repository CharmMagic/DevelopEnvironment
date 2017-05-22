package com.dx.bilibili.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dx.bilibili.R;
import com.dx.bilibili.base.BaseActivity;
import com.dx.bilibili.base.BaseView;
import com.dx.bilibili.base.IBaseActivity;
import com.dx.bilibili.base.IBaseMvpActivity;
import com.dx.bilibili.di.component.ActivityComponent;
import com.dx.bilibili.di.component.DaggerActivityComponent;
import com.dx.bilibili.di.module.ActivityModule;
import com.dx.bilibili.util.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jiayiyang on 17/5/9.
 */

final class ActivityLifecycleManager implements Application.ActivityLifecycleCallbacks {

    private static final String ACTIVITY_BEAN = "ActivityBean";

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity instanceof IBaseActivity) {
            IBaseActivity iActivity = (IBaseActivity) activity;
            //加载布局
            View contentView = LayoutInflater.from(activity).inflate(iActivity.getLayoutId(), null);
            activity.setContentView(contentView);
            //创建变量保存实体
            ActivityBean bean = new ActivityBean();
            //依赖注入
            Unbinder unbinder = ButterKnife.bind(activity);
            bean.setUnbinder(unbinder);
            //保存变量
            activity.getIntent().putExtra(ACTIVITY_BEAN, bean);
            //设置透明状态栏
            if (!iActivity.setCustomStatusBar()) {
                setTransparentStatusBar(activity, contentView);
            }
            //初始化
            iActivity.initInject(getActivityComponent(activity));
            iActivity.initViewAndEvent();
            iActivity.initData();
        } else if (activity instanceof IBaseMvpActivity) {
            IBaseMvpActivity iActivity = (IBaseMvpActivity) activity;
            BaseView view  = (BaseView) iActivity;
            iActivity.initInject(getActivityComponent(activity));
            //创建变量保存实体
            ActivityBean bean = new ActivityBean();
            //presenter.attach
            if(iActivity.getPresenter() != null){
                iActivity.getPresenter().attachView(view);
            }
            //加载布局
            View contentView = LayoutInflater.from(activity).inflate(iActivity.getLayoutId(), null);
            activity.setContentView(contentView);
            //依赖注入
            Unbinder unbinder = ButterKnife.bind(activity);
            bean.setUnbinder(unbinder);
            //保存变量
            activity.getIntent().putExtra(ACTIVITY_BEAN, bean);
            //设置透明状态栏
            if (!iActivity.setCustomStatusBar()) {
                setTransparentStatusBar(activity, contentView);
            }
            //初始化
            iActivity.initViewAndEvent();
            iActivity.initData();
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        boolean isBaseActivity = activity instanceof BaseActivity;
        if (!isBaseActivity) {
            ActivityBean bean = activity.getIntent().getParcelableExtra(ACTIVITY_BEAN);
            bean.getUnbinder().unbind();
            if (activity instanceof IBaseMvpActivity) {
                IBaseMvpActivity iActivity = (IBaseMvpActivity) activity;
                //presenter.detach
                if(iActivity.getPresenter() != null){
                    iActivity.getPresenter().detachView();
                }
            }
        }
    }

    /**
     * 使用透明状态栏,在原来状态栏的位置添加一个大小相同的矩形View
     */
    private void setTransparentStatusBar(Activity activity, View contentView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4及以上 全透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup content = (ViewGroup) activity.findViewById(android.R.id.content);
            //生成一个状态栏大小的矩形
            View statusBarView = createStatusBarView(activity, activity.getResources().getColor(R.color.colorPrimaryDark));
            // 添加 statusBarView 到布局中
            content.addView(statusBarView, 0);
            contentView.setPadding(0, StatusBarUtils.getStatusBarHeight(activity), 0, 0);
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param color 状态栏颜色值
     * @return 状态栏矩形条
     */
    private View createStatusBarView(Context context, int color) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    private ActivityComponent getActivityComponent(Activity activity) {
        return DaggerActivityComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .activityModule(new ActivityModule(activity))
                .build();
    }

}
