package com.example.mohamed.movieapp.api;




import com.example.mohamed.movieapp.model.MoviesResponse;
import com.example.mohamed.movieapp.model.ReviewResponse;
import com.example.mohamed.movieapp.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mohamed on 17/09/2017.
 */

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
     @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);
    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    @GET("movie/{id}/reviews")
    Call<ReviewResponse> getReviewCall(@Path("id") int id, @Query("api_key") String apiKey);
    @GET("movie/{id}/videos")
    Call<TrailerResponse> getTrailerResponse(@Path("id") int id, @Query("api_key") String apiKey);

}
