package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpokenLanguages implements Serializable {

    @SerializedName("english_name")
    public String language_name;

    @SerializedName("iso_3166_1")
    public String country_language_code;

    @SerializedName("name")
    public String country_name;

    public SpokenLanguages(String language_name, String country_language_code, String country_name) {
        this.language_name = language_name;
        this.country_language_code = country_language_code;
        this.country_name = country_name;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public String getCountry_language_code() {
        return country_language_code;
    }

    public void setCountry_language_code(String country_language_code) {
        this.country_language_code = country_language_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "SokenLangugaes{" +
                "language_name='" + language_name + '\'' +
                ", country_language_code='" + country_language_code + '\'' +
                ", country_name='" + country_name + '\'' +
                '}';
    }
}
