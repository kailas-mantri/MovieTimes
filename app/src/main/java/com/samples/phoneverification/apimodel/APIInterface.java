package com.samples.phoneverification.apimodel;

import com.samples.phoneverification.model.CastPOJOModel;
import com.samples.phoneverification.model.GenreModel;
import com.samples.phoneverification.model.MovieItemDetails;
import com.samples.phoneverification.model.MovieMediaGroup;
import com.samples.phoneverification.model.MovieModel;
import com.samples.phoneverification.model.SearchApiModel;
import com.samples.phoneverification.model.SeriesItemIdResults;
import com.samples.phoneverification.model.SeriesModel;

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

    @GET(URLConstants.MOVIES_GENRES_LIST)
    Call<GenreModel> MOVIE_GENRE_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.DISCOVER_MOVIES)
    Call<MovieModel> MOVIE_DISCOVER_MODEL_CALL(
            @QueryMap HashMap<String, String> params
    );

    @GET(URLConstants.SERIES_GENRES_LIST)
    Call<GenreModel> SERIES_GENRE_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.DISCOVER_SERIES)
    Call<SeriesModel> SERIES_DISCOVER_MODEL_CALL(
            @QueryMap HashMap<String, String> params
    );

    @GET(URLConstants.SEARCH_VIEW)
    Call<SearchApiModel> SEARCH_MODEL_CALL(
            @QueryMap HashMap<String, String> params
    );

    @GET(URLConstants.MOVIE_ITEM_DETAILS)
    Call<MovieItemDetails> MOVIE_ITEM_DETAILS_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.MOVIE_MEDIA_GROUP)
    Call<MovieMediaGroup> MOVIE_MEDIA_GROUP_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.MOVIE_CAST_CREDITS)
    Call<CastPOJOModel> CAST_POJO_MODEL_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.RECOMMENDED_MOVIES_BY_MOVIE_ID)
    Call<MovieModel> RECOMMENDED_MOVIES_CALL(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET(URLConstants.SERIES_ITEM_DETAILS)
    Call<SeriesItemIdResults> SERIES_ITEM_ID_RESULTS_CALL(
            @Path("series_id") int seriesId,
            @Query("api_key") String apiKey
    );

}
