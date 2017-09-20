package com.example.mohamed.movieapp.presenter;


import com.example.mohamed.movieapp.model.Movie;

/**
 * Created by mohamed on 19/09/2017.
 */

public interface MovieDetailPresenter {
    void LoadMovieDetail();
    void AddtoFav(Movie movie);
}
