package com.example.sushant.themoviedb.PlainJavaObject;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushant on 15/5/16.
 */
public class MovieList {

    public ArrayList<MovieInfo> getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(ArrayList<MovieInfo> movieInfo) {
        this.movieInfo = movieInfo;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    @Expose
    public ArrayList<MovieInfo> movieInfo;

    @Expose
    public int total_pages;
}
