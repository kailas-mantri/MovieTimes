package com.samples.phoneverification.model;

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
    public int[] genreIds;

    @SerializedName("id")
    public int movieId;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("original_title")
    public String originalMovieTitle;

    @SerializedName("overview")
    public String movieOverview;

    @SerializedName("popularity")
    public double popularity;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public String movieReleaseDate;

    @SerializedName("title")
    public String standardMovieTitle;

    @SerializedName("video")
    public boolean trailerVideo;

    @SerializedName("vote_average")
    public double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;
    public MovieResults(boolean isAdult, String backdropPath, String mediaType, int[] genreIds, int movieId, String originalLanguage,
                        String originalMovieTitle, String movieOverview, double popularity, String posterPath, String movieReleaseDate,
                        String standardMovieTitle, boolean trailerVideo, double voteAverage, int voteCount) {
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

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
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

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
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

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieResults{" +
                "isAdult=" + isAdult +
                ", backdropPath='" + backdropPath + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", genreIds=" + Arrays.toString(genreIds) +
                ", movieId=" + movieId +
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
                '}';
    }
}
