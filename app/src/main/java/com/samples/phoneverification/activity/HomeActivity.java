package com.samples.phoneverification.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.samples.phoneverification.R;
import com.samples.phoneverification.databinding.ActivityHomeBinding;
import com.samples.phoneverification.fragment.HomeFragment;
import com.samples.phoneverification.fragment.MoviesFragment;
import com.samples.phoneverification.fragment.SearchFragment;
import com.samples.phoneverification.fragment.SeriesFragment;

public class HomeActivity extends BaseActivity {

    Fragment fragment;
    private final Fragment homeFragment = new HomeFragment();
    private final Fragment moviesFragment = new MoviesFragment();
    private final Fragment seriesFragment = new SeriesFragment();
    private final Fragment searchFragment = new SearchFragment();
    ActivityHomeBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFragment(new HomeFragment());

        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_Home:
                    fragment = homeFragment;
                    break;
                case R.id.menu_Movies:
                    fragment = moviesFragment;
                    break;
                case R.id.menu_Series:
                    fragment = seriesFragment;
                    break;
                case R.id.menu_Search:
                    fragment = searchFragment;
                    break;
                default:
                    Toast.makeText(this, "Please check your INTERNET connection", Toast.LENGTH_SHORT).show();
            }

            return loadFragment(fragment);
        });

        binding.bottomNavView.setSelectedItemId(R.id.menu_Home);
    }

    private boolean loadFragment(Fragment fragment) {

/*        FragmentManager manager = this.getSupportFragmentManager();
*         FragmentTransaction transaction = manager.beginTransaction();
*         transaction.replace(binding.bottomNavFrame.getId(), fragment);
*         transaction.add(binding.bottomNavFrame.getId(), fragment);
*         transaction.commit();
 */

        if (fragment != null) {
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.bottomNavFrame.getId(), fragment)
                    .commit();
            return true;
        }
        return false;
    }
}