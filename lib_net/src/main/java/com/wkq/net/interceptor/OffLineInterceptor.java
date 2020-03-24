package com.wkq.net.interceptor;

import android.content.Context;


import com.wkq.net.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by xiansong on 2018/4/4.
 */

public class OffLineInterceptor implements Interceptor {

    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天

    private Context mContext;

    public OffLineInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //离线的时候为7天的缓存。
        if (!NetworkUtil.isConnectInternet(mContext)) {
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + TIMEOUT_DISCONNECT)
                    .build();
        }
        return chain.proceed(request);
    }
}
