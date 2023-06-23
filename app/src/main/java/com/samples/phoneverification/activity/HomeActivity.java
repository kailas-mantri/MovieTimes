package com.samples.phoneverification.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
                loadFragment(fragment);
            } else if (id == R.id.menu_Movies) {
                fragment = new MoviesFragment();
                loadFragment(fragment);
            } else if (id == R.id.menu_Series) {
                fragment = new SeriesFragment();
                loadFragment(fragment);
            } else if (id == R.id.menu_Search) {
                fragment = new SearchFragment();
                loadFragment(fragment);
            } else {
                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
            }

            return true;
        });

        binding.bottomNavView.setSelectedItemId(R.id.menu_Home);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(binding.bottomNavFrame.getId(), fragment);
        transaction.commit();
    }
}