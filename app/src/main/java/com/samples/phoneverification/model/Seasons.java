package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Seasons implements Serializable {

    @SerializedName("air_date")
    public String airDate;

    @SerializedName("episode_count")
    public int episodeCount;

    @SerializedName("id")
    public int seasonId;

    @SerializedName("name")
    public String seasonName;

    @SerializedName("overview")
    public String seasonOverview;

    @SerializedName("poster_path")
    public String seasonPosterPath;

    @SerializedName("season_number")
    public int seasonNumber;

    @SerializedName("vote_average")
    public double voteAverage;

    public Seasons(String airDate, int episodeCount, int seasonId, String seasonName, String seasonOverview, String seasonPosterPath, int seasonNumber, double voteAverage) {
        this.airDate = airDate;
        this.episodeCount = episodeCount;
        this.seasonId = seasonId;
        this.seasonName = seasonName;
        this.seasonOverview = seasonOverview;
        this.seasonPosterPath = seasonPosterPath;
        this.seasonNumber = seasonNumber;
        this.voteAverage = voteAverage;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonOverview() {
        return seasonOverview;
    }

    public void setSeasonOverview(String seasonOverview) {
        this.seasonOverview = seasonOverview;
    }

    public String getSeasonPosterPath() {
        return seasonPosterPath;
    }

    public void setSeasonPosterPath(String seasonPosterPath) {
        this.seasonPosterPath = seasonPosterPath;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @NonNull
    @Override
    public String toString() {
        return "Seasons{" +
                "airDate='" + airDate + '\'' +
                ", episodeCount=" + episodeCount +
                ", seasonId=" + seasonId +
                ", seasonName='" + seasonName + '\'' +
                ", seasonOverview='" + seasonOverview + '\'' +
                ", seasonPosterPath='" + seasonPosterPath + '\'' +
                ", seasonNumber=" + seasonNumber +
                ", voteAverage=" + voteAverage +
                '}';
    }
}
