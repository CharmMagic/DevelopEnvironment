package com.dx.bilibili.ui.test.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.dx.bilibili.R;
import com.dx.bilibili.base.BaseActivity;
import com.dx.bilibili.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TestNavigationActivity extends BaseActivity {

    @BindView(R.id.news_btn)
    Button btnNews;
    @BindView(R.id.toolbar_behavior_mvp_btn)
    Button btnToolbarBehavior;
    @BindView(R.id.status_picture_mvp_btn)
    Button btnStatusWithPicture;
    @BindView(R.id.scroll_gradient_mvp_btn)
    Button btnScrollGradient;
    @BindView(R.id.test_api_btn)
    Button btnTestApi;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_navigation;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initViewAndEvent() {
        //关闭右滑返回
        setSwipeBackEnable(false);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.toolbar_behavior_mvp_btn, R.id.news_btn, R.id.status_picture_mvp_btn, R.id.scroll_gradient_mvp_btn, R.id.test_api_btn})
    public void jumpToPage(View view){
        switch (view.getId()){
            case R.id.toolbar_behavior_mvp_btn:
                startActivity(new Intent(mContext, ToolbarBehaviorActivity.class));
                break;
            case R.id.news_btn:
                startActivity(new Intent(mContext, NewsActivity.class));
                break;
            case R.id.status_picture_mvp_btn:
                startActivity(new Intent(mContext, StatusWithPictureActivity.class));
                break;
            case R.id.scroll_gradient_mvp_btn:
                startActivity(new Intent(mContext, ScrollGradientActivity.class));
                break;
            case R.id.test_api_btn:
                startActivity(new Intent(mContext, TestApiActivity.class));
                break;
        }
    }

}
