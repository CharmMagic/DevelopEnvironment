package com.dx.bilibili.ui.test.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dx.bilibili.R;
import com.dx.bilibili.base.BaseMvpActivity;
import com.dx.bilibili.ui.test.mvp.contract.TestRoomContract;
import com.dx.bilibili.ui.test.mvp.presenter.TestRoomPresenter;
import com.dx.bilibili.util.StatusBarUtil;

import butterknife.BindView;

public class TestRoomActivity extends BaseMvpActivity<TestRoomPresenter> implements TestRoomContract.View {

    private final String TAG = TestRoomActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_room;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {
        //自定义statusbar样式,与toolbar融合
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        mToolbar.setTitle("测试Room");
        setSupportActionBar(mToolbar);
        mPresenter.testApi();
    }

}
