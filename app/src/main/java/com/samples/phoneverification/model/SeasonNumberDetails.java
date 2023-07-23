package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SeasonNumberDetails implements Serializable {

    @SerializedName("_id")
    public String series_id;

    @SerializedName("air_date")
    public String air_date;

    @SerializedName("episodes")
    public ArrayList<Episodes> episodeList;

    @SerializedName("name")
    public String season_name;

    @SerializedName("overview")
    public String seasons_overview;

    @SerializedName("id")
    public int season_id;

    @SerializedName("poster_path")
    public String season_poster_path;

    @SerializedName("season_number")
    public int season_number;

    @SerializedName("vte_average")
    public double season_vte_average;

    public SeasonNumberDetails(String series_id, String air_date, ArrayList<Episodes> episodeList, String season_name, String seasons_overview,
                               int season_id, String season_poster_path, int season_number, double season_vte_average) {
        this.series_id = series_id;
        this.air_date = air_date;
        this.episodeList = episodeList;
        this.season_name = season_name;
        this.seasons_overview = seasons_overview;
        this.season_id = season_id;
        this.season_poster_path = season_poster_path;
        this.season_number = season_number;
        this.season_vte_average = season_vte_average;
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public ArrayList<Episodes> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(ArrayList<Episodes> episodeList) {
        this.episodeList = episodeList;
    }

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public String getSeasons_overview() {
        return seasons_overview;
    }

    public void setSeasons_overview(String seasons_overview) {
        this.seasons_overview = seasons_overview;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public String getSeason_poster_path() {
        return season_poster_path;
    }

    public void setSeason_poster_path(String season_poster_path) {
        this.season_poster_path = season_poster_path;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public double getSeason_vte_average() {
        return season_vte_average;
    }

    public void setSeason_vte_average(double season_vte_average) {
        this.season_vte_average = season_vte_average;
    }

    @NonNull
    @Override
    public String toString() {
        return "SeriesItemSeasonDetails{" +
                "series_id='" + series_id + '\'' +
                ", air_date='" + air_date + '\'' +
                ", episodes=" + episodeList +
                ", series_season_name='" + season_name + '\'' +
                ", series_season_overview='" + seasons_overview + '\'' +
                ", series_season_id=" + season_id +
                ", season_poster_path='" + season_poster_path + '\'' +
                ", season_number=" + season_number +
                ", season_vte_average=" + season_vte_average +
                '}';
    }
}
