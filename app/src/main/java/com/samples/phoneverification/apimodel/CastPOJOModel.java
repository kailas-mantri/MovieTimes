package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CastPOJOModel implements Serializable {

    @SerializedName("id")
    public Integer movieId;

    @SerializedName("cast")
    public ArrayList<CastCrewArray> castList;

    @SerializedName("crew")
    public ArrayList<CastCrewArray> crewArrays;

    public CastPOJOModel() {
    }

    public CastPOJOModel(Integer movieId, ArrayList<CastCrewArray> castList, ArrayList<CastCrewArray> crewArrays) {
        this.movieId = movieId;
        this.castList = castList;
        this.crewArrays = crewArrays;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public ArrayList<CastCrewArray> getCastList() {
        return castList;
    }

    public void setCastList(ArrayList<CastCrewArray> castList) {
        this.castList = castList;
    }

    public ArrayList<CastCrewArray> getCrewArrays() {
        return crewArrays;
    }

    public void setCrewArrays(ArrayList<CastCrewArray> crewArrays) {
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
