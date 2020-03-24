package com.wkq.order.modlue.main.frame.presenter;

import android.content.Context;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Event;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.order.modlue.main.frame.view.MoveTopView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveTopPresenter extends MvpBasePresenter<MoveTopView> {


    private Event event;

    public void getData(Context context, int page) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("page", page + "");

        event = Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDataInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDataInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getTopRated(data)).subscribe(context, callback);
            }
        }).<BaseInfo<MoveDataInfo>>event().setFailureCallback((state, message) -> {
            if (getView() != null) getView().showMessage(message);

        }).setSuccessCallback(data -> {

            if (getView() != null) getView().setData(data);


        }).start();
    }

    public void cancel() {
        if (event != null) event.cencel();
    }

}
