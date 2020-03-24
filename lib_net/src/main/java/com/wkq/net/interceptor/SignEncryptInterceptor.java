package com.wkq.net.interceptor;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignEncryptInterceptor implements Interceptor {

    private boolean valueUrlencode = false;

    public SignEncryptInterceptor() {

    }

    public SignEncryptInterceptor(boolean valueUrlencode) {
        this.valueUrlencode = valueUrlencode;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (request.body() instanceof FormBody) {
            //处理方式 1
            Map<String, String> parameter = new HashMap<>();

            FormBody body = ((FormBody) request.body());
            FormBody.Builder builder = new FormBody.Builder();

            for (int i = 0; i < body.size(); i++) {
                parameter.put(body.name(i), body.value(i));
            }
//
//            for (Map.Entry<String, String> entity : SignUtil.getOpenSignMap(parameter, valueUrlencode).entrySet()) {
//                builder.add(entity.getKey(), entity.getValue());
//            }
            RequestBody newBody = builder.build();

            Request newRequest = request.newBuilder()
                    .method(request.method(), newBody)
                    .url(request.url())
                    .build();
            return chain.proceed(newRequest);
        } else if (request.body() instanceof MultipartBody) {
            //处理方式 2 (暂时不签名)
        } else {
            if ("POST".equals(request.method())) {
                HttpUrl.Builder authorizedUrlBuilder = request.url().newBuilder();

                Map<String, String> parameter = new HashMap<>();

                for (String key : request.url().queryParameterNames()) {
                    parameter.put(key, request.url().queryParameter(key));
                    authorizedUrlBuilder.removeAllQueryParameters(key);
                }

                FormBody.Builder builder = new FormBody.Builder();

//                for (Map.Entry<String, String> entity : SignUtil.getOpenSignMap(parameter, valueUrlencode).entrySet()) {
//                    builder.add(entity.getKey(), entity.getValue());
//                }
                RequestBody newBody = builder.build();

                Request newRequest = request.newBuilder()
                        .method(request.method(), newBody)
                        .url(authorizedUrlBuilder.build())
                        .build();
                return chain.proceed(newRequest);
            } else {
                HttpUrl.Builder authorizedUrlBuilder = request.url().newBuilder();

                Map<String, String> parameter = new HashMap<>();

                for (String key : request.url().queryParameterNames()) {
                    parameter.put(key, request.url().queryParameter(key));
                    authorizedUrlBuilder.removeAllQueryParameters(key);
                }

//                for (Map.Entry<String, String> entity : SignUtil.getOpenSignMap(parameter, valueUrlencode).entrySet()) {
//                    authorizedUrlBuilder.addQueryParameter(entity.getKey(), entity.getValue());
//                }

                authorizedUrlBuilder.scheme(request.url().scheme());
                authorizedUrlBuilder.host(request.url().host());

                Request newRequest = request.newBuilder()
                        .method(request.method(), request.body())
                        .url(authorizedUrlBuilder.build())
                        .build();
                return chain.proceed(newRequest);
            }
        }

        return chain.proceed(request);
    }


}