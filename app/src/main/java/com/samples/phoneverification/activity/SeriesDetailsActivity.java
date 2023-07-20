package com.samples.phoneverification.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.apimodel.SearchApiResults;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.databinding.ActivitySeriesDetailsBinding;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SeriesDetailsActivity extends AppCompatActivity {

    ActivitySeriesDetailsBinding binding;
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

        Intent intent = getIntent();
        int seriesId = (int) intent.getSerializableExtra("series_id");

    }
}