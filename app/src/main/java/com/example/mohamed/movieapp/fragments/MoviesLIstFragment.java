package com.example.mohamed.movieapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.mohamed.movieapp.R;
import com.example.mohamed.movieapp.adapter.MoviesAdapter;
import com.example.mohamed.movieapp.api.Requests;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.presenter.MainViewPresenter;
import com.example.mohamed.movieapp.ui.MovieDetailActvity;
import com.example.mohamed.movieapp.view.MainView;

import java.util.List;

/**
 * Created by mohamed on 20/09/2017.
 */

public class MoviesLIstFragment extends Fragment implements MainView,MoviesAdapter.MovieItemListener{

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainViewPresenter mainViewPresenter;
    private MoviesAdapter moviesAdapter;
    String type="popular";
    private View view;

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
      view=inflater.inflate(R.layout.activity_main,container,false);
        mainViewPresenter=new MainViewPresenter(this,new Requests());
        initRecyclerView();
        initSwipeRefreshLayout();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    mainViewPresenter.loadMovieData(type);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popular:
                type="popular";
                mainViewPresenter.loadMovieData(type);
                return true;
            case R.id.favourite:
//                type="favourite";
//                mainViewPresenter.loadMovieData(type);
                Toast.makeText(getActivity(), "No Develop Yet", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.top_rated:
                type="top_rated";
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
        startActivity(MovieDetailActvity.newIntent(getActivity(),movie));
    }

    @Override
    public void showMovies(List<Movie> movies) {
        moviesAdapter.replaceData(movies);
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(getActivity(), "Error at Connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieItemClick(Movie item) {
        mainViewPresenter.clickMovieItem(item);
    }
    void initRecyclerView(){
        moviesAdapter=new MoviesAdapter(R.layout.movie_item,getActivity(),this);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.movies_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
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
}
