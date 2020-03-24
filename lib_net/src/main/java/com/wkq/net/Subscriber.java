package com.wkq.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import androidx.annotation.IntDef;

import com.wkq.net.model.IBaseInfo;
import com.wkq.net.util.NetworkUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

public abstract class Subscriber<T extends IBaseInfo> {

    private static final String TAG = "NetLoader";

    private Response<T> response;
    private Throwable response_error;
    private Context context;
    private Config config;

    public Subscriber(@NonNull Context context) {
        this.context = context;
        this.config = new Config();
        init();
    }

    public Subscriber(@NonNull Context context, @NonNull Config config) {
        this.context = context;
        this.config = config;
        init();
    }

    private String reloadString(String str, int defRes) {
        return TextUtils.isEmpty(str) ? context.getString(defRes) : str;
    }

    private void init() {
        config.load_success_message = reloadString(config.load_success_message, R.string.load_success_message);
        config.load_failure_message = reloadString(config.load_failure_message, R.string.load_failure_message);
        config.load_error_message = reloadString(config.load_error_message, R.string.load_error_message);
        config.no_network_message = reloadString(config.no_network_message, R.string.no_network_message);
        config.unknown_error_message = reloadString(config.no_network_message, R.string.unknown_error_message);
    }

    public static Config newConfig() {
        return new Config();
    }

    public Consumer<Result<T>> next = new Consumer<Result<T>>() {
        @Override
        public void accept(Result<T> data) throws Exception {
            if (data == null) return;
            if (data.isError()) response_error = data.error();
            else response = data.response();
        }
    };

    public Consumer<Throwable> error = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable e) throws Exception {
            Log.d(TAG, "onError ", e);
            if (context != null && !NetworkUtil.isConnectInternet(context))
                onNetworkNoConnected();
            else {
                onError(config.load_error_code, e != null ? e.getMessage() : config.load_error_message);
            }
        }
    };

    public Action complete = new Action() {
        @Override
        public void run() throws Exception {
             if (response == null) {
                if (!NetworkUtil.isConnectInternet(context)) onNetworkNoConnected();
                else if (response_error != null) {
                    onError(config.load_error_code, config.load_error_message);
                    Log.d(TAG, "onError ", response_error);
                } else onError(config.unknown_error_code, config.unknown_error_message);
            } else {
                T pageData = response.body();

                if (pageData != null && config.load_success_code.equals(pageData.getCode()))
                    onCompleted(config.load_success_code, config.load_success_message, pageData);
                else if (pageData != null)
                    if (config.customLoadResponse != null && config.custom_map.containsKey(pageData.getCode()))
                        config.customLoadResponse.onCustomEvent(pageData.getCode(), pageData.getMessage(), pageData);
                    else onError(pageData.getCode(), pageData.getMessage());
                else if (context != null && !NetworkUtil.isConnectInternet(context))
                    onNetworkNoConnected();
                else onError(config.load_failure_code, config.load_failure_message);
            }
        }
    };

    public Consumer<Disposable> subscribe = new Consumer<Disposable>() {
        @Override
        public void accept(@NonNull Disposable disposable) throws Exception {
            onStart();
        }
    };

    public abstract void onCompleted(String code, String message, T data);

    public abstract void onError(String code, String message);

    public void onNetworkNoConnected() {
        onError(config.no_network_code, config.no_network_message);
    }

    public void onStart() {
    }

    public interface CustomLoadResponse {
        void onCustomEvent(String code, String message, IBaseInfo pageData);
    }

    public static class Config {

        @IntDef({Config.response_success, Config.response_failure, Config.response_error, Config.response_no_network, Config.response_unknown})
        @Retention(RetentionPolicy.SOURCE)
        @interface RESPONSE_TYPE {
        }

        public static final int response_success = 200;
        public static final int response_failure = 1;
        public static final int response_error = 2;
        public static final int response_no_network = 3;
        public static final int response_unknown = 4;

        public static final String default_success_code = "200";
        public static final String default_failure_code = "-1";
        public static final String default_error_code = "-2";
        public static final String default_no_network_code = "-3";
        public static final String default_unknown_error_code = "-4";

        private String load_success_code = default_success_code;
        private String load_success_message;

        private String load_failure_code = default_failure_code;
        private String load_failure_message;

        private String load_error_code = default_error_code;
        private String load_error_message;

        private String no_network_code = default_no_network_code;
        private String no_network_message;

        private String unknown_error_code = default_unknown_error_code;
        private String unknown_error_message;

        /**
         * Custom event map , value{code : message}
         */
        private Map<String, String> custom_map = new HashMap<>();

        private CustomLoadResponse customLoadResponse;

        private Config() {
        }

        public Config setCustomLoadResponse(CustomLoadResponse customLoadResponse) {
            this.customLoadResponse = customLoadResponse;
            return this;
        }

        public Config putInfo(@RESPONSE_TYPE int type, String code, String message) {
            switch (type) {
                case response_success:
                    this.load_success_code = code;
                    this.load_success_message = message;
                    break;
                case response_failure:
                    this.load_failure_code = code;
                    this.load_failure_message = message;
                    break;
                case response_error:
                    this.load_error_code = code;
                    this.load_error_message = message;
                    break;
                case response_no_network:
                    this.no_network_code = code;
                    this.no_network_message = message;
                    break;
                case response_unknown:
                    this.unknown_error_code = code;
                    this.unknown_error_message = message;
                    break;
            }
            return this;
        }

        public Config removeInfo(@RESPONSE_TYPE int type) {
            switch (type) {
                case response_success:
                    this.load_success_code = default_success_code;
                    this.load_success_message = null;
                    break;
                case response_failure:
                    this.load_failure_code = default_failure_code;
                    this.load_failure_message = null;
                    break;
                case response_error:
                    this.load_error_code = default_error_code;
                    this.load_error_message = null;
                    break;
                case response_no_network:
                    this.no_network_code = default_no_network_code;
                    this.no_network_message = null;
                    break;
                case response_unknown:
                    this.unknown_error_code = default_unknown_error_code;
                    this.unknown_error_message = null;
                    break;
            }
            return this;
        }

        public Config putCustomResponse(String code, String message) {
            custom_map.put(code, message);
            return this;
        }

        public Config removeCustomResponse(String code) {
            custom_map.remove(code);
            return this;
        }

    }
}