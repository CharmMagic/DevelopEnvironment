package com.dx.bilibili.model.api;

import com.dx.bilibili.di.scope.ApiInfo;
import com.dx.bilibili.model.bean.HotListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by yangjiayi on 2017/3/25.
 * 知乎Apis
 */
public interface ZhihuApis {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 热门日报
     */
    @GET("news/hot")
    @ApiInfo(needSigned = false)
    Flowable<HotListBean> getHotList();
}
