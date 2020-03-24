package com.wkq.net.logic;


import android.util.Log;


import com.wkq.net.logic.callback.DataCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.wkq.net.logic.Config.FAIL;
import static com.wkq.net.logic.Config.SUCCESS;


public class Logic {
    private Event event;

    protected List<BaseAction> actions;

    private Object startParem;

    private Paramer paramer = new Paramer();

    protected Logic(Object startParem) {
        this.startParem = startParem;
        actions = new ArrayList<>();
    }

    public static Logic create(Object startParem) {
        return new Logic(startParem);
    }

    public static Logic create() {
        return new Logic(null);
    }

    public <param, result> Logic action(Action<param, result> action) {
        if (action == null) return this;
        actions.add(action);
        return this;
    }

    public <param, result> Logic action(ActionMerge<param, result> action) {
        if (action == null) return this;
        actions.add(action);
        return this;
    }

    private int action_index = -1;

    private boolean inStart() {
        return action_index != -1;
    }

    private <Data, Return> void next(Data data) {
        BaseAction action = actions.get(action_index++);
        BaseAction newAction = action_index < actions.size() ? actions.get(action_index) : null;
        LogicCallback<Return> callback = new LogicCallback<>(event, newAction);
        Disposable disposable = null;
        Exception exception = null;
        try {
            if (action == null) return;
            if (action instanceof Action) disposable = ((Action) action).action(data, callback);
            if (action instanceof ActionMerge) disposable = ((ActionMerge) action).action(data, paramer, callback);
        } catch (Exception e) {
            exception = e;
            Log.e("Logic", "页面逻辑出错!", e);
        }

        if (disposable != null) event.addDisposable(disposable);
        else if (exception != null) onError(FAIL, exception.getMessage(), event);
    }

    public <T> Event<T> event() {
        if (event != null) return event;
        event = new Event<T>();
        event.setLogic(this);
        return event;
    }

    //
    public void start(Event event) {
        if (event == null) return;
        if (inStart()) return;

        this.action_index = 0;
        this.event = event;

        if (event.active) {
            event.onState(Config.STATE_LOAD);
            next(startParem);
        }
    }

    public interface Execute<T> {
        T execute();
    }

    private static String getErrorMessage(Throwable throwable) {
        return throwable != null ? throwable.getMessage() : "";
    }

    public static <T> Disposable process(Execute<T> exce, DataCallback<T> callback) {
        Consumer<T> successEV = data -> {
            if (callback != null) callback.callback(SUCCESS, "", data);
        };
        Consumer<Throwable> errorEV = throwable -> {
            if (callback != null) callback.callback(FAIL, getErrorMessage(throwable), null);
        };
        return Observable
                .create((ObservableOnSubscribe<T>) emitter -> emitter.onNext(exce.execute()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(successEV, errorEV);
    }

    private interface BaseAction {
    }

    public interface Action<param, result> extends BaseAction {
        Disposable action(param data, DataCallback<result> callback);
    }

    public interface ActionMerge<param, result> extends BaseAction {
        Disposable action(param data, Paramer paramer, DataCallback<result> callback);
    }

    public class LogicCallback<T> implements DataCallback<T> {
        private BaseAction action;
        private Event event;

        private LogicCallback(Event event, BaseAction action) {
            this.event = event;
            this.action = action;
        }

        @Override
        public void callback(int code, String message, T data) {
            if (code == SUCCESS) {
                if (event.active) {
                    if (action != null) next(data);
                    else {
                        event.onState(Config.STATE_IDLE);
                        event.onSuccess(data);
                    }
                }
            } else onError(code, message, event);
        }
    }

    private void onError(int code, String message, Event event) {
        if (event.active) {
            event.onState(Config.STATE_IDLE);
            event.onFailure(code, message);
        }
    }

    public static class Paramer {
        private Map<String, Object> map = new HashMap<>();

        public void addParamer(String key, Object value) {
            map.put(key, value);
        }

        public <T> T getParamer(String key) {
            return (T) map.get(key);
        }
    }
}
