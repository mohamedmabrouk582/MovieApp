package com.example.mohamed.movieapp.presenter;


import com.example.mohamed.movieapp.model.Movie;

/**
 * Created by mohamed on 17/09/2017.
 */

public interface MainPresenter {
    void loadMovieData(String type);
    void clickMovieItem(Movie item);
}
