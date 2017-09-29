package com.example.mohamed.movieapp.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.example.mohamed.movieapp.MovieDb.DbOperations;
import com.example.mohamed.movieapp.R;
import com.example.mohamed.movieapp.fragments.MovieDetailFragment;
import com.example.mohamed.movieapp.fragments.MoviesLIstFragment;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.utils.SingleFragmentActivity;

import java.util.List;


public class MovieListActivity extends SingleFragmentActivity  implements MoviesLIstFragment.Callbacks{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (findViewById(R.id.detail_fragment_container)!=null) {
            onMovieSelected( DbOperations.getmOperations(this).getMovies().get(0));
        }
    }

    @Override
    public Fragment CreateFragment() {
        return MoviesLIstFragment.newFragment();
    }

    @Override
    public void onMovieSelected( Movie movie) {
        if (findViewById(R.id.detail_fragment_container)==null){
            Intent intent=MovieDetailActvity.newIntent(this,movie);
            startActivity(intent);
        }else {
            Fragment fragment= MovieDetailFragment.newFragment(movie);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container,fragment).commit();
        }
    }
}
