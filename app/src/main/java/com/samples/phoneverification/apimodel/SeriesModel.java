package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SeriesModel implements Serializable {

    @SerializedName("page")
    public Integer page;

    @SerializedName("results")
    public ArrayList<SeriesResults> seriesResults;

    @SerializedName("total_pages")
    public Integer totalPages;

    @SerializedName("total_results")
    public Integer totalResults;

    public SeriesModel(Integer page, ArrayList<SeriesResults> seriesResults, Integer totalPages, Integer totalResults) {
        this.page = page;
        this.seriesResults = seriesResults;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<SeriesResults> getSeriesResults() {
        return seriesResults;
    }

    public void setSeriesResults(ArrayList<SeriesResults> seriesResults) {
        this.seriesResults = seriesResults;
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
        return "SeriesModel {" +
                "page=" + page +
                ", seriesResults=" + seriesResults +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
