package com.example.mohamed.movieapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.mohamed.movieapp.Application.MyApp;
import com.example.mohamed.movieapp.ContantProvider.DataManager;
import com.example.mohamed.movieapp.MovieDb.DbOperations;
import com.example.mohamed.movieapp.R;
import com.example.mohamed.movieapp.adapter.MoviesAdapter;
import com.example.mohamed.movieapp.api.Requests;
import com.example.mohamed.movieapp.api.requestsInterface;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.presenter.MainViewPresenter;
import com.example.mohamed.movieapp.ui.MovieDetailActvity;
import com.example.mohamed.movieapp.view.MainView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 20/09/2017.
 */

public class MoviesLIstFragment extends Fragment implements MainView,MoviesAdapter.MovieItemListener{
    private Callbacks mCallbacks;
    public final static String MOVIES = "movies";
    private Bundle saveBundle;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainViewPresenter mainViewPresenter;
    private MoviesAdapter moviesAdapter;
    String type="top_rated";
    private requestsInterface mRequestsInterface;
    private List<Movie> mList=new ArrayList<>();
    private View view;
    private DataManager dataManager;
     public static MoviesLIstFragment newFragment(){

         return new MoviesLIstFragment();
     }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
             saveBundle=savedInstanceState;
            view = inflater.inflate(R.layout.activity_main, container, false);
            mainViewPresenter = new MainViewPresenter(this, new Requests(getActivity()));
            saveBundle = savedInstanceState;
            dataManager=((MyApp) getActivity().getApplication()).getDataManager();
            initRecyclerView();
            initSwipeRefreshLayout();
            type=dataManager.getType();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        type=dataManager.getType();
        DbOperations.getmOperations(getActivity()).deleteMovie();
        mainViewPresenter.loadMovieData(type);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider =400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popular:
                type="popular";
                dataManager.clear();
                dataManager.savetype(type);
                mainViewPresenter.loadMovieData(type);
                return true;
            case R.id.favourite:
                type="favourite";
                dataManager.clear();
                dataManager.savetype(type);
                mSwipeRefreshLayout.setRefreshing(false);
                mainViewPresenter.loadMovieData(type);
                return true;
            case R.id.top_rated:
                type="top_rated";
                dataManager.clear();
                dataManager.savetype(type);
                mainViewPresenter.loadMovieData(type);
                return true;
        }
        return super.onOptionsItemSelected(item);    }

    @Override
    public void showProgress() {
        if (!mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }


    @Override
    public void hideProgress() {
        if (mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showMovieClickedMessage(Movie movie) {
        mCallbacks.onMovieSelected(movie);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        if (saveBundle!=null){
            mList=saveBundle.getParcelableArrayList(MOVIES);
        }
        mList=movies;
        moviesAdapter.replaceData(mList);
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(getActivity(), "Error at Connection", Toast.LENGTH_SHORT).show();
        mRequestsInterface.onSucess(DbOperations.getmOperations(getActivity()).getMovies());

    }

    @Override
    public void onMovieItemClick(Movie item) {
        mainViewPresenter.clickMovieItem(item);
    }
    void initRecyclerView(){
        moviesAdapter=new MoviesAdapter(R.layout.movie_item,getActivity(),this);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.movies_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),numberOfColumns()));
        mRecyclerView.setAdapter(moviesAdapter);
    }

    void initSwipeRefreshLayout(){
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainViewPresenter.loadMovieData(type);
            }
        });
    }

    public interface Callbacks {
        void onMovieSelected(Movie movie);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MOVIES, (ArrayList<? extends Parcelable>) mList);
        super.onSaveInstanceState(outState);

    }


}
