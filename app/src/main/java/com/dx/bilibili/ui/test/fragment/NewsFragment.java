package com.dx.bilibili.ui.test.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dx.bilibili.R;
import com.dx.bilibili.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jiayiyang on 17/4/14.
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private NewsPagerAdapter adapter;
    private List<NewsPageFragment> mFagments = new ArrayList<>();
    private String[] mTitles = {"Tab1", "Tab2", "Tab333344", "Tab4", "Tab5", "Tab6"};

    @Override
    protected int setContentView() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {
        for (String s : mTitles) {
            mFagments.add(new NewsPageFragment());
        }
        adapter = new NewsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager, mTitles);
    }

    @Override
    protected void initData() {

    }

    private class NewsPagerAdapter extends FragmentPagerAdapter {

        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFagments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }

}
