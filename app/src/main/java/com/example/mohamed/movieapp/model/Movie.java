package com.example.mohamed.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mohamed on 17/09/2017.
 */

public class Movie  implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("id")
    private Integer id;
    @SerializedName("vote_average")
    private float voteCount;


    public Movie(String title, String posterPath, Integer id, float voteCount, String release_date) {
        this.title = title;
        this.posterPath = posterPath;
        this.id = id;
        this.voteCount = voteCount;
        this.release_date = release_date;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(float voteCount) {
        this.voteCount = voteCount;
    }


}
