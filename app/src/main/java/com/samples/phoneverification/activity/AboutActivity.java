package com.samples.phoneverification.activity;

import android.os.Bundle;

import com.samples.phoneverification.R;
import com.samples.phoneverification.databinding.ActivityAboutBinding;

public class AboutActivity extends BaseActivity {

    ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(view -> onBackPressed());

        String[] descriptionArray = getResources().getStringArray(R.array.app_description);
        StringBuilder builder = new StringBuilder();
        for (String description : descriptionArray) {
            builder.append(description).append("\n");
        }
        binding.appDescription.setText(builder.toString().trim());
    }
}