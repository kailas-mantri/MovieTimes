package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MediaTypeArray implements Serializable {

    @SerializedName("iso_639_1")
    public String trailerLangType;

    @SerializedName("iso_3166_1")
    public String trailer_CountryCode;

    @SerializedName("name")
    public String trailer_name;

    @SerializedName("key")
    public String media_key;

    @SerializedName("site")
    public String media_on_website;

    @SerializedName("size")
    public int media_resolution;

    @SerializedName("type")
    public String media_type;

    @SerializedName("official")
    public boolean isOfficial;

    @SerializedName("published_at")
    public String publishDate;

    @SerializedName("id")
    public String mediaTrailerId;

    public MediaTypeArray() {
    }

    public String getTrailerLangType() {
        return trailerLangType;
    }

    public void setTrailerLangType(String trailerLangType) {
        this.trailerLangType = trailerLangType;
    }

    public String getTrailer_CountryCode() {
        return trailer_CountryCode;
    }

    public void setTrailer_CountryCode(String trailer_CountryCode) {
        this.trailer_CountryCode = trailer_CountryCode;
    }

    public String getTrailer_name() {
        return trailer_name;
    }

    public void setTrailer_name(String trailer_name) {
        this.trailer_name = trailer_name;
    }

    public String getMedia_key() {
        return media_key;
    }

    public void setMedia_key(String media_key) {
        this.media_key = media_key;
    }

    public String getMedia_on_website() {
        return media_on_website;
    }

    public void setMedia_on_website(String media_on_website) {
        this.media_on_website = media_on_website;
    }

    public int getMedia_resolution() {
        return media_resolution;
    }

    public void setMedia_resolution(int media_resolution) {
        this.media_resolution = media_resolution;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public void setOfficial(boolean official) {
        isOfficial = official;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getMediaTrailerId() {
        return mediaTrailerId;
    }

    public void setMediaTrailerId(String mediaTrailerId) {
        this.mediaTrailerId = mediaTrailerId;
    }

    @NonNull
    @Override
    public String toString() {
        return "MediaTypeArray{" +
                "trailerLangType='" + trailerLangType + '\'' +
                ", trailer_CountryCode='" + trailer_CountryCode + '\'' +
                ", trailer_name='" + trailer_name + '\'' +
                ", media_key='" + media_key + '\'' +
                ", media_on_website='" + media_on_website + '\'' +
                ", media_resolution=" + media_resolution +
                ", media_type='" + media_type + '\'' +
                ", isOfficial=" + isOfficial +
                ", publishDate='" + publishDate + '\'' +
                ", mediaTrailerId='" + mediaTrailerId + '\'' +
                '}';
    }
}
