package com.samples.phoneverification.apimodel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIInterface {

    @GET("3/movie/upcoming")
    Call<MovieModel> UP_COMING_MOVIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/trending/movie/day")
    Call<MovieModel> TRENDING_MOVIE_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/movie/now_playing")
    Call<MovieModel> NOW_PLAYING_MOVIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/movie/top_rated")
    Call<MovieModel> TOP_RATED_MOVIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/movie/popular")
    Call<MovieModel> POPULAR_MOVIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/tv/top_rated")
    Call<SeriesModel> TOP_RATED_SERIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/tv/popular")
    Call<SeriesModel> POPULAR_SERIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/tv/on_the_air")
    Call<SeriesModel> UP_COMING_SERIES_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/genre/movie/list")
    Call<GenreModel> MOVIE_GENRE_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/discover/movie")
    Call<MovieModel> MOVIE_DISCOVER_MODEL_CALL(
            @QueryMap HashMap<String, String> params
    );

    @GET("3/genre/tv/list")
    Call<GenreModel> SERIES_GENRE_MODEL_CALL(
            @Query("api_key") String apiKey
    );

    @GET("3/discover/tv")
    Call<SeriesModel> SERIES_DISCOVER_MODEL_CALL(
            @QueryMap HashMap<String, String> params
    );
}
