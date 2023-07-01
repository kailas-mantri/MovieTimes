package com.samples.phoneverification.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samples.phoneverification.adapter.CarouselMAdapter;
import com.samples.phoneverification.adapter.GenreMAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.apimodel.GenreModel;
import com.samples.phoneverification.apimodel.GenreResults;
import com.samples.phoneverification.apimodel.MovieModel;
import com.samples.phoneverification.apimodel.MovieResults;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.databinding.FragmentMoviesBinding;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MoviesFragment extends Fragment {

    FragmentMoviesBinding binding;
    CarouselMAdapter adapter;
    GenreMAdapter genreMAdapter;
    ArrayList<MovieResults> upComingMovies = new ArrayList<>();
    ArrayList<GenreResults> genreResults = new ArrayList<>();

    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URLs.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    APIInterface anInterface = retrofit.create(APIInterface.class);
    private final Handler handler = new Handler();
    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(getLayoutInflater());

        // TODO: 1. setRecycler LayoutManager
        binding.recyclerImageList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        // TODO: 2. All Adapter
        initAdapter();

        // TODO: 3. After Setting Adapter use Timer for Auto-Sliding
        CarouselSliders();

        // TODO: 4. Call all API.
        DynamicApiCalls();

        return binding.getRoot();
    }

    private void DynamicApiCalls() {
        // TODO: 2. Genre Model.
        dynamicGenreCall();

        // TODO: 1. upcoming Movies.
        UpComingMovieCall();

    }

    private void initAdapter() {
        // TODO 02: Genres Recycler Adapter
        init_GenreAdapter();

        // TODO 01: Carousel Adapter
        init_CarouselAdapter();
    }

    private void dynamicGenreCall() {
        Call<GenreModel> call = anInterface.MOVIE_GENRE_MODEL_CALL(URLs.API_KEY);
        call.enqueue(new Callback<GenreModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<GenreModel> call, @NonNull Response<GenreModel> response) {
                if (response.body() != null) {
                    genreResults = response.body().getGenresArray();
                    genreMAdapter.updateData(genreResults);
                }

                // TODO **Set parameters for second API call**
                for (int i = 0; i < genreResults.size(); i++) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("api_key", URLs.API_KEY);
                    params.put("with_genres", String.valueOf(genreResults.get(i).getId()));

                    Call<MovieModel> movieModelCall = anInterface.MOVIE_DISCOVER_MODEL_CALL(params);
                    int finalI = i;

                    movieModelCall.enqueue(new Callback<MovieModel>() {
                        @Override
                        public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                            if (response.body() != null) {
                                genreResults.get(finalI).setMovieResults(response.body().getMovieResults());
//                                System.out.println(genreResults);
                                genreMAdapter.updateData(genreResults);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                            Log.w(getTag(), "onFailure: "+t.getMessage());
                        }
                    });
                }

            }

            @Override
            public void onFailure(@NonNull Call<GenreModel> call, @NonNull Throwable t) {
                Log.w(getTag(), "onFailure: "+t.getMessage());
            }
        });
    }

    private void init_GenreAdapter() {
        genreMAdapter = new GenreMAdapter(requireContext(), genreResults, position -> {
            Log.d(getTag(), "init_GenreAdapter: "+genreResults.toString());
        });
        binding.recyclerImageList.setAdapter(genreMAdapter);
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

    private void init_CarouselAdapter() {
        adapter = new CarouselMAdapter(requireContext(), upComingMovies, position -> {
            Log.d(getTag(), upComingMovies.toString());
        });
        binding.carouselViewPager.setAdapter(adapter);
    }

    private void CarouselSliders() {
        binding.carouselViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(slideRunnable);
                handler.postDelayed(slideRunnable, 2000);
            }
        });
    }

    private final Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            binding.carouselViewPager.setCurrentItem(binding.carouselViewPager.getCurrentItem() + 1);
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