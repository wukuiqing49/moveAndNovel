package com.wkq.net.api;

import com.wkq.net.BaseInfo;
import com.wkq.net.model.MTimeMoveDetailInfo;

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


public interface ApiMTime {

//    https://ticket-api-m.mtime.cn/movie/detail.api?locationId=290&movieId=48615

    @GET("movie/detail.api")
    Observable<Result<BaseInfo<MTimeMoveDetailInfo>>> getMtMoveDetail(@QueryMap Map<String, String> requestMap);

}
