package com.example.mohamed.movieapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mohamed.movieapp.R;
import com.example.mohamed.movieapp.model.Movie;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 17/09/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private int rowLayout;
    private String urlbase="http://image.tmdb.org/t/p/w185";
    private  Context context;
    private final MovieItemListener movieItemListener;
    private List<Movie> movies=new ArrayList<>();


    public  class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private KenBurnsView mImageView;

        public MovieViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            mImageView= (KenBurnsView) v.findViewById(R.id.movie_poster);
        }

        @Override
        public void onClick(View v) {
        movieItemListener.onMovieItemClick(movies.get(getAdapterPosition()));
        }
    }

    public MoviesAdapter( int rowLayout, Context context,MovieItemListener movieItemListener) {
        this.movieItemListener=movieItemListener;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public void replaceData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Picasso.with(context).load(Uri.parse(urlbase+movies.get(position).getPosterPath())).into(holder.mImageView);
   }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface MovieItemListener {
        void onMovieItemClick(Movie item);
    }
}
