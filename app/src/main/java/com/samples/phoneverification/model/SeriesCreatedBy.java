package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeriesCreatedBy implements Serializable {

    @SerializedName("id")
    public int created_by_id;

    @SerializedName("credit_id")
    public String credit_id;

    @SerializedName("name")
    public String credit_name;

    @SerializedName("gender")
    public int gender;

    @SerializedName("profile_path")
    public String profile_path;

    public SeriesCreatedBy(int created_by_id, String credit_id, String credit_name, int gender, String profile_path) {
        this.created_by_id = created_by_id;
        this.credit_id = credit_id;
        this.credit_name = credit_name;
        this.gender = gender;
        this.profile_path = profile_path;
    }

    public int getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(int created_by_id) {
        this.created_by_id = created_by_id;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getCredit_name() {
        return credit_name;
    }

    public void setCredit_name(String credit_name) {
        this.credit_name = credit_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    @NonNull
    @Override
    public String toString() {
        return "SeriesCreatedBy{" +
                "created_by_id=" + created_by_id +
                ", credit_id='" + credit_id + '\'' +
                ", credit_name='" + credit_name + '\'' +
                ", gender=" + gender +
                ", profile_path='" + profile_path + '\'' +
                '}';
    }
}
