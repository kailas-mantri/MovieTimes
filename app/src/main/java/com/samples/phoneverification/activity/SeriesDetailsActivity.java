package com.samples.phoneverification.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.R;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.controller.SeasonTextAdapter;
import com.samples.phoneverification.model.Seasons;
import com.samples.phoneverification.model.SeriesItemIdResults;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.databinding.ActivitySeriesDetailsBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SeriesDetailsActivity extends AppCompatActivity {

    ActivitySeriesDetailsBinding binding;
    private boolean isWishListed = false;
    private SeasonTextAdapter textAdapter;

//    private final ArrayList<SeriesItemIdResults> itemIdResults = new ArrayList<>();
    private final ArrayList<Seasons> seasons = new ArrayList<>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URLs.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    APIInterface anInterface = retrofit.create(APIInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeriesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarBack.setOnClickListener(v -> onBackPressed() );

        Intent intent = getIntent();
        int seriesId = (int) intent.getSerializableExtra("series_id");

        Call<SeriesItemIdResults> call = anInterface.SERIES_ITEM_ID_RESULTS_CALL(seriesId, URLs.API_KEY);
        call.enqueue(new Callback<SeriesItemIdResults>() {
            @Override
            public void onResponse(@NonNull Call<SeriesItemIdResults> call, @NonNull Response<SeriesItemIdResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SeriesItemIdResults itemResults = response.body();
                    displayUI(itemResults);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesItemIdResults> call, @NonNull Throwable t) {
                Log.w("SeriesItemId API", "onFailure: ", t.fillInStackTrace());
            }
        });

    }

    private void displayUI(SeriesItemIdResults itemResults) {
        AppBarItems(itemResults);

    }

    private void AppBarItems(SeriesItemIdResults itemResults) {
        // 1. Set Background - COLLAPSING Toolbar and title.
        Glide.with(binding.backdropPath).load(URLs.IMAGE_BASE_URL + itemResults.getBackdropPath())
                .into(binding.backdropPath);
        binding.seriesTitle.setText(itemResults.getSeriesTitle());

        binding.ratingBar.setRating((float)(itemResults.getVoteAverage() / 10)*5);

        binding.wishList.setOnClickListener(v -> {
            if (isWishListed) {
                isWishListed = false;
                binding.wishList.setText(R.string.wishList_add);
                binding.wishList.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.wishlist_add, 0, 0, 0);
            } else {
                isWishListed = true;
                binding.wishList.setText(R.string.wishlist_remove);
                binding.wishList.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.wishlist_remove, 0, 0, 0);
            }
        });

        binding.recyclerSeasons.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        initSeasonRecyclerAdapter();
    }

    private void initSeasonRecyclerAdapter() {
        textAdapter = new SeasonTextAdapter(getApplicationContext(), seasons, (item, position, action) ->  {
            int seasonVise = item.getSeasonNumber();
        });
        binding.recyclerSeasons.setAdapter(textAdapter);
    }
}