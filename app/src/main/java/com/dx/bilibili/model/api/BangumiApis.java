package com.dx.bilibili.model.api;

import com.dx.bilibili.di.scope.ApiInfo;
import com.dx.bilibili.model.bean.BangumiIndexPageResponse;
import com.dx.bilibili.model.bean.ResultObject;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jiayiyang on 17/3/26.
 */

public interface BangumiApis {

    String HOST = "http://bangumi.bilibili.com";

    /**
     *
     * https://bangumi.bilibili.com/appindex/follow_index_page?appkey=1d8b6e7d45233436&build=502000&mobi_app=android&platform=android&ts=1493967208000&sign=3eff79d895af9cf800016%20fe8f6bc6ce0
     */
    @GET("/appindex/follow_index_page")
    @ApiInfo(needSigned = true)
    Flowable<ResultObject<BangumiIndexPageResponse>> getIndexPage(@Query("appkey") String appkey,
                                                                  @Query("build") String build,
                                                                  @Query("mobi_app") String mobi_app,
                                                                  @Query("platform") String platform,
                                                                  @Query("ts") String ts
    );
}
