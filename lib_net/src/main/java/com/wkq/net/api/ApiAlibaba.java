package com.wkq.net.api;

import com.wkq.net.BaseInfo;

import com.wkq.net.model.VUserInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-26
 * <p>
 * 用途:
 */


public interface ApiAlibaba {

    @POST("VmovesAndNovel/registerUser")
    Observable<Result<BaseInfo<VUserInfo>>> registerUser( @QueryMap Map<String, String> requestMap);
    @POST("VmovesAndNovel/registerUserIMEI")
    Observable<Result<BaseInfo<VUserInfo>>> registerUserIMEI( @QueryMap Map<String, String> requestMap);

}
