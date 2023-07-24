package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProdCountries implements Serializable {

    @SerializedName("iso_3166_1")
    public String productionCountry;

    @SerializedName("name")
    public String country_name;

    public ProdCountries(String productionCountry, String country_name) {
        this.productionCountry = productionCountry;
        this.country_name = country_name;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
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
        return "MovieProductionCountries{" +
                "productionCountry='" + productionCountry + '\'' +
                ", country_name='" + country_name + '\'' +
                '}';
    }
}
