package com.samples.phoneverification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.samples.phoneverification.R;
import com.samples.phoneverification.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {

    ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(view -> onBackPressed());

    }
}