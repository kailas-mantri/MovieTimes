package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MediaGroup implements Serializable {

    @SerializedName("id")
    public int movieId;

    @SerializedName("results")
    public ArrayList<MediaList> mediaList;

    public MediaGroup() {
    }

    public MediaGroup(int movieId, ArrayList<MediaList> mediaList) {
        this.movieId = movieId;
        this.mediaList = mediaList;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public ArrayList<MediaList> getMediaList() {
        return mediaList;
    }

    public void setMediaList(ArrayList<MediaList> mediaList) {
        this.mediaList = mediaList;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieMediaGroup{" +
                "movieId=" + movieId +
                ", mediaList=" + mediaList +
                '}';
    }
}
