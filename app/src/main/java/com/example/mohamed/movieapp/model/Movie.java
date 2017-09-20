package com.example.mohamed.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohamed on 17/09/2017.
 */
@SuppressWarnings("serial")
public class Movie implements Serializable{
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("id")
    private Integer id;
    @SerializedName("vote_count")
    private Integer voteCount;

    public Movie(String title, String posterPath, Integer id, Integer voteCount) {
        this.title = title;
        this.posterPath = posterPath;
        this.id = id;
        this.voteCount = voteCount;
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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
