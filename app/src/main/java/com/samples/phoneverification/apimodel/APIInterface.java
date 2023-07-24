package com.samples.phoneverification.apimodel;

import com.samples.phoneverification.model.CastModel;
import com.samples.phoneverification.model.GenresModel;
import com.samples.phoneverification.model.MovieIdDetails;
import com.samples.phoneverification.model.MediaGroup;
import com.samples.phoneverification.model.MovieModel;
import com.samples.phoneverification.model.SearchModel;
import com.samples.phoneverification.model.SeriesIdResults;
import com.samples.phoneverification.model.SeasonNoDetails;
import com.samples.phoneverification.model.SeriesModel;
import com.samples.phoneverification.model.WatchProvider;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIInterface {

    @GET(URLConstants.UPCOMING_MOVIE)
    Call<MovieModel> UP_COMING_MOVIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.TRENDING_MOVIE)
    Call<MovieModel> TRENDING_MOVIE_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.NOW_PLAYING)
    Call<MovieModel> NOW_PLAYING_MOVIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.TOP_RATED_MOVIE)
    Call<MovieModel> TOP_RATED_MOVIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.POPULAR_MOVIES)
    Call<MovieModel> POPULAR_MOVIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.TOP_RATED_SERIES)
    Call<SeriesModel> TOP_RATED_SERIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.POPULAR_SERIES)
    Call<SeriesModel> POPULAR_SERIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.UPCOMING_SERIES)
    Call<SeriesModel> UP_COMING_SERIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.MOVIE_GENRES_LIST)
    Call<GenresModel> MOVIE_GENRES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.DISCOVER_MOVIES)
    Call<MovieModel> MOVIE_DISCOVER_MODEL_CALL(
            @QueryMap HashMap<String, String> params
    );

    @GET(URLConstants.SERIES_GENRES_LIST)
    Call<GenresModel> SERIES_GENRE_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.DISCOVER_SERIES)
    Call<SeriesModel> SERIES_DISCOVER_MODEL_CALL(
            @QueryMap HashMap<String, String> params
    );

    @GET(URLConstants.SEARCH_VIEW)
    Call<SearchModel> SEARCH_MODEL_CALL(
            @QueryMap HashMap<String, String> params
    );

    @GET(URLConstants.MOVIE_ITEM_DETAILS)
    Call<MovieIdDetails> MOVIE_ID_DETAILS_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.MOVIE_MEDIA_GROUP)
    Call<MediaGroup> MOVIE_MEDIA_GROUP_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.MOVIE_CAST_CREDITS)
    Call<CastModel> MOVIE_CAST_MODEL_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.RECOMMENDED_MOVIES_BY_MOVIE_ID)
    Call<MovieModel> RECOMMENDED_MOVIES_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.SERIES_ITEM_DETAILS)
    Call<SeriesIdResults> SERIES_ITEM_ID_RESULTS_CALL(
            @Path("series_id") int seriesId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.SERIES_MEDIA_GROUP)
    Call<MediaGroup> SERIES_MEDIA_GROUP_CALL(
            @Path("series_id") int seriesId,
            @Query("api_key") String apiKey
    );
    @GET(URLConstants.SERIES_SEASON_ITEM_DETAILS)
    Call<SeasonNoDetails> SERIES_ITEM_SEASON_DETAILS_CALL(
            @Path("series_id") int seriesId,
            @Path("season_number") int season_no,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.SERIES_CAST_CREDITS)
    Call<CastModel> SERIES_CAST_MODEL_CALL(
            @Path("series_id") int seriesId,
            @Path("season_number") int season_no,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.RECOMMENDED_SERIES_BY_SERIES_ID)
    Call<SeriesModel> RECOMMENDED_SERIES_ITEM_RESULTS_CALL(
            @Path("series_id") int seriesId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.MOVIE_WATCH_PROVIDER)
    Call<WatchProvider> MOVIE_WATCH_PROVIDER_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.SERIES_WATCH_PROVIDER)
    Call<WatchProvider> SERIES_WATCH_PROVIDER_CALL(
            @Path("series_id") int seriesId,
            @Query("api_key") String apiKey
    );
}
