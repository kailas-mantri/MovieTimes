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
import com.samples.phoneverification.activity.SeriesDetailsActivity;
import com.samples.phoneverification.adapters.CarouselSAdapter;
import com.samples.phoneverification.adapters.GenreSAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.model.GenresModel;
import com.samples.phoneverification.model.GenresList;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;
import com.samples.phoneverification.model.SeriesModel;
import com.samples.phoneverification.model.SeriesResults;
import com.samples.phoneverification.databinding.FragmentSeriesBinding;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SeriesFragment extends Fragment {

    FragmentSeriesBinding binding;
    CarouselSAdapter carouselSAdapter;
    GenreSAdapter genreAdapter;
    ArrayList<SeriesResults> seriesResults = new ArrayList<>();
    ArrayList<GenresList> genreResults = new ArrayList<>();
    private APIInterface anInterface;
    private final Handler handler = new Handler();

    public SeriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        anInterface = retrofit.create(APIInterface.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO:1. Inflate the layout for this fragment
        binding = FragmentSeriesBinding.inflate(getLayoutInflater());

        // TODO:2. SetLayoutManger - GenreRecycler
        binding.recyclerImageList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        // TODO:3. Initialize Carousel Slider
        carouselSlider();
        // TODO:4. Initialize All Adapters
        initAdapters();
        //TODO:6. Call APIs.
        AllApiCalls();

        return binding.getRoot();
    }

    private void AllApiCalls() {
        // TODO:7. upComingSAPICall.
        upComingSAPICall();

        //TODO;9. DynamicSeriesRecyclerCall.
        DynamicSeriesRecyclerCall();
    }

    private void DynamicSeriesRecyclerCall() {
        Call<GenresModel> call = anInterface.SERIES_GENRE_MODEL_CALL(BuildConfig.API_KEY);
        call.enqueue(new Callback<GenresModel>() {
            @Override
            public void onResponse(@NonNull Call<GenresModel> call, @NonNull Response<GenresModel> response) {
                if (response.body() != null) {
                    genreResults = response.body().getGenresArray();
                    genreAdapter.updateData(genreResults);
                }

                // TODO **Set parameters for second API call**
                for (int i = 0; i < genreResults.size(); i++) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("api_key", BuildConfig.API_KEY);
                    params.put("with_genres", String.valueOf(genreResults.get(i).getId()));

                    Call<SeriesModel> seriesModelCall = anInterface.SERIES_DISCOVER_MODEL_CALL(params);

                    int finalI = i;
                    seriesModelCall.enqueue(new Callback<SeriesModel>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onResponse(@NonNull Call<SeriesModel> call, @NonNull Response<SeriesModel> response) {
                            if (response.body() != null) {
                                genreResults.get(finalI).setSeriesList(response.body().getSeriesResults());
//                                System.out.println(genreResults);
                                genreAdapter.updateData(genreResults);
                                genreAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<SeriesModel> call, @NonNull Throwable t) {
                            Log.e(getTag(), "onFailure: "+t.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenresModel> call, @NonNull Throwable t) {
                Log.e(getTag(), "onFailure: "+t.getMessage());
            }
        });
    }

    private void upComingSAPICall() {
        Call<SeriesModel> up_coming_series_model_call = anInterface.UP_COMING_SERIES_MODEL_CALL(BuildConfig.API_KEY);
        up_coming_series_model_call.enqueue(new Callback<SeriesModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SeriesModel> call, @NonNull Response<SeriesModel> response) {
                if (response.body() != null) {
                    seriesResults = response.body().getSeriesResults();
                    carouselSAdapter.updateData(seriesResults);
                    carouselSAdapter.notifyDataSetChanged();
                } else {
                    Log.e(getTag(), "onResponse: " + up_coming_series_model_call);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesModel> call, @NonNull Throwable t) {
                Log.e(getTag(), "onFailure: " + t.getMessage());
            }
        });
    }

    private void initAdapters() {
        //TODO:5. Carousel Adapter
        init_CarouselAdapter();

        //TODO:8.INITIALIZE GenreAdapter
        init_GenreAdapter();
    }

    private void init_GenreAdapter() {
        genreAdapter = new GenreSAdapter(requireContext(), genreResults, new OnRecyclerItemClickListener<SeriesResults>() {
            @Override
            public void onItemClicked(SeriesResults item, int position, int action) {
                int seriesId = item.getSeriesId();
                Intent intent = new Intent(requireActivity(), SeriesDetailsActivity.class);
                intent.putExtra("series_id", seriesId);
                startActivity(intent);
            }
        });
        binding.recyclerImageList.setAdapter(genreAdapter);
    }

    private void init_CarouselAdapter() {
        carouselSAdapter = new CarouselSAdapter(requireContext(), seriesResults, (item, position, action) -> {
            int seriesId = item.getSeriesId();
            Intent i = new Intent(requireActivity(), SeriesDetailsActivity.class);
            i.putExtra("series_id", seriesId);
            startActivity(i);
        });
        binding.carouselViewPager.setAdapter(carouselSAdapter);
    }
    private void carouselSlider() {
        binding.carouselViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }
        });
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            binding.carouselViewPager.setCurrentItem(binding.carouselViewPager.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }
}