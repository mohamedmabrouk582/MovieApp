package com.example.mohamed.movieapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohamed on 19/09/2017.
 */

public class Review {
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("id")
    private String id;
    public Review(String author, String content, String id) {
        this.author = author;
        this.content = content;
        this.id = id;
   }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
