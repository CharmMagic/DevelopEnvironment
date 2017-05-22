package com.dx.bilibili.ui.test.activity;

import android.support.v7.app.AppCompatActivity;

import com.dx.bilibili.R;
import com.dx.bilibili.base.IBaseActivity;
import com.dx.bilibili.di.component.ActivityComponent;

public class TestNoBaseActivity extends AppCompatActivity implements IBaseActivity {

    @Override
    public boolean setCustomStatusBar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_no_base;
    }

    @Override
    public void initInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void initViewAndEvent() {

    }

    @Override
    public void initData() {

    }
}
