package com.example.mohamed.movieapp.api;




import com.example.mohamed.movieapp.model.MoviesResponse;
import com.example.mohamed.movieapp.model.ReviewResponse;
import com.example.mohamed.movieapp.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohamed on 17/09/2017.
 */

public class Requests {
    static ApiInterface apiInterface;
    public static final String AI_KEY="c258ef3167d2f4ec83da643c7f76b785";




    public void MovieDetail(int id,String url,final requestsInterface requestsInterface){
        apiInterface=ApiClient.getClient(url).create(ApiInterface.class);
        Call<ReviewResponse> call=apiInterface.getReviewCall(id,AI_KEY);
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                requestsInterface.onSucess(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });
    }
    public void MovieTrailer(int id,String url,final requestsInterface requestsInterface){
        apiInterface=ApiClient.getClient(url).create(ApiInterface.class);
        Call<TrailerResponse> call=apiInterface.getTrailerResponse(id,AI_KEY);
        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                requestsInterface.onSucess(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {

            }
        });
    }


    public  void startRequest(String type,String url,final requestsInterface requestsInterface){
        apiInterface=ApiClient.getClient(url).create(ApiInterface.class);
        Call<MoviesResponse> responseCall = null;
        switch (type){
            case "popular":
                responseCall=apiInterface.getPopularMovies(AI_KEY);
                break;
            case "top_rated":
                responseCall=apiInterface.getTopRatedMovies(AI_KEY);
                break;
            case "favourite":

                break;
            default:
                responseCall=apiInterface.getTopRatedMovies(AI_KEY);

        }
        responseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
              requestsInterface.onSucess(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
               requestsInterface.onFaile(t.getMessage());
            }
        });
    }


}
