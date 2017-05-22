package com.dx.bilibili.di.module;

import com.dx.bilibili.ui.test.fragment.NewsFragment;
import com.dx.bilibili.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiayiyang on 17/4/14.
 */

@Module
public class PageModule {

    @Provides
    @PerActivity
    NewsFragment provideNewsFragemnt(){
        return new NewsFragment();
    }

}
