package com.wkq.order.modlue.login.frame.presenter;

import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiAlibaba;
import com.wkq.net.logic.Config;
import com.wkq.net.logic.Event;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.model.VUserInfo;
import com.wkq.order.modlue.login.frame.view.LoginView;
import com.wkq.order.modlue.login.ui.activity.LoginActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-26
 * <p>
 * 用途:
 */


public class LoginPresenter extends MvpBasePresenter<LoginView> {

    private Event event;

    public void registerUSer(LoginActivity activity, String userPhoneNum, String userPwq, String userIMEI) {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("userPhoneNum", userPhoneNum);
        requestMap.put("userPwd", userPwq);
        requestMap.put("userIMEI", userIMEI);

        event = Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<VUserInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<VUserInfo>> callback) {
                return ApiRequest.serviceAlbaba(ApiAlibaba.class, apiMoveDb -> apiMoveDb.registerUserIMEI(requestMap)).subscribe(activity, callback);
            }
        }).<BaseInfo<VUserInfo>>event().setStateCallback(state -> {
            if (state == Config.STATE_LOAD) {
                if (getView() != null) getView().showLoading();
            } else if (state == Config.SUCCESS) {
                if (getView() != null) getView().hideLoading();
            }
        }).setFailureCallback((state, message) -> {
            if (getView() != null) getView().hideLoading();
            if (getView() != null) getView().showMessage(message);

        }).setSuccessCallback(data -> {
            if (getView() != null) getView().setData(data);
            if (getView() != null) getView().hideLoading();
        }).start();

    }

    public void cancel() {
        if (event != null) event.cencel();
    }
}
