package com.samples.phoneverification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.samples.phoneverification.databinding.ActivityStoreBinding;

public class StoreActivity extends AppCompatActivity {

    ActivityStoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(view -> onBackPressed());
    }
}