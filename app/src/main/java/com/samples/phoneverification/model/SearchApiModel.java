package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchApiModel implements Serializable {

    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public ArrayList<SearchApiResults> searchResults;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("total_results")
    public int totalResults;

    public SearchApiModel() {
    }

    public SearchApiModel(int page, ArrayList<SearchApiResults> searchResults, int totalPages, int totalResults) {
        this.page = page;
        this.searchResults = searchResults;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<SearchApiResults> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(ArrayList<SearchApiResults> searchResults) {
        this.searchResults = searchResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @NonNull
    @Override
    public String toString() {
        return "SearchApiModel{" +
                "page=" + page +
                ", searchResults=" + searchResults +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
