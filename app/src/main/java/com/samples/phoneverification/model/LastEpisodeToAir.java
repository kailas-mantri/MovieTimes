package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LastEpisodeToAir implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("episode_number")
    private int episodeNumber;

    @SerializedName("production_code")
    private String productionCode;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("season_number")
    private int seasonNumber;

    @SerializedName("show_id")
    private int showId;

    @SerializedName("still_path")
    private String stillPath;

    public LastEpisodeToAir(int id, String name, String overview, double voteAverage, int voteCount, String airDate,
                            int episodeNumber, String productionCode, int runtime, int seasonNumber, int showId, String stillPath) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.airDate = airDate;
        this.episodeNumber = episodeNumber;
        this.productionCode = productionCode;
        this.runtime = runtime;
        this.seasonNumber = seasonNumber;
        this.showId = showId;
        this.stillPath = stillPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    @NonNull
    @Override
    public String toString() {
        return "LastEpisodeToAir{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", airDate='" + airDate + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", productionCode='" + productionCode + '\'' +
                ", runtime=" + runtime +
                ", seasonNumber=" + seasonNumber +
                ", showId=" + showId +
                ", stillPath='" + stillPath + '\'' +
                '}';
    }
}
