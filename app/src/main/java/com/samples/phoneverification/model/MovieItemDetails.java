package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieItemDetails implements Serializable {

    @SerializedName("adult")
    public boolean isAdult;

    @SerializedName("backdrop_path")
    public String backdrop_path;

    @SerializedName("belongs_to_collection")
    public BelongToCollections belongToCollections;

    @SerializedName("budget")
    public Integer movie_budget;

    @SerializedName("genres")
    public ArrayList<GenreResults> genresArray;

    @SerializedName("homepage")
    public String movie_homePage;

    @SerializedName("id")
    public Integer movie_id;

    @SerializedName("imdb_id")
    public String imdb_id;

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

    @SerializedName("production_companies")
    public ArrayList<ProductionCompanies> productionCompanies;

    @SerializedName("production_countries")
    public ArrayList<ProductionCountries> productionCountries;

    @SerializedName("release_date")
    public String movie_release_date;

    @SerializedName("revenue")
    public Integer movie_revenue;

    @SerializedName("runtime")
    public Integer movie_play_time;

    @SerializedName("spoken_languages")
    public ArrayList<SpokenLanguages> spoken_language;

    @SerializedName("status")
    public String movie_release_status;

    @SerializedName("tagline")
    public String movie_tagline;

    @SerializedName("title")
    public String standardMovieTitle;

    @SerializedName("video")
    public boolean trailerVideo;

    @SerializedName("vote_average")
    public double voteAverage;

    @SerializedName("vote_count")
    private Integer voteCount;

    public MovieItemDetails(boolean isAdult, String backdrop_path, BelongToCollections belongToCollections, Integer movie_budget,
                            ArrayList<GenreResults> genresArray, String movie_homePage, Integer movie_id, String imdb_id, String originalLanguage,
                            String originalMovieTitle, String movieOverview, double popularity, String posterPath, ArrayList<ProductionCompanies> productionCompanies,
                            ArrayList<ProductionCountries> productionCountries, String movie_release_date, Integer movie_revenue, Integer movie_play_time, ArrayList<SpokenLanguages> spoken_language, String movie_release_status, String movie_tagline, String standardMovieTitle, boolean trailerVideo, double voteAverage, Integer voteCount) {
        this.isAdult = isAdult;
        this.backdrop_path = backdrop_path;
        this.belongToCollections = belongToCollections;
        this.movie_budget = movie_budget;
        this.genresArray = genresArray;
        this.movie_homePage = movie_homePage;
        this.movie_id = movie_id;
        this.imdb_id = imdb_id;
        this.originalLanguage = originalLanguage;
        this.originalMovieTitle = originalMovieTitle;
        this.movieOverview = movieOverview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.movie_release_date = movie_release_date;
        this.movie_revenue = movie_revenue;
        this.movie_play_time = movie_play_time;
        this.spoken_language = spoken_language;
        this.movie_release_status = movie_release_status;
        this.movie_tagline = movie_tagline;
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

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public BelongToCollections getBelongToCollections() {
        return belongToCollections;
    }

    public void setBelongToCollections(BelongToCollections belongToCollections) {
        this.belongToCollections = belongToCollections;
    }

    public Integer getMovie_budget() {
        return movie_budget;
    }

    public void setMovie_budget(Integer movie_budget) {
        this.movie_budget = movie_budget;
    }

    public ArrayList<GenreResults> getGenresArray() {
        return genresArray;
    }

    public void setGenresArray(ArrayList<GenreResults> genresArray) {
        this.genresArray = genresArray;
    }

    public String getMovie_homePage() {
        return movie_homePage;
    }

    public void setMovie_homePage(String movie_homePage) {
        this.movie_homePage = movie_homePage;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
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

    public ArrayList<ProductionCompanies> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ArrayList<ProductionCompanies> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public ArrayList<ProductionCountries> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(ArrayList<ProductionCountries> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getMovie_release_date() {
        return movie_release_date;
    }

    public void setMovie_release_date(String movie_release_date) {
        this.movie_release_date = movie_release_date;
    }

    public Integer getMovie_revenue() {
        return movie_revenue;
    }

    public void setMovie_revenue(Integer movie_revenue) {
        this.movie_revenue = movie_revenue;
    }

    public Integer getMovie_play_time() {
        return movie_play_time;
    }

    public void setMovie_play_time(Integer movie_play_time) {
        this.movie_play_time = movie_play_time;
    }

    public ArrayList<SpokenLanguages> getSpoken_language() {
        return spoken_language;
    }

    public void setSpoken_language(ArrayList<SpokenLanguages> spoken_language) {
        this.spoken_language = spoken_language;
    }

    public String getMovie_release_status() {
        return movie_release_status;
    }

    public void setMovie_release_status(String movie_release_status) {
        this.movie_release_status = movie_release_status;
    }

    public String getMovie_tagline() {
        return movie_tagline;
    }

    public void setMovie_tagline(String movie_tagline) {
        this.movie_tagline = movie_tagline;
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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieItemDetails{" +
                "isAdult=" + isAdult +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", belongToCollections=" + belongToCollections +
                ", movie_budget=" + movie_budget +
                ", genresArray=" + genresArray +
                ", movie_homePage='" + movie_homePage + '\'' +
                ", movie_id=" + movie_id +
                ", imdb_id='" + imdb_id + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalMovieTitle='" + originalMovieTitle + '\'' +
                ", movieOverview='" + movieOverview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", productionCompanies=" + productionCompanies +
                ", productionCountries=" + productionCountries +
                ", movie_release_date='" + movie_release_date + '\'' +
                ", movie_revenue=" + movie_revenue +
                ", movie_play_time=" + movie_play_time +
                ", spoken_language=" + spoken_language +
                ", movie_release_status='" + movie_release_status + '\'' +
                ", movie_tagline='" + movie_tagline + '\'' +
                ", standardMovieTitle='" + standardMovieTitle + '\'' +
                ", trailerVideo=" + trailerVideo +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}