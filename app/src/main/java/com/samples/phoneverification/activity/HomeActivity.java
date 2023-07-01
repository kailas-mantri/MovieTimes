package com.samples.phoneverification.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.samples.phoneverification.R;
import com.samples.phoneverification.databinding.ActivityHomeBinding;
import com.samples.phoneverification.fragment.HomeFragment;
import com.samples.phoneverification.fragment.MoviesFragment;
import com.samples.phoneverification.fragment.SearchFragment;
import com.samples.phoneverification.fragment.SeriesFragment;

public class HomeActivity extends AppCompatActivity {

    Fragment fragment;
    ActivityHomeBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_Home) {
                fragment = new HomeFragment();
                loadFragment(fragment, false);
            } else if (id == R.id.menu_Movies) {
                fragment = new MoviesFragment();
                loadFragment(fragment, false);
            } else if (id == R.id.menu_Series) {
                fragment = new SeriesFragment();
                loadFragment(fragment, false);
            } else if (id == R.id.menu_Search) {
                fragment = new SearchFragment();
                loadFragment(fragment, false);
            } else {
                Toast.makeText(this, "Please check your INTERNET connection", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        binding.bottomNavView.setSelectedItemId(R.id.menu_Home);
    }

    private void loadFragment(Fragment fragment, boolean flag) {
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (!flag) {
            transaction.add(binding.bottomNavFrame.getId(), fragment);
        }
        transaction.replace(binding.bottomNavFrame.getId(), fragment);
        transaction.commit();
    }
}