package com.example.mohamed.movieapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;

import com.example.mohamed.movieapp.fragments.MovieDetailFragment;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.utils.SingleFragmentActivity;


/**
 * Created by mohamed on 19/09/2017.
 */

public class MovieDetailActvity extends SingleFragmentActivity {
     private static String MOVIE="MOVIE";
     public static  Intent newIntent(Context context,Movie movie){
         Intent intent=new Intent(context,MovieDetailActvity.class);
         intent.putExtra(MOVIE, (Parcelable) movie);
         return intent;
     }

    @Override
    public Fragment CreateFragment() {
        return MovieDetailFragment.newFragment((Movie) getIntent().getParcelableExtra(MOVIE));
    }
}
