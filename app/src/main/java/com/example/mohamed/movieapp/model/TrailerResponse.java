package com.example.mohamed.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mohamed on 20/09/2017.
 */

public class TrailerResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Trailer> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
