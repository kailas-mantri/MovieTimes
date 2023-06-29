package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GenreResults implements Serializable {

    @SerializedName("id")
    public Integer id;

    @SerializedName("name")
    private String name;

    private ArrayList<SeriesResults> seriesList = new ArrayList<>();
    private ArrayList<MovieResults> movieResults = new ArrayList<>();

    public GenreResults() {
    }

    public GenreResults(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreResults(Integer id, String name, ArrayList<MovieResults> movieResults) {
        this.id = id;
        this.name = name;
        this.movieResults = movieResults;
    }

    public GenreResults(Integer id, String name, ArrayList<SeriesResults> seriesList, ArrayList<MovieResults> movieResults) {
        this.id = id;
        this.name = name;
        this.seriesList = seriesList;
        this.movieResults = movieResults;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SeriesResults> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(ArrayList<SeriesResults> seriesList) {
        this.seriesList = seriesList;
    }

    public ArrayList<MovieResults> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(ArrayList<MovieResults> movieResults) {
        this.movieResults = movieResults;
    }

    @NonNull
    @Override
    public String toString() {
        return "GenreResults{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seriesList=" + seriesList +
                ", movieResults=" + movieResults +
                '}';
    }
}
