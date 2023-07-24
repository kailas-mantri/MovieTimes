package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Providers implements Serializable {

    @SerializedName("logo_path")
    private String providersLogoPath;

    @SerializedName("provider_id")
    private int providerId;

    @SerializedName("provider_name")
    private String providerName;

    @SerializedName("display_priority")
    private int displayPriority;

    public Providers(String providersLogoPath, int providerId, String providerName, int displayPriority) {
        this.providersLogoPath = providersLogoPath;
        this.providerId = providerId;
        this.providerName = providerName;
        this.displayPriority = displayPriority;
    }

    public String getProvidersLogoPath() {
        return providersLogoPath;
    }

    public void setProvidersLogoPath(String providersLogoPath) {
        this.providersLogoPath = providersLogoPath;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getDisplayPriority() {
        return displayPriority;
    }

    public void setDisplayPriority(int displayPriority) {
        this.displayPriority = displayPriority;
    }

    @NonNull
    @Override
    public String toString() {
        return "Providers{" +
                "providersLogoPath='" + providersLogoPath + '\'' +
                ", providerId=" + providerId +
                ", providerName='" + providerName + '\'' +
                ", displayPriority=" + displayPriority +
                '}';
    }
}
