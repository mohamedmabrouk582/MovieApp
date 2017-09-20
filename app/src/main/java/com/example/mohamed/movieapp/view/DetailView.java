package com.example.mohamed.movieapp.view;



import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.model.Review;
import com.example.mohamed.movieapp.model.Trailer;

import java.util.List;

/**
 * Created by mohamed on 19/09/2017.
 */

public interface DetailView {
    void ShowMovieDetail(Movie movie);
    void ShowMovieReviews(List<Review> reviews);
    void ShowMovieTrailers(List<Trailer> list);
    void AddToFavourate(Movie movie);

}
