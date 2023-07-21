package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Episodes implements Serializable {

    @SerializedName("air_date")
    public String episode_air_date;

    @SerializedName("episode_number")
    public int episode_number;

    @SerializedName("id")
    public int episode_id;

    @SerializedName("name")
    public String episode_name;

    @SerializedName("overview")
    public String episode_overview;

    @SerializedName("production_code")
    public String episode_production_code;

    @SerializedName("runtime")
    public int episode_runtime;

    @SerializedName("season_number")
    public int season_number;

    @SerializedName("show_id")
    public int episode_show_id;

    @SerializedName("still_path")
    public String episode_still_path;

    @SerializedName("vote_average")
    public int episode_vote_average;

    @SerializedName("vote_count")
    public int season_vote_count;

    @SerializedName("crew")
    public ArrayList<CastCrewArray> crewArrays;

    @SerializedName("guest_stars")
    public ArrayList<GuestStars> guestStars;

    public Episodes(String episode_air_date, int episode_number, int episode_id, String episode_name, String episode_overview, String episode_production_code,
                    int episode_runtime, int season_number, int episode_show_id, String episode_still_path, int episode_vote_average,
                    int season_vote_count, ArrayList<CastCrewArray> crewArrays, ArrayList<GuestStars> guestStars) {
        this.episode_air_date = episode_air_date;
        this.episode_number = episode_number;
        this.episode_id = episode_id;
        this.episode_name = episode_name;
        this.episode_overview = episode_overview;
        this.episode_production_code = episode_production_code;
        this.episode_runtime = episode_runtime;
        this.season_number = season_number;
        this.episode_show_id = episode_show_id;
        this.episode_still_path = episode_still_path;
        this.episode_vote_average = episode_vote_average;
        this.season_vote_count = season_vote_count;
        this.crewArrays = crewArrays;
        this.guestStars = guestStars;
    }

    public String getEpisode_air_date() {
        return episode_air_date;
    }

    public void setEpisode_air_date(String episode_air_date) {
        this.episode_air_date = episode_air_date;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(int episode_id) {
        this.episode_id = episode_id;
    }

    public String getEpisode_name() {
        return episode_name;
    }

    public void setEpisode_name(String episode_name) {
        this.episode_name = episode_name;
    }

    public String getEpisode_overview() {
        return episode_overview;
    }

    public void setEpisode_overview(String episode_overview) {
        this.episode_overview = episode_overview;
    }

    public String getEpisode_production_code() {
        return episode_production_code;
    }

    public void setEpisode_production_code(String episode_production_code) {
        this.episode_production_code = episode_production_code;
    }

    public int getEpisode_runtime() {
        return episode_runtime;
    }

    public void setEpisode_runtime(int episode_runtime) {
        this.episode_runtime = episode_runtime;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public int getEpisode_show_id() {
        return episode_show_id;
    }

    public void setEpisode_show_id(int episode_show_id) {
        this.episode_show_id = episode_show_id;
    }

    public String getEpisode_still_path() {
        return episode_still_path;
    }

    public void setEpisode_still_path(String episode_still_path) {
        this.episode_still_path = episode_still_path;
    }

    public int getEpisode_vote_average() {
        return episode_vote_average;
    }

    public void setEpisode_vote_average(int episode_vote_average) {
        this.episode_vote_average = episode_vote_average;
    }

    public int getSeason_vote_count() {
        return season_vote_count;
    }

    public void setSeason_vote_count(int season_vote_count) {
        this.season_vote_count = season_vote_count;
    }

    public ArrayList<CastCrewArray> getCrewArrays() {
        return crewArrays;
    }

    public void setCrewArrays(ArrayList<CastCrewArray> crewArrays) {
        this.crewArrays = crewArrays;
    }

    public ArrayList<GuestStars> getGuestStars() {
        return guestStars;
    }

    public void setGuestStars(ArrayList<GuestStars> guestStars) {
        this.guestStars = guestStars;
    }

    @NonNull
    @Override
    public String toString() {
        return "Episodes{" +
                "episode_air_date='" + episode_air_date + '\'' +
                ", episode_number=" + episode_number +
                ", episode_id=" + episode_id +
                ", episode_name='" + episode_name + '\'' +
                ", episode_overview='" + episode_overview + '\'' +
                ", episode_production_code='" + episode_production_code + '\'' +
                ", episode_runtime=" + episode_runtime +
                ", season_number=" + season_number +
                ", episode_show_id=" + episode_show_id +
                ", episode_still_path='" + episode_still_path + '\'' +
                ", episode_vote_average=" + episode_vote_average +
                ", season_vote_count=" + season_vote_count +
                ", crewArrays=" + crewArrays +
                ", guestStars=" + guestStars +
                '}';
    }
}
