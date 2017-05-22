package com.dx.bilibili.ui.test.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dx.bilibili.R;
import com.dx.bilibili.base.BaseMvpActivity;
import com.dx.bilibili.model.bean.WeiXinJingXuanBean;
import com.dx.bilibili.ui.test.mvp.contract.MvpStructureContract;
import com.dx.bilibili.ui.test.adapter.MvpStructureAdapter;
import com.dx.bilibili.util.StatusBarUtils;
import com.dx.bilibili.ui.test.mvp.presenter.MvpStructurePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScrollGradientActivity extends BaseMvpActivity<MvpStructurePresenter> implements MvpStructureContract.View {

    private final String TAG = ScrollGradientActivity.class.getSimpleName();

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_main_srl)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.activity_main_rv)
    RecyclerView recyclerView;

    private MvpStructureAdapter mAdapter;
    private List<WeiXinJingXuanBean.NewsList> mList = new ArrayList<>();

    @Override
    protected boolean setCustomStatusBar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_gradient;
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
        StatusBarUtils.setStatusBarMergeWithToolBar(mToolbar, this);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        mToolbar.setTitle("新闻");
        setSupportActionBar(mToolbar);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MvpStructureAdapter(mContext, mList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.loadData();
    }

    @Override
    public void updateData(List<WeiXinJingXuanBean.NewsList> list) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setRefreshing() {
        swipeRefreshLayout.setRefreshing(true);
    }

}
