package com.example.mohamed.movieapp.ContantProvider;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mohamed on 14/09/2017.
 */

public class SharedPrefsHelper {
    private SharedPreferences mSharedPreferences;
    public static final String MY_PREFS = "MY_PREFS";
    public static final String TYPE = "EMAIL";

    public SharedPrefsHelper(Context context){
     mSharedPreferences=context.getSharedPreferences(MY_PREFS,Context.MODE_PRIVATE);
    }

    public void Clear(){
        mSharedPreferences.edit().clear().apply();
    }

    public void PutType(String email){
        mSharedPreferences.edit().putString(TYPE,email).apply();
    }

    public String getType(){
        return mSharedPreferences.getString(TYPE,"top_rated");
    }



}
