package com.samples.phoneverification.apimodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class SearchApiResults implements Serializable {

    @SerializedName("adult")
    public boolean isAdult;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("id")
    public Integer itemId;

    @SerializedName("name")
    public String standardSeriesTitle;

    @SerializedName("title")
    public String standardMovieTitle;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("original_name")
    public String originalSeriesTitle;

    @SerializedName("original_title")
    public String originalMovieTitle;

    @SerializedName("overview")
    public String itemOverview;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("media_type")
    public String mediaType;

    @SerializedName("genre_ids")
    public Integer[] genreIds;

    @SerializedName("popularity")
    public float popularity;

    @SerializedName("vote_average")
    public float voteAverage;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("origin_country")
    public String[] origin_country;

    @SerializedName("video")
    public boolean trailerVideo;

    @SerializedName("first_air_date")
    public String seriesFirstAirDate;

    @SerializedName("release_date")
    public String movieReleaseDate;

    public SearchApiResults(boolean isAdult, String backdropPath, Integer itemId, String standardSeriesTitle, String standardMovieTitle,
                            String originalLanguage, String originalSeriesTitle, String originalMovieTitle, String itemOverview,
                            String posterPath, String mediaType, Integer[] genreIds, float popularity, float voteAverage, Integer voteCount,
                            String[] origin_country, boolean trailerVideo, String seriesFirstAirDate, String movieReleaseDate) {
        this.isAdult = isAdult;
        this.backdropPath = backdropPath;
        this.itemId = itemId;
        this.standardSeriesTitle = standardSeriesTitle;
        this.standardMovieTitle = standardMovieTitle;
        this.originalLanguage = originalLanguage;
        this.originalSeriesTitle = originalSeriesTitle;
        this.originalMovieTitle = originalMovieTitle;
        this.itemOverview = itemOverview;
        this.posterPath = posterPath;
        this.mediaType = mediaType;
        this.genreIds = genreIds;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.origin_country = origin_country;
        this.trailerVideo = trailerVideo;
        this.seriesFirstAirDate = seriesFirstAirDate;
        this.movieReleaseDate = movieReleaseDate;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getStandardSeriesTitle() {
        return standardSeriesTitle;
    }

    public void setStandardSeriesTitle(String standardSeriesTitle) {
        this.standardSeriesTitle = standardSeriesTitle;
    }

    public String getStandardMovieTitle() {
        return standardMovieTitle;
    }

    public void setStandardMovieTitle(String standardMovieTitle) {
        this.standardMovieTitle = standardMovieTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalSeriesTitle() {
        return originalSeriesTitle;
    }

    public void setOriginalSeriesTitle(String originalSeriesTitle) {
        this.originalSeriesTitle = originalSeriesTitle;
    }

    public String getOriginalMovieTitle() {
        return originalMovieTitle;
    }

    public void setOriginalMovieTitle(String originalMovieTitle) {
        this.originalMovieTitle = originalMovieTitle;
    }

    public String getItemOverview() {
        return itemOverview;
    }

    public void setItemOverview(String itemOverview) {
        this.itemOverview = itemOverview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Integer[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(Integer[] genreIds) {
        this.genreIds = genreIds;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String[] getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String[] origin_country) {
        this.origin_country = origin_country;
    }

    public boolean isTrailerVideo() {
        return trailerVideo;
    }

    public void setTrailerVideo(boolean trailerVideo) {
        this.trailerVideo = trailerVideo;
    }

    public String getSeriesFirstAirDate() {
        return seriesFirstAirDate;
    }

    public void setSeriesFirstAirDate(String seriesFirstAirDate) {
        this.seriesFirstAirDate = seriesFirstAirDate;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    @Override
    public String toString() {
        return "SearchApiResults{" +
                "isAdult=" + isAdult +
                ", backdropPath='" + backdropPath + '\'' +
                ", itemId=" + itemId +
                ", standardSeriesTitle='" + standardSeriesTitle + '\'' +
                ", standardMovieTitle='" + standardMovieTitle + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalSeriesTitle='" + originalSeriesTitle + '\'' +
                ", originalMovieTitle='" + originalMovieTitle + '\'' +
                ", itemOverview='" + itemOverview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", genreIds=" + Arrays.toString(genreIds) +
                ", popularity=" + popularity +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", origin_country=" + Arrays.toString(origin_country) +
                ", trailerVideo=" + trailerVideo +
                ", seriesFirstAirDate='" + seriesFirstAirDate + '\'' +
                ", movieReleaseDate='" + movieReleaseDate + '\'' +
                '}';
    }
}
