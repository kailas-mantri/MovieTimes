package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieModel implements Serializable {

    @SerializedName("page")
    public Integer page;

    @SerializedName("results")
    public ArrayList<MovieResults> movieResults;

    @SerializedName("total_pages")
    public Integer totalPages;

    @SerializedName("total_results")
    public Integer totalResults;

    public MovieModel() {
    }

    public MovieModel(Integer page, ArrayList<MovieResults> movieResults, Integer totalPages, Integer totalResults) {
        this.page = page;
        this.movieResults = movieResults;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<MovieResults> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(ArrayList<MovieResults> movieResults) {
        this.movieResults = movieResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieModel{" +
                "page=" + page +
                ", movieResults=" + movieResults +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
