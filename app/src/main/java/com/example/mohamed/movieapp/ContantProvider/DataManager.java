package com.example.mohamed.movieapp.ContantProvider;

/**
 * Created by mohamed on 15/09/2017.
 */

public class DataManager {
    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void clear() {
        mSharedPrefsHelper.Clear();
    }
    public void SavePostion(int postion){
        mSharedPrefsHelper.PutPostion(postion);
    }

    public int getPstion(){
        return mSharedPrefsHelper.getPstion();
    }
    public void savetype(String type) {
        mSharedPrefsHelper.PutType(type);
    }

    public String getType() {
        return mSharedPrefsHelper.getType();
    }



}
