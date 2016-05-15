package com.example.sushant.themoviedb.PlainJavaObject;

import com.google.gson.annotations.Expose;


/**
 * Created by sushant on 14/5/16.
 */
public class MovieInfo {

    @Expose
    public String backdrop_path;

    @Expose
    public int id;

    @Expose
    public String release_date;

    @Expose
    public String poster_path;

    @Expose
    public int rating;

    @Expose
    public String title;

    @Expose
    public int vote_average;

    @Expose
    public int vote_count;


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

}
