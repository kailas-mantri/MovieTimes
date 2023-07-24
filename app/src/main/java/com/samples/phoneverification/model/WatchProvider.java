package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class WatchProvider implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("results")
    public Map<String, ProvidersRegionList> regionList;

    public WatchProvider(int id, Map<String, ProvidersRegionList> regionList) {
        this.id = id;
        this.regionList = regionList;
    }

    public WatchProvider() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, ProvidersRegionList> getRegionList() {
        return regionList;
    }

    public void setRegionList(Map<String, ProvidersRegionList> regionList) {
        this.regionList = regionList;
    }

    @NonNull
    @Override
    public String toString() {
        return "WatchProvider{" +
                "id=" + id +
                ", regionList=" + regionList +
                '}';
    }
}
