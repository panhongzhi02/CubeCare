package com.taiji.cc.http.methods;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by panho on 2016/9/2.
 */
public class HttpMethod {

    public static final String BASE_URL =  "http://10.6.69.201:8088/CubeCare/";

    private static final int DEFAULT_TIMEOUT = 5;

    private HttpMethod(){
    }

    private static class SingletonHolder{
        private static final HttpMethod INSTANCE  = new HttpMethod();
    }

    public static HttpMethod getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public Retrofit getRetrofit(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return mRetrofit;
    }

}
