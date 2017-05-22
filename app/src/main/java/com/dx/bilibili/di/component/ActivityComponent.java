package com.dx.bilibili.di.component;

import com.dx.bilibili.di.module.ActivityModule;
import com.dx.bilibili.ui.test.activity.NewsActivity;
import com.dx.bilibili.ui.test.activity.TestApiActivity;
import com.dx.bilibili.ui.test.activity.TestNoBaseActivity;
import com.dx.bilibili.ui.test.activity.TestNoBaseMvpActivity;
import com.dx.bilibili.ui.test.activity.ToolbarBehaviorActivity;
import com.dx.bilibili.di.module.PageModule;
import com.dx.bilibili.di.scope.PerActivity;
import com.dx.bilibili.ui.test.activity.ScrollGradientActivity;
import com.dx.bilibili.ui.test.activity.StatusWithPictureActivity;

import dagger.Component;

/**
 * Created by jiayiyang on 17/3/23.
 */

@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, PageModule.class})
@PerActivity
public interface ActivityComponent {

    void inject(NewsActivity newsActivity);

    void inject(ToolbarBehaviorActivity toolbarBehaviorActivity);

    void inject(StatusWithPictureActivity statusWithPictureActivity);

    void inject(ScrollGradientActivity scrollGradientActivity);

    void inject(TestApiActivity testApiActivity);

    void inject(TestNoBaseActivity testNoBaseActivity);

    void inject(TestNoBaseMvpActivity testNoBaseMvpActivity);

}
