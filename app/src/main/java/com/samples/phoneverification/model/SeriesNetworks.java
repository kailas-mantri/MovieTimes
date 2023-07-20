package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeriesNetworks implements Serializable {

    @SerializedName("id")
    public int network_id;

    @SerializedName("logo_path")
    public String network_logo_path;

    @SerializedName("name")
    public String network_name;

    @SerializedName("origin_country")
    public String origin_country;

    public SeriesNetworks(int network_id, String network_logo_path, String network_name, String origin_country) {
        this.network_id = network_id;
        this.network_logo_path = network_logo_path;
        this.network_name = network_name;
        this.origin_country = origin_country;
    }

    public int getNetwork_id() {
        return network_id;
    }

    public void setNetwork_id(int network_id) {
        this.network_id = network_id;
    }

    public String getNetwork_logo_path() {
        return network_logo_path;
    }

    public void setNetwork_logo_path(String network_logo_path) {
        this.network_logo_path = network_logo_path;
    }

    public String getNetwork_name() {
        return network_name;
    }

    public void setNetwork_name(String network_name) {
        this.network_name = network_name;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    @NonNull
    @Override
    public String toString() {
        return "SeriesNetworks{" +
                "network_id=" + network_id +
                ", network_logo_path='" + network_logo_path + '\'' +
                ", network_name='" + network_name + '\'' +
                ", origin_country='" + origin_country + '\'' +
                '}';
    }
}
