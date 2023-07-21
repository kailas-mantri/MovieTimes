package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SeriesItemSeasonDetails implements Serializable {

    @SerializedName("_id")
    public String series_id;

    @SerializedName("air_date")
    public String air_date;

    @SerializedName("episodes")
    public ArrayList<Episodes> episodes;

    @SerializedName("name")
    public String series_season_name;

    @SerializedName("overview")
    public String series_season_overview;

    @SerializedName("id")
    public int series_season_id;

    @SerializedName("poster_path")
    public String season_poster_path;

    @SerializedName("season_number")
    public int season_number;

    @SerializedName("vte_average")
    public double season_vte_average;

    public SeriesItemSeasonDetails(String series_id, String air_date, ArrayList<Episodes> episodes, String series_season_name, String series_season_overview,
                                   int series_season_id, String season_poster_path, int season_number, double season_vte_average) {
        this.series_id = series_id;
        this.air_date = air_date;
        this.episodes = episodes;
        this.series_season_name = series_season_name;
        this.series_season_overview = series_season_overview;
        this.series_season_id = series_season_id;
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

    public ArrayList<Episodes> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episodes> episodes) {
        this.episodes = episodes;
    }

    public String getSeries_season_name() {
        return series_season_name;
    }

    public void setSeries_season_name(String series_season_name) {
        this.series_season_name = series_season_name;
    }

    public String getSeries_season_overview() {
        return series_season_overview;
    }

    public void setSeries_season_overview(String series_season_overview) {
        this.series_season_overview = series_season_overview;
    }

    public int getSeries_season_id() {
        return series_season_id;
    }

    public void setSeries_season_id(int series_season_id) {
        this.series_season_id = series_season_id;
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
                ", episodes=" + episodes +
                ", series_season_name='" + series_season_name + '\'' +
                ", series_season_overview='" + series_season_overview + '\'' +
                ", series_season_id=" + series_season_id +
                ", season_poster_path='" + season_poster_path + '\'' +
                ", season_number=" + season_number +
                ", season_vte_average=" + season_vte_average +
                '}';
    }
}
