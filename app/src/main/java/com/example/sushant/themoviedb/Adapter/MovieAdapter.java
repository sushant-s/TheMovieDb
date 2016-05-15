package com.example.sushant.themoviedb.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sushant.themoviedb.PlainJavaObject.MovieInfo;
import com.example.sushant.themoviedb.R;

import java.util.ArrayList;

/**
 * Created by sushant on 14/5/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static Context sContext;
    private ArrayList<MovieInfo> movieList = new ArrayList<>();

    public MovieAdapter(Context context, ArrayList<MovieInfo> movieList){
        sContext = context;
        this.movieList = movieList;
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
        holder.cardMovieName.setText(movieList.get(getItemPosition(position)).title);
    }

    public int getItemPosition(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView cardMovieName;
        public ViewHolder(View v) {
            super(v);
            cardView = (CardView)v.findViewById(R.id.movieCardView);
            cardMovieName = (TextView)v.findViewById(R.id.movie_name);

        }
    }

}
