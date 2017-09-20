package com.example.mohamed.movieapp.ui;

import android.support.v4.app.Fragment;

import com.example.mohamed.movieapp.fragments.MoviesLIstFragment;


public class MovieListActivity extends SingleFragmentActivity {

    @Override
    public Fragment CreateFragment() {
        return MoviesLIstFragment.newFragment();
    }
}
