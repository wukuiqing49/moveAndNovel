package com.wkq.order.modlue.main.frame.presenter;

import android.app.Activity;
import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiDemo;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.logic.callback.FailureCallback;
import com.wkq.net.logic.callback.SuccessCallback;
import com.wkq.net.model.MovieInTheatersBean;
import com.wkq.net.util.StringUtils;
import com.wkq.order.modlue.main.frame.view.MoviesView;
import com.wkq.net.model.MoviesInfo;

import java.net.URLEncoder;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoviesPresenter extends MvpBasePresenter<MoviesView> {

    public void initData(Activity activity) {

        String urlStr = "";
        try {
            urlStr = StringUtils.encode("北京");
        } catch (Exception e) {
        }
//        0df993c66c0c636e29ecbb5344252a4a
        HashMap<String, String> requestMap = new HashMap<>();
//        requestMap.put("apikey", "0df993c66c0c636e29ecbb5344252a4a");

        Logic.create(requestMap).action(new Logic.Action<HashMap<String, String>, BaseInfo<MovieInTheatersBean>>() {
            @Override
            public Disposable action(HashMap<String, String> data, DataCallback<BaseInfo<MovieInTheatersBean>> callback) {
                return ApiRequest.serviceDouBan(ApiDemo.class, apiDemo -> apiDemo.getMovieInTheaters()).subscribe(activity, callback);

            }
        }).<BaseInfo<MovieInTheatersBean>>event().setFailureCallback(new FailureCallback() {
            @Override
            public void onFailure(int state, String message) {
                Log.e("", "");

            }
        }).setSuccessCallback(new SuccessCallback<BaseInfo<MovieInTheatersBean>>() {
            @Override
            public void onSuccess(BaseInfo<MovieInTheatersBean> data) {


                if (data != null && getView() != null) getView().setData(data);
            }
        }).start();

    }
}
