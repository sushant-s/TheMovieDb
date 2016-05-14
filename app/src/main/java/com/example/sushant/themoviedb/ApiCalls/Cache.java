package com.example.sushant.themoviedb.ApiCalls;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by sushant on 14/5/16.
 */
public class Cache implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        return chain.proceed(chain.request())
                .newBuilder()
                .header("Cache-Control","max-age=3600 ")
                .build();
    }
}
