package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SeriesIdResults implements Serializable {

    @SerializedName("adult")
    public boolean isAdult;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("created_by")
    public ArrayList<SeriesCreatedBy> seriesCreatedBy;

    @SerializedName("episode_run_time")
    public int[] episodeRunTime;

    @SerializedName("first_air_date")
    public String firstAirDate;

    @SerializedName("genres")
    public ArrayList<GenresList> genreResults;

    @SerializedName("homepage")
    public String webHomepage;

    @SerializedName("id")
    public int seriesId;

    @SerializedName("in_production")
    public boolean inProduction;

    @SerializedName("languages")
    public String[] languageUsed;

    @SerializedName("last_air_date")
    public String lastAirDate;

    @SerializedName("last_episode_to_air")
    public SeriesLastEpisodeList seriesLastEpisodeList;

    @SerializedName("name")
    public String seriesName;

    @SerializedName("next_episode_to_air")
    public SeriesNextEpisodeList seriesNextEpisodeList;

    @SerializedName("networks")
    public ArrayList<SeriesNetworks> networks;

    @SerializedName("number_of_episodes")
    public int numberOfEpisodes;

    @SerializedName("number_of_seasons")
    public int numberOfSeasons;

    @SerializedName("origin_country")
    public String[] originCountry;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("original_name")
    public String originalName;

    @SerializedName("overview")
    public String series_Overview;

    @SerializedName("popularity")
    public double popularity;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("production_companies")
    public ArrayList<ProdCompanies> prodCompanies;

    @SerializedName("production_countries")
    public ArrayList<ProdCountries> prodCountries;

    @SerializedName("seasons")
    public ArrayList<Seasons> seasons;

    @SerializedName("spoken_languages")
    public ArrayList<SpokenLanguages> spokenLanguages;

    @SerializedName("status")
    public String status;

    @SerializedName("tagline")
    public String tagline;

    @SerializedName("type")
    public String type;

    @SerializedName("vote_average")
    public double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    public SeriesIdResults(boolean isAdult, String backdropPath, ArrayList<SeriesCreatedBy> seriesCreatedBy, int[] episodeRunTime,
                           String firstAirDate, ArrayList<GenresList> genreResults, String webHomepage, int seriesId, boolean inProduction,
                           String[] languageUsed, String lastAirDate, SeriesLastEpisodeList seriesLastEpisodeList, String seriesName,
                           SeriesNextEpisodeList seriesNextEpisodeList, ArrayList<SeriesNetworks> networks, int numberOfEpisodes, int numberOfSeasons,
                           String[] originCountry, String originalLanguage, String originalName, String series_Overview,
                           double popularity, String posterPath, ArrayList<ProdCompanies> prodCompanies,
                           ArrayList<ProdCountries> prodCountries, ArrayList<Seasons> seasons, ArrayList<SpokenLanguages> spokenLanguages,
                           String status, String tagline, String type, double voteAverage, int voteCount) {
        this.isAdult = isAdult;
        this.backdropPath = backdropPath;
        this.seriesCreatedBy = seriesCreatedBy;
        this.episodeRunTime = episodeRunTime;
        this.firstAirDate = firstAirDate;
        this.genreResults = genreResults;
        this.webHomepage = webHomepage;
        this.seriesId = seriesId;
        this.inProduction = inProduction;
        this.languageUsed = languageUsed;
        this.lastAirDate = lastAirDate;
        this.seriesLastEpisodeList = seriesLastEpisodeList;
        this.seriesName = seriesName;
        this.seriesNextEpisodeList = seriesNextEpisodeList;
        this.networks = networks;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.series_Overview = series_Overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.prodCompanies = prodCompanies;
        this.prodCountries = prodCountries;
        this.seasons = seasons;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.tagline = tagline;
        this.type = type;
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

    public ArrayList<SeriesCreatedBy> getSeriesCreatedBy() {
        return seriesCreatedBy;
    }

    public void setSeriesCreatedBy(ArrayList<SeriesCreatedBy> seriesCreatedBy) {
        this.seriesCreatedBy = seriesCreatedBy;
    }

    public int[] getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(int[] episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public ArrayList<GenresList> getGenreResults() {
        return genreResults;
    }

    public void setGenreResults(ArrayList<GenresList> genreResults) {
        this.genreResults = genreResults;
    }

    public String getWebHomepage() {
        return webHomepage;
    }

    public void setWebHomepage(String webHomepage) {
        this.webHomepage = webHomepage;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    public String[] getLanguageUsed() {
        return languageUsed;
    }

    public void setLanguageUsed(String[] languageUsed) {
        this.languageUsed = languageUsed;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public SeriesLastEpisodeList getLastEpisodeToAir() {
        return seriesLastEpisodeList;
    }

    public void setLastEpisodeToAir(SeriesLastEpisodeList seriesLastEpisodeList) {
        this.seriesLastEpisodeList = seriesLastEpisodeList;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public SeriesNextEpisodeList getNextEpisodeToAir() {
        return seriesNextEpisodeList;
    }

    public void setNextEpisodeToAir(SeriesNextEpisodeList seriesNextEpisodeList) {
        this.seriesNextEpisodeList = seriesNextEpisodeList;
    }

    public ArrayList<SeriesNetworks> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<SeriesNetworks> networks) {
        this.networks = networks;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String[] getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String[] originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSeries_Overview() {
        return series_Overview;
    }

    public void setSeries_Overview(String series_Overview) {
        this.series_Overview = series_Overview;
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

    public ArrayList<ProdCompanies> getProductionCompanies() {
        return prodCompanies;
    }

    public void setProductionCompanies(ArrayList<ProdCompanies> prodCompanies) {
        this.prodCompanies = prodCompanies;
    }

    public ArrayList<ProdCountries> getProductionCountries() {
        return prodCountries;
    }

    public void setProductionCountries(ArrayList<ProdCountries> prodCountries) {
        this.prodCountries = prodCountries;
    }

    public ArrayList<Seasons> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Seasons> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<SpokenLanguages> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(ArrayList<SpokenLanguages> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "SeriesItemIdResults{" +
                "isAdult=" + isAdult +
                ", backdropPath='" + backdropPath + '\'' +
                ", seriesCreatedBy=" + seriesCreatedBy +
                ", episodeRunTime=" + Arrays.toString(episodeRunTime) +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", genreResults=" + genreResults +
                ", webHomepage='" + webHomepage + '\'' +
                ", seriesId=" + seriesId +
                ", inProduction=" + inProduction +
                ", languageUsed=" + Arrays.toString(languageUsed) +
                ", lastAirDate='" + lastAirDate + '\'' +
                ", lastEpisodeToAir=" + seriesLastEpisodeList +
                ", seriesName='" + seriesName + '\'' +
                ", nextEpisodeToAir=" + seriesNextEpisodeList +
                ", networks=" + networks +
                ", numberOfEpisodes=" + numberOfEpisodes +
                ", numberOfSeasons=" + numberOfSeasons +
                ", originCountry=" + Arrays.toString(originCountry) +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalName='" + originalName + '\'' +
                ", seriesOverview='" + series_Overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", productionCompanies=" + prodCompanies +
                ", productionCountries=" + prodCountries +
                ", seasons=" + seasons +
                ", spokenLanguages=" + spokenLanguages +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", type='" + type + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}
