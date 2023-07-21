package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GuestStars implements Serializable {

    @SerializedName("character")
    public String guestStar_character;

    @SerializedName("credit_id")
    public String guestStar_credit_id;

    @SerializedName("order")
    public int guestStar_order;
    @SerializedName("adult")
    public boolean guestStar_adult;

    @SerializedName("gender")
    public int guestStar_Gender;

    @SerializedName("id")
    public int guestStar_id;

    @SerializedName("known_for_department")
    public String guestStar_known_for_department;

    @SerializedName("name")
    public String guestStar_name;

    @SerializedName("original_name")
    public String guestStar_original_name;
    @SerializedName("popularity")
    public double guestStar_popularity;

    @SerializedName("profile_path")
    public String guestStar_profile_path;

    public GuestStars(String guestStar_character, String guestStar_credit_id, int guestStar_order, boolean guestStar_adult, int guestStar_Gender, int guestStar_id, String guestStar_known_for_department, String guestStar_name, String guestStar_original_name, double guestStar_popularity, String guestStar_profile_path) {
        this.guestStar_character = guestStar_character;
        this.guestStar_credit_id = guestStar_credit_id;
        this.guestStar_order = guestStar_order;
        this.guestStar_adult = guestStar_adult;
        this.guestStar_Gender = guestStar_Gender;
        this.guestStar_id = guestStar_id;
        this.guestStar_known_for_department = guestStar_known_for_department;
        this.guestStar_name = guestStar_name;
        this.guestStar_original_name = guestStar_original_name;
        this.guestStar_popularity = guestStar_popularity;
        this.guestStar_profile_path = guestStar_profile_path;
    }

    public String getGuestStar_character() {
        return guestStar_character;
    }

    public void setGuestStar_character(String guestStar_character) {
        this.guestStar_character = guestStar_character;
    }

    public String getGuestStar_credit_id() {
        return guestStar_credit_id;
    }

    public void setGuestStar_credit_id(String guestStar_credit_id) {
        this.guestStar_credit_id = guestStar_credit_id;
    }

    public int getGuestStar_order() {
        return guestStar_order;
    }

    public void setGuestStar_order(int guestStar_order) {
        this.guestStar_order = guestStar_order;
    }

    public boolean isGuestStar_adult() {
        return guestStar_adult;
    }

    public void setGuestStar_adult(boolean guestStar_adult) {
        this.guestStar_adult = guestStar_adult;
    }

    public int getGuestStar_Gender() {
        return guestStar_Gender;
    }

    public void setGuestStar_Gender(int guestStar_Gender) {
        this.guestStar_Gender = guestStar_Gender;
    }

    public int getGuestStar_id() {
        return guestStar_id;
    }

    public void setGuestStar_id(int guestStar_id) {
        this.guestStar_id = guestStar_id;
    }

    public String getGuestStar_known_for_department() {
        return guestStar_known_for_department;
    }

    public void setGuestStar_known_for_department(String guestStar_known_for_department) {
        this.guestStar_known_for_department = guestStar_known_for_department;
    }

    public String getGuestStar_name() {
        return guestStar_name;
    }

    public void setGuestStar_name(String guestStar_name) {
        this.guestStar_name = guestStar_name;
    }

    public String getGuestStar_original_name() {
        return guestStar_original_name;
    }

    public void setGuestStar_original_name(String guestStar_original_name) {
        this.guestStar_original_name = guestStar_original_name;
    }

    public double getGuestStar_popularity() {
        return guestStar_popularity;
    }

    public void setGuestStar_popularity(double guestStar_popularity) {
        this.guestStar_popularity = guestStar_popularity;
    }

    public String getGuestStar_profile_path() {
        return guestStar_profile_path;
    }

    public void setGuestStar_profile_path(String guestStar_profile_path) {
        this.guestStar_profile_path = guestStar_profile_path;
    }

    @NonNull
    @Override
    public String toString() {
        return "GuestStars{" +
                "guestStar_character='" + guestStar_character + '\'' +
                ", guestStar_credit_id='" + guestStar_credit_id + '\'' +
                ", guestStar_order=" + guestStar_order +
                ", guestStar_adult=" + guestStar_adult +
                ", guestStar_Gender=" + guestStar_Gender +
                ", guestStar_id=" + guestStar_id +
                ", guestStar_known_for_department='" + guestStar_known_for_department + '\'' +
                ", guestStar_name='" + guestStar_name + '\'' +
                ", guestStar_original_name='" + guestStar_original_name + '\'' +
                ", guestStar_popularity=" + guestStar_popularity +
                ", guestStar_profile_path='" + guestStar_profile_path + '\'' +
                '}';
    }
}
