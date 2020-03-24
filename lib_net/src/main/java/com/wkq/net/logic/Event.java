package com.wkq.net.logic;


import com.wkq.net.logic.callback.Callback;
import com.wkq.net.logic.callback.CancelCallback;
import com.wkq.net.logic.callback.FailureCallback;
import com.wkq.net.logic.callback.ProgressCallback;
import com.wkq.net.logic.callback.StateCallback;
import com.wkq.net.logic.callback.SuccessCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by xiansong on 2018/1/8.
 */

public class Event<T> {

    private static final int success_call = 1;
    private static final int failure_call = 2;
    private static final int state_call = 3;
    private static final int cancel_call = 4;
    private static final int progress_call = 5;

    public boolean active = true;
    private Logic logic;
    private List<Disposable> disposables = new ArrayList<>();
    private Map<Integer, Callback> callbackArray;

    public Event() {
        this.callbackArray = new HashMap<>();
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public Event<T> setSuccessCallback(SuccessCallback<T> callback) {
        callbackArray.put(success_call, callback);
        return this;
    }

    public Event<T> setFailureCallback(FailureCallback callback) {
        callbackArray.put(failure_call, callback);
        return this;
    }

    public Event<T> setStateCallback(StateCallback callback) {
        callbackArray.put(state_call, callback);
        return this;
    }

    public Event<T> setCancelCallback(CancelCallback callback) {
        callbackArray.put(cancel_call, callback);
        return this;
    }

    public Event<T> setProgressCallback(ProgressCallback callback) {
        callbackArray.put(progress_call, callback);
        return this;
    }

    public Event<T> addDisposable(Disposable disposable) {
        if (disposable != null) disposables.add(disposable);
        return this;
    }

    public void onSuccess(T data) {
        active = false;
        SuccessCallback<T> callback = (SuccessCallback<T>) callbackArray.get(success_call);
        if (callback != null) callback.onSuccess(data);
    }

    public void onFailure(int state, String message) {
        active = false;
        FailureCallback callback = (FailureCallback) callbackArray.get(failure_call);
        if (callback != null) callback.onFailure(state, message);
    }

    public void onState(int state) {
        StateCallback callback = (StateCallback) callbackArray.get(state_call);
        if (callback != null) callback.onState(state);
    }

    public void onCancle() {
        CancelCallback callback = (CancelCallback) callbackArray.get(cancel_call);
        if (callback != null) callback.onCancel();
        destroy();
    }

    public void onProgress(double progress) {
        ProgressCallback callback = (ProgressCallback) callbackArray.get(progress_call);
        if (callback != null) callback.onProgress(progress);
    }

    public void cencel() {
        onCancle();
    }

    private void destroy() {
        active = false;
        callbackArray.clear();
        if (disposables != null) for (Disposable disposable : disposables) disposable.dispose();
    }

    public Event<T> start() {
        if (logic != null) logic.start(this);
        return this;
    }
}
