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

import com.samples.phoneverification.activity.MovieDetailsActivity;
import com.samples.phoneverification.adapter.CarouselMAdapter;
import com.samples.phoneverification.adapter.SeriesAdapter;
import com.samples.phoneverification.adapter.MovieAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.apimodel.SeriesModel;
import com.samples.phoneverification.apimodel.SeriesResults;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.apimodel.MovieResults;
import com.samples.phoneverification.apimodel.MovieModel;
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
    private Integer movieId, seriesId;
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
            .baseUrl(URLs.BASE_URL)
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

        // TODO 01: Set All Adapters.
        initAllAdapters();

        // TODO 02: After Setting Adapter use Timer for Auto-Sliding
        CarouselSliders();

        // TODO 03: Set All LayoutManger for RecyclerView.
        recyclerLayoutMangers();

        // TODO 04: Set All API Calls.
        allApiCalls();

        return binding.getRoot();
    }

    private void recyclerLayoutMangers() {
        //TODO: 1. setLayoutManager, Trending Movies.
        binding.trendingMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //TODO: 2. setLayoutManager, upComing Series.
        binding.upComingSeries.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //TODO: 3. setLayoutManager, NowPlaying Movies.
        binding.nowPlayingMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //TODO: 4. setLayoutManager, TopRated Movies.
        binding.topRatedMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //TODO: 5. setLayoutManager, TopRated Series.
        binding.topRatedSeries.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //TODO: 6. setLayoutManager, Popular Movies.
        binding.popularMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //TODO: 7. setLayoutManager, Popular Series.
        binding.popularSeries.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }

    private void allApiCalls() {
        //TODO: 1. upcoming Movies.
        UpComingMovieCall();

        //TODO: 2. Trending Movies.
        TrendingMovieCall();

        // TODO 3. UpComing Series.
        UpComingSeriesCall();

        //TODO: 4. nowPlaying Movies.
        NowPlayingMovieCall();

        //TODO: 5. topRated Movies.
        TopRatedMovieCall();

        // TODO 6. topRated Series.
        TopRatedSeriesCall();

        //TODO: 7. popular Movies.
        PopularMovieCall();

        // TODO 8. Popular Series.
        PopularSeriesCall();
    }

    private void initAllAdapters() {
        // TODO: 1. Adapter Carousel.
        initAdapter_Carousel();

        // TODO: 2. Adapter TrendingMovies.
        initAdapter_trendingMovies();

        // TODO 3. Adapter upComingSeries.
        initAdapter_upComingSeries();

        // TODO 4. Adapter NowPlayingMovies.
        initAdapter_NowPlayingMovies();

        // TODO 5. Adapter topRatedMovies.
        initAdapter_topRatedMovies();

        // TODO 6. Adapter topRatedSeries.
        initAdapter_topRatedSeries();

        // TODO 7. Adapter popularMovies.
        initAdapter_popularMovies();

        // TODO 8. Adapter popularSeries.
        initAdapter_popularSeries();
    }

    private void PopularSeriesCall() {
        Call<SeriesModel> call = anInterface.POPULAR_SERIES_MODEL_CALL(URLs.API_KEY);
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
        popularSeriesAdapter = new SeriesAdapter(requireContext(), popularSeries, position -> {
            seriesId = popularSeries.get(position).getSeriesId();
//            Intent intent = new Intent(requireActivity(), SeriesDetailsActivity.class);
//            intent.putExtra("series_id", seriesId);
//            startActivity(intent);
            Log.d(getTag(), "initAdapter_topRatedSeries: "+popularSeries.toString());
        });
        binding.popularSeries.setAdapter(popularSeriesAdapter);
    }

    private void PopularMovieCall() {
        Call<MovieModel> call = anInterface.POPULAR_MOVIES_MODEL_CALL(URLs.API_KEY);
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
        popularMovieAdapter = new MovieAdapter(requireContext(), popularMovies, position ->{
            movieId = popularMovies.get(position).getMovieId();
            Intent intent = new Intent(requireActivity(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });
        binding.popularMovies.setAdapter(popularMovieAdapter);
    }

    private void TopRatedSeriesCall() {
        Call<SeriesModel> call = anInterface.TOP_RATED_SERIES_MODEL_CALL(URLs.API_KEY);
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
        topRatedSeriesAdapter = new SeriesAdapter(requireContext(), topRatedSeries, position -> {
            seriesId = topRatedSeries.get(position).getSeriesId();
//            Intent intent = new Intent(requireActivity(), SeriesDetailsActivity.class);
//            intent.putExtra("series_id", seriesId);
//            startActivity(intent);
            Log.d(getTag(), "initAdapter_topRatedSeries: "+topRatedSeries.toString());
        });
        binding.topRatedSeries.setAdapter(topRatedSeriesAdapter);
    }

    private void TopRatedMovieCall() {
        Call<MovieModel> call = anInterface.TOP_RATED_MOVIES_MODEL_CALL(URLs.API_KEY);
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
        topRatedMovieAdapter = new MovieAdapter(requireContext(), topRatedMovies, position -> {
            movieId = topRatedMovies.get(position).getMovieId();
            Intent intent = new Intent(requireActivity(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });
        binding.topRatedMovies.setAdapter(topRatedMovieAdapter);
    }

    private void NowPlayingMovieCall() {
        Call<MovieModel> modelCall = anInterface.NOW_PLAYING_MOVIES_MODEL_CALL(URLs.API_KEY);
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
        nowPlayingMovieAdapter = new MovieAdapter(requireContext(), nowPlayingMovies, position -> {
            movieId = nowPlayingMovies.get(position).getMovieId();
            Intent intent = new Intent(requireActivity(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });
        binding.nowPlayingMovies.setAdapter(nowPlayingMovieAdapter);
    }

    private void UpComingSeriesCall() {
        Call<SeriesModel> call = anInterface.UP_COMING_SERIES_MODEL_CALL(URLs.API_KEY);
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
        upComingSeriesAdapter = new SeriesAdapter(requireContext(), upComingSeries, position -> {
            seriesId = upComingSeries.get(position).getSeriesId();
//            Intent intent = new Intent(requireActivity(), SeriesDetailsActivity.class);
//            intent.putExtra("series_id", seriesId);
//            startActivity(intent);
            Log.d(getTag(), "initAdapter_topRatedSeries: "+upComingSeries.toString());
        });
        binding.upComingSeries.setAdapter(upComingSeriesAdapter);
    }

    private void TrendingMovieCall() {
        Call<MovieModel> modelCall = anInterface.TRENDING_MOVIE_MODEL_CALL(URLs.API_KEY);
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
        trendingMovieAdapter = new MovieAdapter(getContext(), trendingMovies, position -> {
            movieId = trendingMovies.get(position).getMovieId();
            Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", movieId);
            startActivity(intent);
        });
        binding.trendingMovies.setAdapter(trendingMovieAdapter);
    }

    private void CarouselSliders() {
        binding.myViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(slideRunnable);
                handler.postDelayed(slideRunnable, 2000);
            }
        });
    }

    private void UpComingMovieCall() {
        Call<MovieModel> moviesModelCall = anInterface.UP_COMING_MOVIES_MODEL_CALL(URLs.API_KEY);

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
        adapter = new CarouselMAdapter(requireContext(), upComingMovies, (position) -> {
            movieId = upComingMovies.get(position).getMovieId();
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