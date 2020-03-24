package com.wkq.net;

import android.content.Context;


import com.wkq.net.converter.GsonConverterFactory;
import com.wkq.net.interceptor.OffLineInterceptor;
import com.wkq.net.interceptor.OnLineInterceptor;
import com.wkq.net.model.IBaseInfo;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

//import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuild<Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends IBaseInfo> {

    public interface Api<Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends IBaseInfo> {
        Observable request(Service service);
    }

    private Class<Service> clazz;
    private String endpoint;
    private Api<Service, Observable, BaseData> api;
    private long connectTimeout = 10_000;
    private long readTimeout = 10_000;
    private boolean logging = false;
    private List<Interceptor> interceptors;
    private Context context;

    private ApiBuild(Class<Service> clazz) {
        this.clazz = clazz;
        interceptors = new ArrayList<>();
    }

    public static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>,
            BaseData extends IBaseInfo> ApiBuild<Service, Observable, BaseData> service(Class<Service> clazz, Api<Service, Observable, BaseData> api) {
        ApiBuild<Service, Observable, BaseData> build = new ApiBuild<Service, Observable, BaseData>(clazz);
        build.api = api;
        return build;
    }

    public ApiBuild<Service, Observable, BaseData> setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public ApiBuild<Service, Observable, BaseData> setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public ApiBuild<Service, Observable, BaseData> setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public ApiBuild<Service, Observable, BaseData> setLogging(boolean logging) {
        this.logging = logging;
        return this;
    }

    public ApiBuild<Service, Observable, BaseData> addInterceptor(Interceptor... interceptors) {
        if (interceptors != null) this.interceptors.addAll(Arrays.asList(interceptors));
        return this;
    }

    public ApiBuild<Service, Observable, BaseData> setCache(Context context) {
        this.context = context;
        return this;
    }

    public Observable build() {
        return build(false);
    }

    public Observable build(boolean decode) {
        return api.request(service(decode));
    }

    public Observable build(boolean decode, long keepAliveDuration) {
        return api.request(service(decode, keepAliveDuration));
    }

    public Service service() {
        return service(false);
    }

    public Service service(boolean decode) {
        //okhttp默认的
        return service(decode, 5 * 60);
    }

    public Service service(boolean decode, long keepAliveDuration) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();
//        okHttpClientBuilder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("99.86.33.92", 42122)));
//        okHttpClientBuilder.
        if (connectTimeout > 0) {
            okHttpClientBuilder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        }
        if (readTimeout > 0) {
            okHttpClientBuilder.readTimeout(readTimeout, TimeUnit.SECONDS);
        }
        if (context != null) {
            okHttpClientBuilder.addNetworkInterceptor(new OnLineInterceptor());
            okHttpClientBuilder.addInterceptor(new OffLineInterceptor(context));

            okHttpClientBuilder.cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024));
        }

        if (interceptors != null && interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors)
                okHttpClientBuilder.addInterceptor(interceptor);
        }

        if (logging) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        okHttpClientBuilder.connectionPool(new ConnectionPool(5, keepAliveDuration, TimeUnit.SECONDS));


        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(decode))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(clazz);

    }
}