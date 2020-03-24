package com.wkq.net.api;

import com.wkq.net.BaseInfo;
import com.wkq.net.model.MTimeMoveDetailInfo;
import com.wkq.net.model.MoveMTimeSearchInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public interface ApiMTimeSearch {

    @POST("Search.api")
    Observable<Result<BaseInfo<MoveMTimeSearchInfo>>> search(@QueryMap Map<String, String> requestMap);

}
