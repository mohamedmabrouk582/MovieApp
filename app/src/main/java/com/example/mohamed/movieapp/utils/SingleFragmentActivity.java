package com.example.mohamed.movieapp.utils;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mohamed.movieapp.R;


public abstract class SingleFragmentActivity extends AppCompatActivity  {
    private static String MOVIE_KEY="Movie_Key";
    Fragment fragment;
    public abstract Fragment CreateFragment();




    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.container_view;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if(savedInstanceState==null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            //fragment=fragmentManager.findFragmentById(R.id.Fragment_Container);
            fragment=CreateFragment();
            fragmentManager.beginTransaction().add(R.id.Fragment_Container,fragment).commit();

        }


//        if (fragment==null){
//            fragment=CreateFragment();
//        }

    }
}
