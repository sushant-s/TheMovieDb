package com.example.sushant.themoviedb;

import android.app.Application;
import android.content.Context;

import com.example.sushant.themoviedb.ApiCalls.TheMovieDbApi;

/**
 * Created by sushant on 14/5/16.
 */
public class TheMovieDbApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        TheMovieDbApi theMovieDbApi = new TheMovieDbApi();
        TheMovieDbApi.setupTheMovieDbApi();

        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}
