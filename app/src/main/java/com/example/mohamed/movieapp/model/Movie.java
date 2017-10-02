package com.example.mohamed.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohamed on 17/09/2017.
 */

public class Movie  implements Serializable, Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("id")
    private Integer id;
    @SerializedName("vote_average")
    private float voteCount;
    @SerializedName("release_date")
    private String release_date;

    public Movie(String title, String posterPath, Integer id, float voteCount, String release_date) {
        this.title = title;
        this.posterPath = posterPath;
        this.id = id;
        this.voteCount = voteCount;
        this.release_date = release_date.substring(0,4);
    }
    protected Movie(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
        id = in.readInt();
        voteCount = in.readFloat();
        release_date = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeInt(id);
        dest.writeFloat(voteCount);
        dest.writeString(release_date);

    }
}
