package com.dx.bilibili.ui.test.mvp.presenter;

import android.util.Log;

import com.dx.bilibili.base.AbsBasePresenter;
import com.dx.bilibili.model.api.WeChatApis;
import com.dx.bilibili.ui.test.mvp.contract.MvpStructureContract;

import javax.inject.Inject;

/**
 * Created by jiayiyang on 17/3/25.
 */

public class NoBaseMvpPresenter extends AbsBasePresenter<MvpStructureContract.View> implements MvpStructureContract.Prensenter {

    private static final String TAG = NoBaseMvpPresenter.class.getSimpleName();

    private WeChatApis weChatApis;

    @Inject
    public NoBaseMvpPresenter(WeChatApis weChatApis) {
        this.weChatApis = weChatApis;
    }

    @Override
    public void loadData() {
        Log.d("misery", "loadData()");
    }

    @Override
    public void deleteData() {

    }
}
