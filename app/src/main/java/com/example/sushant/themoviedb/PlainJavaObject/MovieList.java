package com.example.sushant.themoviedb.PlainJavaObject;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushant on 15/5/16.
 */
public class MovieList {


    public List<MovieInfo> getResults() {
        return results;
    }

    public void setResults(List<MovieInfo> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    @Expose
    public List<MovieInfo> results;

    @Expose
    public int total_pages;
}
