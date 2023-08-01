package com.samples.phoneverification.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.samples.phoneverification.BuildConfig;
import com.samples.phoneverification.activity.MovieDetailsActivity;
import com.samples.phoneverification.activity.SeriesDetailsActivity;
import com.samples.phoneverification.adapters.CarouselMAdapter;
import com.samples.phoneverification.adapters.SeriesAdapter;
import com.samples.phoneverification.adapters.MovieAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.model.SeriesModel;
import com.samples.phoneverification.model.SeriesResults;
import com.samples.phoneverification.model.MovieResults;
import com.samples.phoneverification.model.MovieModel;
import com.samples.phoneverification.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CarouselMAdapter adapter;
    private MovieAdapter trendingMovieAdapter, nowPlayingMovieAdapter, topRatedMovieAdapter, popularMovieAdapter;
    private SeriesAdapter topRatedSeriesAdapter, popularSeriesAdapter, upComingSeriesAdapter;
    private ArrayList<MovieResults> upComingMovies = new ArrayList<>();
    private ArrayList<MovieResults> trendingMovies = new ArrayList<>();
    private ArrayList<MovieResults> nowPlayingMovies = new ArrayList<>();
    private ArrayList<MovieResults> topRatedMovies = new ArrayList<>();
    private ArrayList<MovieResults> popularMovies = new ArrayList<>();
    private ArrayList<SeriesResults> topRatedSeries = new ArrayList<>();
    private ArrayList<SeriesResults> popularSeries = new ArrayList<>();
    private ArrayList<SeriesResults> upComingSeries = new ArrayList<>();
    private final Handler handler = new Handler();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private APIInterface anInterface;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        anInterface = retrofit.create(APIInterface.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        // TODO 01: Set allAdapters.
        initAdapters();

        // TODO 02: After Setting Adapter use Timer for Auto-Sliding
        CarouselSlider();

        // TODO 03: Set LayoutManger -RecyclerView.
        setLayoutMangers();

        // TODO 04: Set allAPICalls.
        apiCallbacks();

        return binding.getRoot();
    }

    private void setLayoutMangers() {
        //TODO: Trending Movies,  upComing Series, NowPlaying Movies, TopRated Movies, TopRated Series, Popular Movies, Popular Series.
        binding.trendingMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.upComingSeries.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.nowPlayingMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.topRatedMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.topRatedSeries.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.popularMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.popularSeries.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }

    private void apiCallbacks() {
        /*TODO: 1. upcoming, Trending, nowPlaying, topRated, popular Movies.
         *TODO: 2. upcoming, topRated, popular Series.*/
        UpComingMovieCallback();
        TrendingMovieCallback();
        UpComingSeriesCallback();
        NowPlayingMovieCallback();
        TopRatedMovieCallback();
        TopRatedSeriesCallback();
        PopularMovieCallback();
        PopularSeriesCallback();
    }

    private void initAdapters() {
        // TODO: Adapters:  Carousel, TrendingMovies, upComingSeries, NowPlayingMovies, topRatedMovies, topRatedSeries, popularMovies, popularSeries.
        initAdapter_Carousel();
        initAdapter_trendingMovies();
        initAdapter_upComingSeries();
        initAdapter_NowPlayingMovies();
        initAdapter_topRatedMovies();
        initAdapter_topRatedSeries();
        initAdapter_popularMovies();
        initAdapter_popularSeries();
    }

    private void PopularSeriesCallback() {
        Call<SeriesModel> call = anInterface.POPULAR_SERIES_MODEL_CALL(BuildConfig.API_KEY);
        call.enqueue(new Callback<SeriesModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SeriesModel> call, @NonNull Response<SeriesModel> response) {
                if ((response.isSuccessful()) && (response.body() != null)) {
                    popularSeries = response.body().getSeriesResults();
                    popularSeriesAdapter.updateData(popularSeries);
                    popularSeriesAdapter.notifyDataSetChanged();
                } else {
                    Log.d(getTag(), "onResponse: "+topRatedSeries.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesModel> call, @NonNull Throwable t) {
                Log.d(getTag(), "onFailure: "+t.getMessage());
            }
        });
    }

    private void initAdapter_popularSeries() {
        popularSeriesAdapter = new SeriesAdapter(requireContext(), popularSeries, (item, position, action) -> {
            int seriesId = item.getSeriesId();
            Intent intent = new Intent(requireActivity(), SeriesDetailsActivity.class);
            intent.putExtra("series_id", seriesId);
            startActivity(intent);
        });
        binding.popularSeries.setAdapter(popularSeriesAdapter);
    }

    private void PopularMovieCallback() {
        Call<MovieModel> call = anInterface.POPULAR_MOVIES_MODEL_CALL(BuildConfig.API_KEY);
        call.enqueue(new Callback<MovieModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                if ((response.isSuccessful()) && (response.body() != null)) {
                    popularMovies = response.body().getMovieResults();
                    popularMovieAdapter.updateData(popularMovies);
                    popularMovieAdapter.notifyDataSetChanged();
                } else {
                    Log.w(getTag(), "onResponse: "+popularMovies.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Log.w(getTag(), "onFailure: "+t.getMessage());
            }
        });
    }

    private void initAdapter_popularMovies() {
        popularMovieAdapter = new MovieAdapter(requireContext(), popularMovies, (item, position, action) -> {
            int movieId = item.getMovieId();
            Intent intent = new Intent(requireActivity(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });
        binding.popularMovies.setAdapter(popularMovieAdapter);
    }

    private void TopRatedSeriesCallback() {
        Call<SeriesModel> call = anInterface.TOP_RATED_SERIES_MODEL_CALL(BuildConfig.API_KEY);
        call.enqueue(new Callback<SeriesModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SeriesModel> call, @NonNull Response<SeriesModel> response) {
                if ((response.isSuccessful()) && (response.body() != null)) {
                    topRatedSeries = response.body().getSeriesResults();
                    topRatedSeriesAdapter.updateData(topRatedSeries);
                    topRatedSeriesAdapter.notifyDataSetChanged();
                } else {
                    Log.d(getTag(), "onResponse: "+topRatedSeries.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesModel> call, @NonNull Throwable t) {
                Log.d(getTag(), "onFailure: "+t.getMessage());
            }
        });
    }

    private void initAdapter_topRatedSeries() {
        topRatedSeriesAdapter = new SeriesAdapter(requireContext(), topRatedSeries, (item, position, action) -> {
            int seriesId = item.getSeriesId();
            Intent intent = new Intent(requireActivity(), SeriesDetailsActivity.class);
            intent.putExtra("series_id", seriesId);
            startActivity(intent);
            Log.d(getTag(), "initAdapter_topRatedSeries: "+topRatedSeries.toString());
        });
        binding.topRatedSeries.setAdapter(topRatedSeriesAdapter);
    }

    private void TopRatedMovieCallback() {
        Call<MovieModel> call = anInterface.TOP_RATED_MOVIES_MODEL_CALL(BuildConfig.API_KEY);
        call.enqueue(new Callback<MovieModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                if ((response.isSuccessful()) && (response.body() != null)) {
                    topRatedMovies = response.body().getMovieResults();
                    topRatedMovieAdapter.updateData(topRatedMovies);
                    topRatedMovieAdapter.notifyDataSetChanged();
                } else {
                    Log.w(getTag(), "onResponse: "+trendingMovies.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Log.w(getTag(), "onFailure: "+ t.getMessage());
            }
        });
    }

    private void initAdapter_topRatedMovies() {
        topRatedMovieAdapter = new MovieAdapter(requireContext(), topRatedMovies, (item, position, action) -> {
            int movieId = item.getMovieId();
            Intent intent = new Intent(requireActivity(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });
        binding.topRatedMovies.setAdapter(topRatedMovieAdapter);
    }

    private void NowPlayingMovieCallback() {
        Call<MovieModel> modelCall = anInterface.NOW_PLAYING_MOVIES_MODEL_CALL(BuildConfig.API_KEY);
        modelCall.enqueue(new Callback<MovieModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                if ((response.isSuccessful()) && (response.body() != null)) {
                    nowPlayingMovies = response.body().getMovieResults();
                    nowPlayingMovieAdapter.updateData(nowPlayingMovies);
                    nowPlayingMovieAdapter.notifyDataSetChanged();
                } else {
                    Log.w(getTag(), "onResponse: "+nowPlayingMovies.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Log.w(getTag(), "onFailure: "+t.getMessage());
            }
        });
    }

    private void initAdapter_NowPlayingMovies() {
        nowPlayingMovieAdapter = new MovieAdapter(requireContext(), nowPlayingMovies, (item, position, action) -> {
            int movieId = item.getMovieId();
            Intent i = new Intent(requireActivity(), MovieDetailsActivity.class);
            i.putExtra("movie_id", movieId);
            startActivity(i);
        });
        binding.nowPlayingMovies.setAdapter(nowPlayingMovieAdapter);
    }

    private void UpComingSeriesCallback() {
        Call<SeriesModel> call = anInterface.UP_COMING_SERIES_MODEL_CALL(BuildConfig.API_KEY);
        call.enqueue(new Callback<SeriesModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SeriesModel> call, @NonNull Response<SeriesModel> response) {
                if ((response.isSuccessful()) && (response.body() != null)) {
                    upComingSeries = response.body().getSeriesResults();
                    upComingSeriesAdapter.updateData(upComingSeries);
                    upComingSeriesAdapter.notifyDataSetChanged();
                } else {
                    Log.d(getTag(), "onResponse: "+upComingSeries.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesModel> call, @NonNull Throwable t) {
                Log.d(getTag(), "onFailure: "+t.getMessage());
            }
        });
    }

    private void initAdapter_upComingSeries() {
        upComingSeriesAdapter = new SeriesAdapter(requireContext(), upComingSeries, (item, position, action) -> {
            int seriesId = item.getSeriesId();
            Intent intent = new Intent(requireActivity(), SeriesDetailsActivity.class);
            intent.putExtra("series_id", seriesId);
            startActivity(intent);
        });
        binding.upComingSeries.setAdapter(upComingSeriesAdapter);
    }

    private void TrendingMovieCallback() {
        Call<MovieModel> modelCall = anInterface.TRENDING_MOVIE_MODEL_CALL(BuildConfig.API_KEY);
        modelCall.enqueue(new Callback<MovieModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    MovieModel model = response.body();
                    if (model != null) {
                        trendingMovies = model.getMovieResults();
                        trendingMovieAdapter.updateData(trendingMovies);
                        trendingMovieAdapter.notifyDataSetChanged();
                    } else {
                            Log.w(getTag(), "onResponse: "+trendingMovies.toString());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Log.w(getTag(), "onFailure: "+t.getMessage());
            }
        });
    }

    private void initAdapter_trendingMovies() {
        trendingMovieAdapter = new MovieAdapter(getContext(), trendingMovies, (item, position, action) -> {
            int movieId = item.getMovieId();
            Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });
        binding.trendingMovies.setAdapter(trendingMovieAdapter);
    }

    private void CarouselSlider() {
        binding.myViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(slideRunnable);
                handler.postDelayed(slideRunnable, 2000);
            }
        });
    }

    private void UpComingMovieCallback() {
        Call<MovieModel> moviesModelCall = anInterface.UP_COMING_MOVIES_MODEL_CALL(BuildConfig.API_KEY);

        moviesModelCall.enqueue(new Callback<MovieModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    MovieModel movieModel = response.body();
                    if(movieModel != null) {
                        upComingMovies = movieModel.getMovieResults();
                        adapter.updateData(upComingMovies);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.w(getTag(), "onResponse: "+upComingMovies.toString());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Log.e(getTag(), "UpComingMovie Failed: " + t.getMessage());
            }
        });
    }

    private void initAdapter_Carousel() {
        adapter = new CarouselMAdapter(requireContext(), upComingMovies, (item, position, action) -> {
            int movieId = upComingMovies.get(position).getMovieId();
            Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });
        binding.myViewPager.setAdapter(adapter);
    }

    private final Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            binding.myViewPager.setCurrentItem(binding.myViewPager.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(slideRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(slideRunnable, 2000);
    }
}