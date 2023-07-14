package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BelongToCollections implements Serializable {

    @SerializedName("id")
    public Integer collection_movie_id;

    @SerializedName("name")
    public String collection_movie_name;

    @SerializedName("poster_path")
    public String collection_movie_posterPath;

    @SerializedName("backdrop_path")
    public String collection_movie_backdropPath;

    public BelongToCollections() {
    }

    public BelongToCollections(Integer collection_movie_id, String collection_movie_name, String collection_movie_posterPath, String collection_movie_backdropPath) {
        this.collection_movie_id = collection_movie_id;
        this.collection_movie_name = collection_movie_name;
        this.collection_movie_posterPath = collection_movie_posterPath;
        this.collection_movie_backdropPath = collection_movie_backdropPath;
    }

    public Integer getCollection_movie_id() {
        return collection_movie_id;
    }

    public void setCollection_movie_id(int collection_movie_id) {
        this.collection_movie_id = collection_movie_id;
    }

    public String getCollection_movie_name() {
        return collection_movie_name;
    }

    public void setCollection_movie_name(String collection_movie_name) {
        this.collection_movie_name = collection_movie_name;
    }

    public String getCollection_movie_posterPath() {
        return collection_movie_posterPath;
    }

    public void setCollection_movie_posterPath(String collection_movie_posterPath) {
        this.collection_movie_posterPath = collection_movie_posterPath;
    }

    public String getCollection_movie_backdropPath() {
        return collection_movie_backdropPath;
    }

    public void setCollection_movie_backdropPath(String collection_movie_backdropPath) {
        this.collection_movie_backdropPath = collection_movie_backdropPath;
    }

    @NonNull
    @Override
    public String toString() {
        return "BelongToCollections{" +
                "collection_movie_id=" + collection_movie_id +
                ", collection_movie_name='" + collection_movie_name + '\'' +
                ", collection_movie_posterPath='" + collection_movie_posterPath + '\'' +
                ", collection_movie_backdropPath='" + collection_movie_backdropPath + '\'' +
                '}';
    }
}
