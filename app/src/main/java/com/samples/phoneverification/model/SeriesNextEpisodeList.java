package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeriesNextEpisodeList implements Serializable {

    @SerializedName("id")
    private int next_episode_id;

    @SerializedName("name")
    private String next_episode_name;

    @SerializedName("overview")
    private String next_episode_overview;

    @SerializedName("vote_average")
    private double vote_average;

    @SerializedName("vote_count")
    private int vote_count;

    @SerializedName("air_date")
    private String next_air_date;

    @SerializedName("episode_number")
    private int next_episode_number;

    @SerializedName("production_code")
    private String next_episode_production_code;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("season_number")
    private int season_number;

    @SerializedName("show_id")
    private int show_id;

    @SerializedName("still_path")
    private String next_episode_still_path;

    public SeriesNextEpisodeList(int next_episode_id, String next_episode_name, String next_episode_overview, double vote_average, int vote_count, String next_air_date, int next_episode_number, String next_episode_production_code, int runtime, int season_number, int show_id, String next_episode_still_path) {
        this.next_episode_id = next_episode_id;
        this.next_episode_name = next_episode_name;
        this.next_episode_overview = next_episode_overview;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.next_air_date = next_air_date;
        this.next_episode_number = next_episode_number;
        this.next_episode_production_code = next_episode_production_code;
        this.runtime = runtime;
        this.season_number = season_number;
        this.show_id = show_id;
        this.next_episode_still_path = next_episode_still_path;
    }

    public int getNext_episode_id() {
        return next_episode_id;
    }

    public void setNext_episode_id(int next_episode_id) {
        this.next_episode_id = next_episode_id;
    }

    public String getNext_episode_name() {
        return next_episode_name;
    }

    public void setNext_episode_name(String next_episode_name) {
        this.next_episode_name = next_episode_name;
    }

    public String getNext_episode_overview() {
        return next_episode_overview;
    }

    public void setNext_episode_overview(String next_episode_overview) {
        this.next_episode_overview = next_episode_overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getNext_air_date() {
        return next_air_date;
    }

    public void setNext_air_date(String next_air_date) {
        this.next_air_date = next_air_date;
    }

    public int getNext_episode_number() {
        return next_episode_number;
    }

    public void setNext_episode_number(int next_episode_number) {
        this.next_episode_number = next_episode_number;
    }

    public String getNext_episode_production_code() {
        return next_episode_production_code;
    }

    public void setNext_episode_production_code(String next_episode_production_code) {
        this.next_episode_production_code = next_episode_production_code;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public String getNext_episode_still_path() {
        return next_episode_still_path;
    }

    public void setNext_episode_still_path(String next_episode_still_path) {
        this.next_episode_still_path = next_episode_still_path;
    }

    @NonNull
    @Override
    public String toString() {
        return "NextEpisodeToAir{" +
                "next_episode_id=" + next_episode_id +
                ", next_episode_name='" + next_episode_name + '\'' +
                ", next_episode_overview='" + next_episode_overview + '\'' +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                ", next_air_date='" + next_air_date + '\'' +
                ", next_episode_number=" + next_episode_number +
                ", next_episode_production_code='" + next_episode_production_code + '\'' +
                ", runtime=" + runtime +
                ", season_number=" + season_number +
                ", show_id=" + show_id +
                ", next_episode_still_path='" + next_episode_still_path + '\'' +
                '}';
    }
}
