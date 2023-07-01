package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchApiModel implements Serializable {

    @SerializedName("page")
    public Integer page;

    @SerializedName("results")
    public ArrayList<SearchApiResults> searchResults;

    @SerializedName("total_pages")
    public Integer totalPages;

    @SerializedName("total_results")
    public Integer totalResults;

    public SearchApiModel() {
    }

    public SearchApiModel(Integer page, ArrayList<SearchApiResults> searchResults, Integer totalPages, Integer totalResults) {
        this.page = page;
        this.searchResults = searchResults;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<SearchApiResults> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(ArrayList<SearchApiResults> searchResults) {
        this.searchResults = searchResults;
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
        return "SearchApiModel{" +
                "page=" + page +
                ", searchResults=" + searchResults +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
