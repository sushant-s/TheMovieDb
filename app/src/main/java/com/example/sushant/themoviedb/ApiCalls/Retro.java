package com.example.sushant.themoviedb.ApiCalls;

import com.example.sushant.themoviedb.PlainJavaObject.MovieList;
import com.example.sushant.themoviedb.PlainJavaObject.SessionId;
import com.example.sushant.themoviedb.PlainJavaObject.Token;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by sushant on 14/5/16.
 */
public interface Retro {

    @GET
    @Headers("Cache-Control:max-age=3600")
    retrofit2.Call<MovieList> getMovieList(
            @Url String url,
            @Query(Constants.api_key) String api_key,
            @Query(Constants.request_token) String request_token,
            @Query(Constants.session_id) String session_id);

    @GET
    retrofit2.Call<SessionId> getSessionId(
            @Url String url,
            @Query(Constants.api_key) String api_key,
            @Query(Constants.request_token) String request_token);

    @GET
    retrofit2.Call<Token> getToken(
            @Url String url,
            @Query(Constants.api_key) String api_key);



}
