package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductionCompanies implements Serializable {

    @SerializedName("id")
    public int company_id;

    @SerializedName("logo_path")
    public String company_logo;

    @SerializedName("name")
    public String company_name;

    @SerializedName("origin_country")
    public String company_country;

    public ProductionCompanies(int company_id, String company_name, String company_logo, String company_country) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.company_country = company_country;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getCompany_country() {
        return company_country;
    }

    public void setCompany_country(String company_country) {
        this.company_country = company_country;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieProductionCompanies{" +
                "pCompany_id=" + company_id +
                ", pCompany_name='" + company_name + '\'' +
                ", pCompany_logo='" + company_logo + '\'' +
                ", pCompany_country='" + company_country + '\'' +
                '}';
    }
}
