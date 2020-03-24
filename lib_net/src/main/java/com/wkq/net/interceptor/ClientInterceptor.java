package com.wkq.net.interceptor;


import android.content.Context;


import com.wkq.net.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class ClientInterceptor implements Interceptor {

    private Context mContext;

    ClientInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);
        if (NetworkUtil.isConnectInternet(mContext)) {
            int maxAge = 60 * 60; // read from cache for 1 minute
            response.newBuilder()
                    .addHeader("Cache-Control", "public, max-age=" + maxAge)
                    .build();

        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            response.newBuilder()
                    .addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }

        return response;
    }
}