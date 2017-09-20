package com.example.mohamed.movieapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mohamed.movieapp.R;


public abstract class SingleFragmentActivity extends AppCompatActivity  {

    public abstract Fragment CreateFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_view);

        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.Fragment_Container);

        if (fragment==null){
            fragment=CreateFragment();

            fragmentManager.beginTransaction().add(R.id.Fragment_Container,fragment).commit();
        }

    }
}
