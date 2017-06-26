package com.dx.bilibili.ui.test.mvp.presenter;

import android.util.Log;

import com.dx.bilibili.base.AbsBasePresenter;
import com.dx.bilibili.model.bean.WeiXinJingXuanBean;
import com.dx.bilibili.ui.test.mvp.contract.MvpStructureContract;
import com.dx.bilibili.model.api.WeChatApis;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jiayiyang on 17/3/25.
 */

public class MvpStructurePresenter extends AbsBasePresenter<MvpStructureContract.View> implements MvpStructureContract.Prensenter {

    private static final String TAG = MvpStructurePresenter.class.getSimpleName();

    private WeChatApis weChatApis;

    @Inject
    public MvpStructurePresenter(WeChatApis weChatApis) {
        this.weChatApis = weChatApis;
    }

    @Override
    public void loadData() {
        Disposable disposable = weChatApis.getWeiXinJingXuan(WeChatApis.KEY, 25, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<WeiXinJingXuanBean>() {
                    @Override
                    public void accept(@NonNull WeiXinJingXuanBean weiXinJingXuanBean) throws Exception {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeiXinJingXuanBean>() {
                    @Override
                    public void accept(@NonNull WeiXinJingXuanBean weiXinJingXuanBean) throws Exception {
                        mView.updateData(weiXinJingXuanBean.getNewslist());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery", "onError");
                    }
                });
        register(disposable);
    }

    @Override
    public void deleteData() {
//master1
        //master22
        //master33
        //master44
        //master55
        //master66
        //feature1
    }

    @Override
    public void releaseData() {

    }
}
