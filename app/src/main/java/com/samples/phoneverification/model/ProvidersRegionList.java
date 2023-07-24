package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Named;

public class ProvidersRegionList implements Serializable {

    @SerializedName("link")
    public String region_link;

    @SerializedName("buy")
    private ArrayList<Providers> buyList;

    @SerializedName("rent")
    private ArrayList<Providers> rentList;

    @SerializedName("flatrate")
    private ArrayList<Providers> flatRateList;

    public ProvidersRegionList(String region_link, ArrayList<Providers> buyList, ArrayList<Providers> rentList, ArrayList<Providers> flatRateList) {
        this.region_link = region_link;
        this.buyList = buyList;
        this.rentList = rentList;
        this.flatRateList = flatRateList;
    }

    public String getRegion_link() {
        return region_link;
    }

    public void setRegion_link(String region_link) {
        this.region_link = region_link;
    }

    public ArrayList<Providers> getBuyList() {
        return buyList;
    }

    public void setBuyList(ArrayList<Providers> buyList) {
        this.buyList = buyList;
    }

    public ArrayList<Providers> getRentList() {
        return rentList;
    }

    public void setRentList(ArrayList<Providers> rentList) {
        this.rentList = rentList;
    }

    public ArrayList<Providers> getFlatRateList() {
        return flatRateList;
    }

    public void setFlatRateList(ArrayList<Providers> flatRateList) {
        this.flatRateList = flatRateList;
    }

    @NonNull
    @Override
    public String toString() {
        return "ProvidersRegionList{" +
                "region_link='" + region_link + '\'' +
                ", buyList=" + buyList +
                ", rentList=" + rentList +
                ", flatRateList=" + flatRateList +
                '}';
    }

}
