package com.samples.phoneverification.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.samples.phoneverification.apimodel.SearchApiResults;
import com.samples.phoneverification.databinding.ActivitySeriesDetailsBinding;

public class SeriesDetailsActivity extends AppCompatActivity {

    ActivitySeriesDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeriesDetailsBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        SearchApiResults apiResults = (SearchApiResults) intent.getSerializableExtra("selectedItemResult");
//        if (apiResults.getMediaType().equals("movie")) {
//
//        }
        System.out.println(apiResults.getMediaType() + " : "+ apiResults.getStandardMovieTitle());
        binding.movieName.setText(apiResults.getStandardMovieTitle());

        setContentView(binding.getRoot());
    }
}