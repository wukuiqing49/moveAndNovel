package com.wkq.order.modlue.main.frame.presenter;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Event;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.logic.callback.FailureCallback;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.net.model.MoveDbNowPlayingInfo;
import com.wkq.order.modlue.main.frame.view.MoveDbComingView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class MoveDbComingPresenter extends MvpBasePresenter<MoveDbComingView> {

    private Event eventData;
    private Event eventBanner;

    public void getData(Context activity,int page) {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("page", page + "");


        eventData =   Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDataInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDataInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getUpComing(data)).subscribe(activity, callback);
            }
        }).<BaseInfo<MoveDataInfo>>event().setFailureCallback((state, message) -> {
            Log.e("", "");
            if (getView()!=null)getView().showFail(message);

        }).setSuccessCallback(data -> {
            if (data != null && getView() != null) getView().setData(data);

        }).start();


    }

    public void getBannerData(Context conetxt) {
        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("page", 1 + "");

        eventBanner = Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDataInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDataInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.nowPlaying(data)).subscribe(conetxt, callback);
            }
        }).<BaseInfo<MoveDataInfo>>event().setFailureCallback(new FailureCallback() {
            @Override
            public void onFailure(int state, String message) {

                if (getView()!=null)getView().showMessage(message);

            }
        }).setSuccessCallback(data -> {
            if (getView()!=null)getView().setBanner(data);
        }).start();

    }

    public void cancel() {
        if (eventData != null) eventData.cencel();
        if (eventBanner != null) eventBanner.cencel();
    }
}
