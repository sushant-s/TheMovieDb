package com.example.sushant.themoviedb;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sushant.themoviedb.Adapter.MovieAdapter;
import com.example.sushant.themoviedb.ApiCalls.TheMovieDbApi;
import com.example.sushant.themoviedb.PlainJavaObject.MovieInfo;
import com.example.sushant.themoviedb.PlainJavaObject.MovieList;
import com.example.sushant.themoviedb.PlainJavaObject.SessionId;
import com.example.sushant.themoviedb.PlainJavaObject.Token;
import com.example.sushant.themoviedb.EventBusContext;

import java.util.ArrayList;
import java.util.logging.Logger;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sushant on 14/5/16.
 */


public class MainView extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private Toolbar toolbar;
    public EventBus eventBus;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String url;
    private ArrayList<MovieInfo> movieInfos = new ArrayList<>();
    public Token token = new Token();
    public SessionId sessionId = new SessionId();
    public MovieList movieList = new MovieList();

    private static final String API_KEY = "a759d56bc9f8e7a541ecb01619125f73";
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final String USERNAME = "sushant-s";
    private static final String PASSWORD = "sushant1993";
    public static final int GET_TOKEN = 1;
    public static final int POST_GET_TOKEN = 2;
    public static final int GET_SESSION_ID = 3;
    public static final int POST_GET_SESSION_ID = 4;
    public static final int GET_MOVIE_INFO = 5;
    public static final int POST_GET_MOVIE_INFO = 6;
    public static final int VALIDATE = 7;
    public static final int POST_VALIDATE = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Movie Ratings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        eventBus = new EventBus();
        eventBus.register(this);
        new HomeDataLoadThread(GET_TOKEN).start();
        recyclerView = (RecyclerView) findViewById(R.id.list_home);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onRefresh() {
        new HomeDataLoadThread(GET_MOVIE_INFO).start();
    }


    public class HomeDataLoadThread extends Thread {
        int requestType;
        boolean isThreadRunning = false;

        public HomeDataLoadThread(int requestType) {
            this.requestType = requestType;
        }

        @Override
        public void run() {
            super.run();
            //android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            try {
                if (isThreadRunning) {
                    return;
                }
                isThreadRunning = true;
                if(requestType == GET_TOKEN) {
                    if (token.getRequest_token() == null) {
                        url = BASE_URL + "authentication/token/new";
                        Call<Token> tokenCall = TheMovieDbApi.movieDbCall.getToken(url, API_KEY);
                        tokenCall.enqueue(new Callback<Token>() {
                            @Override
                            public void onResponse(Call<Token> call, Response<Token> response) {
                                token = response.body();
                                eventBus.post(new EventBusContext(POST_VALIDATE));
                            }

                            @Override
                            public void onFailure(Call<Token> call, Throwable t) {

                            }
                        });
                    }

                }
                if(requestType == VALIDATE) {
                    if (token.getRequest_token() != null) {
                        url = BASE_URL + "authentication/token/validate_with_login";
                        Call<Token> tokenCall = TheMovieDbApi.movieDbCall.getValidate(url, API_KEY, token.getRequest_token(), USERNAME, PASSWORD);
                        tokenCall.enqueue(new Callback<Token>() {
                            @Override
                            public void onResponse(Call<Token> call, Response<Token> response) {
                                token = response.body();
                                eventBus.post(new EventBusContext(POST_GET_TOKEN));
                            }

                            @Override
                            public void onFailure(Call<Token> call, Throwable t) {

                            }
                        });
                    }

                }
                if(requestType == GET_SESSION_ID) {
                    if ((sessionId.getSession_id() == null) && (token.getRequest_token() != null)) {
                        url = "";
                        url = BASE_URL + "authentication/session/new";
                        Call<SessionId> sessionIdCall = TheMovieDbApi.movieDbCall.getSessionId(url, API_KEY, token.getRequest_token());
                        sessionIdCall.enqueue(new Callback<SessionId>() {
                            @Override
                            public void onResponse(Call<SessionId> call, Response<SessionId> response) {
                                sessionId=response.body();
                                eventBus.post(new EventBusContext(POST_GET_SESSION_ID));
                            }

                            @Override
                            public void onFailure(Call<SessionId> call, Throwable t) {

                            }
                        });
                    }
                }
                if(requestType == GET_MOVIE_INFO) {
                    if ((sessionId.getSession_id() != null) && (token.getRequest_token() != null)) {
                        url = "";
                        url = BASE_URL + "movie/popular";
                        Call<MovieList> movieListCall = TheMovieDbApi.movieDbCall.getMovieList(url, API_KEY);
                        movieListCall.enqueue(new Callback<MovieList>() {
                            @Override
                            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                                movieList=response.body();
                                    for (int i = 0; i < movieList.results.size(); i++) {
                                        movieInfos.add(movieList.getResults().get(i));
                                    }
                                eventBus.post(new EventBusContext(POST_GET_MOVIE_INFO));
                            }

                            @Override
                            public void onFailure(Call<MovieList> call, Throwable t) {

                            }
                        });
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                isThreadRunning=false;
            }
        }
    }

    public void onEventMainThread(EventBusContext eventBusContext) {
        if (eventBusContext.getActionCode() == POST_VALIDATE) {
            new HomeDataLoadThread(VALIDATE).start();
        }
        if (eventBusContext.getActionCode() == POST_GET_TOKEN) {
            new HomeDataLoadThread(GET_SESSION_ID).start();
        }
        if (eventBusContext.getActionCode() == POST_GET_SESSION_ID) {
            new HomeDataLoadThread(GET_MOVIE_INFO).start();
        }
        if (eventBusContext.getActionCode() == POST_GET_MOVIE_INFO) {
            mAdapter = new MovieAdapter(MainView.this, movieInfos);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;

        }
        return true;
    }
}
