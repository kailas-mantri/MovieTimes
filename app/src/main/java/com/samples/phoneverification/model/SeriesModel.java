package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SeriesModel implements Serializable {

    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public ArrayList<SeriesResults> seriesResults;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("total_results")
    public int totalResults;

    public SeriesModel() {
    }

    public SeriesModel(int page, ArrayList<SeriesResults> seriesResults, int totalPages, int totalResults) {
        this.page = page;
        this.seriesResults = seriesResults;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<SeriesResults> getSeriesResults() {
        return seriesResults;
    }

    public void setSeriesResults(ArrayList<SeriesResults> seriesResults) {
        this.seriesResults = seriesResults;
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
        return "SeriesModel {" +
                "page=" + page +
                ", seriesResults=" + seriesResults +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
