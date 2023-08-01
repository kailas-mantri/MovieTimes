package com.samples.phoneverification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.samples.phoneverification.databinding.ActivityWishListBinding;

public class WishListActivity extends AppCompatActivity {

    ActivityWishListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWishListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());
    }
}