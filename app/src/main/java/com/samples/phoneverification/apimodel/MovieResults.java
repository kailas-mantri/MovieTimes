package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class MovieResults implements Serializable {

    @SerializedName("adult")
    public boolean isAdult;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("media_type")
    public String mediaType;

    @SerializedName("genre_ids")
    public Integer[] genreIds;

    @SerializedName("id")
    public Integer movieId;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("original_title")
    public String originalMovieTitle;

    @SerializedName("overview")
    public String movieOverview;

    @SerializedName("popularity")
    public float popularity;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public String movieReleaseDate;

    @SerializedName("title")
    public String standardMovieTitle;

    @SerializedName("video")
    public boolean trailerVideo;

    @SerializedName("vote_average")
    public float voteAverage;

    @SerializedName("vote_count")
    private Integer voteCount;

    public MovieResults(boolean isAdult, String backdropPath, String mediaType, Integer[] genreIds, Integer movieId, String originalLanguage,
                        String originalMovieTitle, String movieOverview, float popularity, String posterPath, String movieReleaseDate,
                        String standardMovieTitle, boolean trailerVideo, float voteAverage, Integer voteCount) {
        this.isAdult = isAdult;
        this.backdropPath = backdropPath;
        this.mediaType = mediaType;
        this.genreIds = genreIds;
        this.movieId = movieId;
        this.originalLanguage = originalLanguage;
        this.originalMovieTitle = originalMovieTitle;
        this.movieOverview = movieOverview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.movieReleaseDate = movieReleaseDate;
        this.standardMovieTitle = standardMovieTitle;
        this.trailerVideo = trailerVideo;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
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

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalMovieTitle() {
        return originalMovieTitle;
    }

    public void setOriginalMovieTitle(String originalMovieTitle) {
        this.originalMovieTitle = originalMovieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getStandardMovieTitle() {
        return standardMovieTitle;
    }

    public void setStandardMovieTitle(String standardMovieTitle) {
        this.standardMovieTitle = standardMovieTitle;
    }

    public boolean isTrailerVideo() {
        return trailerVideo;
    }

    public void setTrailerVideo(boolean trailerVideo) {
        this.trailerVideo = trailerVideo;
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

    @NonNull
    @Override
    public String toString() {
        return "MovieResults{" +
                "isAdult=" + isAdult +
                ", backdropPath='" + backdropPath + '\'' +
                ", genreIds=" + Arrays.toString(genreIds) +
                ", MovieId=" + movieId +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalMovieTitle='" + originalMovieTitle + '\'' +
                ", movieOverview='" + movieOverview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", movieReleaseDate='" + movieReleaseDate + '\'' +
                ", standardMovieTitle='" + standardMovieTitle + '\'' +
                ", trailerVideo=" + trailerVideo +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}
