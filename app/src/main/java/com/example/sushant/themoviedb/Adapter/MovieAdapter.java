package com.example.sushant.themoviedb.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sushant.themoviedb.ApiCalls.TheMovieDbApi;
import com.example.sushant.themoviedb.PlainJavaObject.MovieInfo;
import com.example.sushant.themoviedb.R;
import com.example.sushant.themoviedb.TheMovieDbApp;

import java.util.ArrayList;

/**
 * Created by sushant on 14/5/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static Context sContext;
    private ArrayList<MovieInfo> movieInfos = new ArrayList<>();
    private static final String API_KEY = "a759d56bc9f8e7a541ecb01619125f73";

    public MovieAdapter(Context context, ArrayList<MovieInfo> movieInfos){
        sContext = context;
        this.movieInfos = movieInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.movieName.setText(movieInfos.get(getItemPosition(position)).getTitle());
        holder.movieOverview.setText(movieInfos.get(getItemPosition(position)).getOverview());
        String famous = Float.toString(movieInfos.get(getItemPosition(position)).getPopularity());
        holder.moviePopularity.setText(famous);

        String imageurl = "http://image.tmdb.org/t/p/w500" + movieInfos.get(getItemPosition(position)).getPoster_path();


        if (movieInfos.get(getItemPosition(position)).getPoster_path() != null) {
            Glide
                    .with(TheMovieDbApp.getAppContext())
                    .load(imageurl)
                    .placeholder(R.drawable.small_movie_poster)
                    .crossFade()
                    .into(holder.moviewPoster);
        }

        else holder.moviewPoster.setVisibility(View.GONE);
    }

    public int getItemPosition(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return movieInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView movieName, movieOverview, moviePopularity;
        private ImageView moviewPoster;
        public ViewHolder(View v) {
            super(v);
            movieName = (TextView)v.findViewById(R.id.Title);
            moviewPoster = (ImageView)v.findViewById(R.id.PosterImage);
            movieOverview = (TextView)v.findViewById(R.id.Overview);
            moviePopularity = (TextView)v.findViewById(R.id.popularity);
        }
    }

}
