package com.dx.bilibili.ui.test.activity;

import android.util.Log;

import com.dx.bilibili.R;
import com.dx.bilibili.app.ApiHelper;
import com.dx.bilibili.base.BaseActivity;
import com.dx.bilibili.model.api.AppApis;
import com.dx.bilibili.model.api.BangumiApis;
import com.dx.bilibili.model.api.LiveApis;
import com.dx.bilibili.model.bean.SearchHotResponse;
import com.dx.bilibili.model.bean.BangumiIndexPageResponse;
import com.dx.bilibili.model.bean.IndexResponse;
import com.dx.bilibili.model.bean.LiveAreasResponse;
import com.dx.bilibili.model.bean.LiveCommonResponse;
import com.dx.bilibili.model.bean.LiveRecommendResponse;
import com.dx.bilibili.model.bean.RegionResponse;
import com.dx.bilibili.model.bean.RegionShowResponse;
import com.dx.bilibili.model.bean.ResultList;
import com.dx.bilibili.model.bean.ResultObject;
import com.dx.bilibili.model.bean.SplashResponse;
import com.dx.bilibili.util.DateUtil;
import com.dx.bilibili.util.StatusBarUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TestApiActivity extends BaseActivity {

    @Inject
    AppApis appApis;
    @Inject
    BangumiApis bangumiApis;
    @Inject
    LiveApis liveApis;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_api;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void initData() {
        appApis.getRegionShow(ApiHelper.getAppKey(),ApiHelper.getBUILD(),ApiHelper.getMobiApp(), ApiHelper.getPLATFORM(), DateUtil.getSystemTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultList<RegionShowResponse>>() {
                    @Override
                    public void accept(ResultList<RegionShowResponse> regionShowResponseResultList) {
                        Log.d("misery", "regionShowResponseResultList=" + regionShowResponseResultList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getRegionShow throwable:"+throwable.getMessage());
                    }
                });

        appApis.getRegion(ApiHelper.getBUILD())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultList<RegionResponse>>() {
                    @Override
                    public void accept(ResultList<RegionResponse> regionResponseResultList) {
                        Log.d("misery", "regionResponseResultList="+ regionResponseResultList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getRegion throwable:"+throwable.getMessage());
                    }
                });

        appApis.getIndex(ApiHelper.getAppKey(), ApiHelper.getBUILD(), "1493277505", ApiHelper.getMobiApp(), "wifi", ApiHelper.getPLATFORM(), "true", DateUtil.getSystemTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultList<IndexResponse>>() {
                    @Override
                    public void accept(ResultList<IndexResponse> indexResponseResultList) {
                        Log.d("misery", "indexResponseResultList="+ indexResponseResultList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getIndex throwable:"+throwable.getMessage());
                    }
                });

        appApis.getSplash(ApiHelper.getMobiApp(), ApiHelper.getBUILD(), AppApis.CHANNEL, 1080, 1920, AppApis.VER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultList<SplashResponse>>() {
                    @Override
                    public void accept(ResultList<SplashResponse> splashResponseResultList) {
                        Log.d("misery", "splashResponseResultList="+ splashResponseResultList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getSplash throwable:"+throwable.getMessage());
                    }
                });

        liveApis.getCommon(ApiHelper.getDevice(), ApiHelper.getAppKey(), ApiHelper.getBUILD(), ApiHelper.getMobiApp(), ApiHelper.getPLATFORM(), ApiHelper.getScale(), DateUtil.getSystemTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultObject<LiveCommonResponse>>() {
                    @Override
                    public void accept(ResultObject<LiveCommonResponse> liveCommonResultList) {
                        Log.d("misery", "liveCommonResultList="+ liveCommonResultList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getCommon throwable:"+throwable.getMessage());
                    }
                });

        liveApis.getRecommend(ApiHelper.getDevice(), ApiHelper.getAppKey(), ApiHelper.getBUILD(), ApiHelper.getMobiApp(), ApiHelper.getPLATFORM(), ApiHelper.getScale(), DateUtil.getSystemTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultObject<LiveRecommendResponse>>() {
                    @Override
                    public void accept(ResultObject<LiveRecommendResponse> liveRecommendResponseResultList) {
                        Log.d("misery", "liveRecommendResponseResultList="+ liveRecommendResponseResultList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getRecommend throwable:"+throwable.getMessage());
                    }
                });

        liveApis.getAreas(ApiHelper.getDevice(), ApiHelper.getAppKey(), ApiHelper.getBUILD(), ApiHelper.getMobiApp(), ApiHelper.getPLATFORM(), ApiHelper.getScale(), DateUtil.getSystemTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultList<LiveAreasResponse>>() {
                    @Override
                    public void accept(ResultList<LiveAreasResponse> liveAreasResponseResultList) {
                        Log.d("misery", "liveAreasResponseResultList="+ liveAreasResponseResultList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getAreas throwable:"+throwable.getMessage());
                    }
                });

        bangumiApis.getIndexPage(ApiHelper.getAppKey(),ApiHelper.getBUILD(), ApiHelper.getMobiApp(), ApiHelper.getPLATFORM(), DateUtil.getSystemTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultObject<BangumiIndexPageResponse>>() {
                    @Override
                    public void accept(ResultObject<BangumiIndexPageResponse> bangumiIndexPageResponseResultObject) {
                        Log.d("misery", "bangumiIndexPageResponseResultObject="+bangumiIndexPageResponseResultObject);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getIndexPage throwable:"+throwable.getMessage());
                    }
                });

        appApis.getSerchHot(ApiHelper.getMobiApp(), ApiHelper.getBUILD(), 50, ApiHelper.getMobiApp(), ApiHelper.getPLATFORM(), DateUtil.getSystemTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultObject<SearchHotResponse>>() {
                    @Override
                    public void accept(ResultObject<SearchHotResponse> appSerchHotResponseResultObject) {
                        Log.d("misery", "appSerchHotResponseResultObject="+ appSerchHotResponseResultObject);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("misery","getSerchHot throwable:"+throwable.getMessage());
                    }
                });
    }
}
