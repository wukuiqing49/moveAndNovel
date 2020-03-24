package com.wkq.net.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-22
 * <p>
 * 用途:
 */

public abstract class MoveDbResponseBodyInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        String url = request.url().toString();
        HttpUrl.Builder authorizedUrlBuilder = request.url().newBuilder();
        authorizedUrlBuilder.addQueryParameter("api_key", "9d16633c17134f489c1f643b3b4e274d");
        authorizedUrlBuilder.addQueryParameter("language", "zh");
        authorizedUrlBuilder.addQueryParameter("region", "CN");


        Request newRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(authorizedUrlBuilder.build())
                .build();
        Response response = chain.proceed(newRequest);
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            long contentLength = responseBody.contentLength();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);

            Buffer buffer = source.buffer();

            if ("gzip".equals(response.headers().get("Content-Encoding"))) {
                GzipSource gzippedResponseBody = new GzipSource(buffer.clone());
                buffer = new Buffer();
                buffer.writeAll(gzippedResponseBody);
            }

            MediaType contentType = responseBody.contentType();
            Charset charset;
            if (contentType == null || contentType.charset(StandardCharsets.UTF_8) == null) {
                charset = StandardCharsets.UTF_8;
            } else {
                charset = contentType.charset(StandardCharsets.UTF_8);
            }

            if (charset != null && contentLength != 0L) {
                return intercept(response, authorizedUrlBuilder.build().url().toString(), buffer.clone().readString(charset));
            }
        }

        return chain.proceed(request);
    }

    abstract Response intercept(Response response, String url, String body) throws IOException;
}
