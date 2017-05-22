package com.dx.bilibili.di.component;

import android.app.Activity;

import com.dx.bilibili.di.module.FragmentModule;
import com.dx.bilibili.di.scope.PerFragment;
import com.dx.bilibili.ui.test.fragment.NewsFragment;
import com.dx.bilibili.ui.test.fragment.NewsPageFragment;

import dagger.Component;

/**
 * Created by jiayiyang on 17/4/14.
 */

@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
@PerFragment
public interface FragmentComponent {

    Activity getActivity();

    void inject(NewsFragment newsFragment);

    void inject(NewsPageFragment newsPageFragment);

}
