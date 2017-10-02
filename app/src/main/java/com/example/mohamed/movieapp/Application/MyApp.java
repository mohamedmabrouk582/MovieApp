package com.example.mohamed.movieapp.Application;

import android.app.Application;

import com.example.mohamed.movieapp.ContantProvider.DataManager;
import com.example.mohamed.movieapp.ContantProvider.SharedPrefsHelper;


/**
 * Created by mohamed on 15/09/2017.
 */

public class MyApp extends Application {
    DataManager dataManager;
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsHelper sharedPrefsHelper=new SharedPrefsHelper(getApplicationContext());
        dataManager=new DataManager(sharedPrefsHelper);
    }

    public DataManager getDataManager(){
        return dataManager;
    }
}
