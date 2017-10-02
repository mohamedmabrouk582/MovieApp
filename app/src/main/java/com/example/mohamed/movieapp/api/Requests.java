package com.example.mohamed.movieapp.api;




import android.content.Context;
import android.widget.Toast;

import com.example.mohamed.movieapp.MovieDb.DbOperations;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.model.MoviesResponse;
import com.example.mohamed.movieapp.model.Review;
import com.example.mohamed.movieapp.model.ReviewResponse;
import com.example.mohamed.movieapp.model.Trailer;
import com.example.mohamed.movieapp.model.TrailerResponse;
import com.example.mohamed.movieapp.utils.CheckConnection;
import com.example.mohamed.movieapp.view.MainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohamed on 17/09/2017.
 */

public class Requests {
    static ApiInterface apiInterface;
    public static final String AI_KEY="c258ef3167d2f4ec83da643c7f76b785";
    private Context mContext;
    public Requests(Context context){
        mContext=context;
    }



    public void MovieReview(final int id, String url, final requestsInterface requestsInterface){
        if (CheckConnection.Connection(mContext)){
        apiInterface=ApiClient.getClient(url).create(ApiInterface.class);
        Call<ReviewResponse> call=apiInterface.getReviewCall(id,AI_KEY);
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                requestsInterface.onSucess(response.body().getResults());
                for (Review review:response.body().getResults()) {

                    try {
                        DbOperations.getmOperations(mContext).InsertMovieReview(review, String.valueOf(id));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });
        }else {
            requestsInterface.onSucess(DbOperations.getmOperations(mContext).getReview(String.valueOf(id)));
        }
    }
    public void MovieTrailer(final int id, String url, final requestsInterface requestsInterface) {
        if (CheckConnection.Connection(mContext)) {
            apiInterface = ApiClient.getClient(url).create(ApiInterface.class);
            Call<TrailerResponse> call = apiInterface.getTrailerResponse(id, AI_KEY);
            call.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                    requestsInterface.onSucess(response.body().getResults());
                    for (Trailer review:response.body().getResults()) {
                    DbOperations.getmOperations(mContext).InsertMovieTrailer(review, String.valueOf(id));

                    }
                }

                @Override
                public void onFailure(Call<TrailerResponse> call, Throwable t) {

                }
            });
        }else {
            requestsInterface.onSucess(DbOperations.getmOperations(mContext).getTrailer(String.valueOf(id)));
        }
    }

    public  void getMovie(String type, String url, final requestsInterface requestsInterface) {
        if (CheckConnection.Connection(mContext)) {
            apiInterface = ApiClient.getClient(url).create(ApiInterface.class);
            Call<MoviesResponse> responseCall = null;
            switch (type) {
                case "popular":
                    responseCall = apiInterface.getPopularMovies(AI_KEY);
                    break;
                case "top_rated":
                    responseCall = apiInterface.getTopRatedMovies(AI_KEY);
                    break;
                case "favourite":
                    requestsInterface.onSucess(DbOperations.getmOperations(mContext).getFavMovies());
                    return;
                default:
                    responseCall = apiInterface.getTopRatedMovies(AI_KEY);

            }
            responseCall.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    requestsInterface.onSucess(response.body().getResults());

                    for (Movie movie:response.body().getResults()) {
                        try {
                        DbOperations.getmOperations(mContext).InsertMovie(movie);
                        }catch (Exception e){}
                    }
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    requestsInterface.onFaile(t.getMessage());
                }
            });
        }else if(type.equals("favourite")){
            Toast.makeText(mContext, "favourite ", Toast.LENGTH_SHORT).show();
            requestsInterface.onSucess(DbOperations.getmOperations(mContext).getFavMovies());
        }
        else {
            requestsInterface.onSucess(DbOperations.getmOperations(mContext).getMovies());
        }
    }

}
