package com.example.mohamed.movieapp.view;


import com.example.mohamed.movieapp.model.Movie;

import java.util.List;

/**
 * Created by mohamed on 17/09/2017.
 */

public interface MainView {
    void showProgress();
    void hideProgress();
    void showMovieClickedMessage(Movie s);
    void showMovies(List<Movie> movies);
    void showConnectionError();
}
