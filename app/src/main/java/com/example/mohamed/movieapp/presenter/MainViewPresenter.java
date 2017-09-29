package com.example.mohamed.movieapp.presenter;



import com.example.mohamed.movieapp.api.Requests;
import com.example.mohamed.movieapp.api.requestsInterface;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.view.MainView;

import java.util.List;

/**
 * Created by mohamed on 17/09/2017.
 */

public class MainViewPresenter extends BasePresenter implements MainPresenter {
    private MainView mainView;
    private Requests mRequests;
    private static final String BASE_URL = "http://api.themoviedb.org/3/";


    public MainViewPresenter(MainView mainView, Requests mRequests){
        this.mainView=mainView;
        this.mRequests=mRequests;
    }

    @Override
    public void loadMovieData(String type) {
      mainView.showProgress();
        mRequests.getMovie(type,BASE_URL,new requestsInterface() {
            @Override
            public void onSucess(List movies) {
                mainView.hideProgress();
                mainView.showMovies(movies);
            }

            @Override
            public void onFaile(String f) {
            mainView.showConnectionError();
                mainView.hideProgress();
            }
        });
    }

    @Override
    public void clickMovieItem(Movie item) {
            mainView.showMovieClickedMessage(item);
    }
}
