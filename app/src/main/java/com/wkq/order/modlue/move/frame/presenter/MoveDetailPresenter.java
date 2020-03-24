package com.wkq.order.modlue.move.frame.presenter;

import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Config;
import com.wkq.net.logic.Event;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.order.modlue.move.frame.view.MoveDailView;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-27
 * <p>
 * 用途:
 */


public class MoveDetailPresenter extends MvpBasePresenter<MoveDailView> {

    private Event event;

    public void getMoveDetail(MoveDetailActivity activity, String moveId) {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("append_to_response", "similar_movies,images,credits");

        event = Logic.create(moveId).action(new Logic.Action<String, BaseInfo<MoveDbMoveDetailInfo>>() {
            @Override
            public Disposable action(String data, DataCallback<BaseInfo<MoveDbMoveDetailInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getMovieDetail(data, requestMap)).subscribe(activity, callback);
            }
        }).<BaseInfo<MoveDbMoveDetailInfo>>event().setStateCallback(state -> {
            if (state == Config.STATE_LOAD) {
                if (getView() != null) getView().showLoading();
            } else if (state == Config.SUCCESS) {
                if (getView() != null) getView().hideLoading();
            }
        }).setFailureCallback((state, message) -> {
            Log.e("", "");
        }).setSuccessCallback(data -> {
            if (getView() != null) getView().setData(data);
            if (getView() != null) getView().hideLoading();
        }).start();

    }

    public void cancel() {
        if (event != null) event.cencel();
    }
}
