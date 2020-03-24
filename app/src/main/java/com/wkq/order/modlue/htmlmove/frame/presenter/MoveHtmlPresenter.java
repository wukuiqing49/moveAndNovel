package com.wkq.order.modlue.htmlmove.frame.presenter;

import android.content.Context;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMTime;
import com.wkq.net.logic.Event;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.logic.callback.StateCallback;
import com.wkq.net.logic.callback.SuccessCallback;
import com.wkq.net.model.MTimeMoveDetailInfo;
import com.wkq.order.modlue.htmlmove.frame.view.MoveHtmlView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public class MoveHtmlPresenter extends MvpBasePresenter<MoveHtmlView> {

    private Event event;

    public void initData(Context context, String moveUrl) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("locationId", "290");
        hashMap.put("movieId", moveUrl);

        event = Logic.create(hashMap).action(new Logic.Action<Map<String, String>, BaseInfo<MTimeMoveDetailInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MTimeMoveDetailInfo>> callback) {
                return ApiRequest.serviceMT(ApiMTime.class, apiMTime -> apiMTime.getMtMoveDetail(data)).subscribe(context, callback);
            }
        }).<BaseInfo<MTimeMoveDetailInfo>>event()
                .setStateCallback(new StateCallback() {
                    @Override
                    public void onState(int state) {
                        if (getView() != null) getView().showLoading(true);
                    }
                })
                .setFailureCallback((state, message) -> {
                    if (getView() != null) getView().showLoading(false);
                    if (getView() != null) getView().showFail();
                }).setSuccessCallback(new SuccessCallback<BaseInfo<MTimeMoveDetailInfo>>() {
                    @Override
                    public void onSuccess(BaseInfo<MTimeMoveDetailInfo> data) {
                        if (getView() != null) getView().showLoading(false);
                        if (getView() != null) getView().showSuccess(data);
                    }
                }).start();
    }

    public void cancel() {
        if (event != null) event.cencel();
    }
}
