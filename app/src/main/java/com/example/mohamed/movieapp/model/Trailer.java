package com.example.mohamed.movieapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohamed on 20/09/2017.
 */

public class Trailer {
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;

    public Trailer(String key, String name, String id) {
        this.key = key;
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
