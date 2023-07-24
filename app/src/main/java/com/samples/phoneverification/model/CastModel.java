package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CastModel implements Serializable {

    @SerializedName("id")
    public int movieId;

    @SerializedName("cast")
    public ArrayList<CastCrewList> castList;

    @SerializedName("crew")
    public ArrayList<CastCrewList> crewArrays;

    public CastModel() {
    }

    public CastModel(int movieId, ArrayList<CastCrewList> castList, ArrayList<CastCrewList> crewArrays) {
        this.movieId = movieId;
        this.castList = castList;
        this.crewArrays = crewArrays;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public ArrayList<CastCrewList> getCastList() {
        return castList;
    }

    public void setCastList(ArrayList<CastCrewList> castList) {
        this.castList = castList;
    }

    public ArrayList<CastCrewList> getCrewArrays() {
        return crewArrays;
    }

    public void setCrewArrays(ArrayList<CastCrewList> crewArrays) {
        this.crewArrays = crewArrays;
    }

    @NonNull
    @Override
    public String toString() {
        return "CastPOJOModel{" +
                "movieId=" + movieId +
                ", castList=" + castList +
                ", crewArrays=" + crewArrays +
                '}';
    }
}
