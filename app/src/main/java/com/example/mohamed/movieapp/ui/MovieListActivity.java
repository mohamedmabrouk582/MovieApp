package com.example.mohamed.movieapp.ui;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.widget.Toast;

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
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
//        if (width>600){
//            return R.layout.activity_twopane;
//        }else {
//           return R.layout.container_view;
//        }
        //Toast.makeText(this, width+"", Toast.LENGTH_SHORT).show();
        return R.layout.activity_masterdetail;
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (findViewById(R.id.detail_fragment_container)!=null) {

            try{
                onMovieSelected( DbOperations.getmOperations(this).getMovies().get(0));

            }catch (Exception e){

            }
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
