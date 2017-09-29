package com.example.mohamed.movieapp.presenter;


import com.example.mohamed.movieapp.api.Requests;
import com.example.mohamed.movieapp.api.requestsInterface;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.view.DetailView;

import java.util.List;

/**
 * Created by mohamed on 19/09/2017.
 */

public class MovieDetailViewPresenter extends BasePresenter implements MovieDetailPresenter {

    private DetailView mDetailView;
    private Requests mRequests;
    public static final String AI_KEY="c258ef3167d2f4ec83da643c7f76b785";
    private Movie movie;
    public MovieDetailViewPresenter(DetailView mDetailView,Requests mRequests,Movie movie){
        this.mDetailView=mDetailView;
        this.mRequests=mRequests;
        this.movie=movie;
    }
    @Override
    public void LoadMovieDetail() {
        String trailer_url = "http://api.themoviedb.org/3/movie/" + movie.getId() + "/videos?api_key="+AI_KEY;
        String url_review = "http://api.themoviedb.org/3/movie/" + movie.getId() + "/reviews?api_key="+AI_KEY;
        mDetailView.ShowMovieDetail(movie);
        mRequests.MovieTrailer(movie.getId(), trailer_url, new requestsInterface() {
            @Override
            public void onSucess(List movies) {
                mDetailView.ShowMovieTrailers(movies);
            }

            @Override
            public void onFaile(String f) {

            }
        });

       mRequests.MovieReview(movie.getId(), url_review, new requestsInterface() {
           @Override
           public void onSucess(List movies) {
               mDetailView.ShowMovieReviews(movies);
           }

           @Override
           public void onFaile(String f) {

           }
       });


    }

    @Override
    public void AddtoFav(Movie movie) {
    mDetailView.AddToFavourate(movie);
    }


}
