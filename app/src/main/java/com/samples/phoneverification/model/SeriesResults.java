package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class SeriesResults implements Serializable {

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("first_air_date")
    public String firstAirDate;

    @SerializedName("genre_ids")
    public int[] genreIds;

    @SerializedName("id")
    public int seriesId;

    @SerializedName("name")
    public String standardName;

    @SerializedName("origin_country")
    public String[] originCountry;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("original_name")
    public String originalName;

    @SerializedName("overview")
    public String seriesOverview;

    @SerializedName("popularity")
    public double popularity;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("vote_average")
    public double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    public SeriesResults() {
    }

    public SeriesResults(String backdropPath, String firstAirDate, int[] genreIds, int seriesId, String standardName,
                         String[] originCountry, String originalLanguage, String originalName, String seriesOverview, double popularity,
                         String posterPath, double voteAverage, int voteCount) {
        this.backdropPath = backdropPath;
        this.firstAirDate = firstAirDate;
        this.genreIds = genreIds;
        this.seriesId = seriesId;
        this.standardName = standardName;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.seriesOverview = seriesOverview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String[] getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String[] originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSeriesOverview() {
        return seriesOverview;
    }

    public void setSeriesOverview(String seriesOverview) {
        this.seriesOverview = seriesOverview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @NonNull
    @Override
    public String toString() {
        return "SeriesResults{" + "backdropPath='" + backdropPath + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", genreIds=" + Arrays.toString(genreIds) +
                ", SeriesId=" + seriesId +
                ", standardName='" + standardName + '\'' +
                ", originCountry=" + Arrays.toString(originCountry) +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalName='" + originalName + '\'' +
                ", seriesOverview='" + seriesOverview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}
