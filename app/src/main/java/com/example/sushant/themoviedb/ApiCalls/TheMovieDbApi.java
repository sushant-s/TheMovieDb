package com.example.sushant.themoviedb.ApiCalls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sushant on 14/5/16.
 */
public class TheMovieDbApi {

    public static Retro movieDbCall;
    public static OkHttpClient okHttpClient;

    public static void setupTheMovieDbApi() {
        Gson gson = new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().
                create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.addInterceptor(logging);
        httpClientBuilder.addNetworkInterceptor(new Cache());

        okHttpClient = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.reddit.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        movieDbCall = retrofit.create(Retro.class);

    }
}
