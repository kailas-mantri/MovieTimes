package com.samples.phoneverification.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.samples.phoneverification.R;
import com.samples.phoneverification.databinding.ActivityHomeBinding;
import com.samples.phoneverification.fragment.HomeFragment;
import com.samples.phoneverification.fragment.MoviesFragment;
import com.samples.phoneverification.fragment.SearchFragment;
import com.samples.phoneverification.fragment.SeriesFragment;

public class HomeActivity extends BaseActivity {

    Fragment fragment;
    private ActivityHomeBinding binding;
    private FirebaseAuth mAuth;
    private SharedPreferences preferences;
    private static final String SHARED_PREFERENCE_NAME = "pref";
    private final Fragment homeFragment = new HomeFragment();
    private final Fragment moviesFragment = new MoviesFragment();
    private final Fragment seriesFragment = new SeriesFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFragment(new HomeFragment());

        mAuth = FirebaseAuth.getInstance();

        binding.appBar.navIcon.setOnClickListener(v -> {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        binding.navDrawerBar.setNavigationItemSelectedListener(item -> {
            switch(item.getItemId()) {
                // According to id change the fragments
                case R.id.menu_Home:
                    fragment = homeFragment;
                    break;
                case R.id.menu_store:
                    startActivity(new Intent(HomeActivity.this, StoreActivity.class));
                    break;
                case R.id.wishList:
                    startActivity(new Intent(HomeActivity.this, WishListActivity.class));
                    break;
                case R.id.aboutMenu:
                    startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                    break;
                case R.id.feedback:
                    startActivity(new Intent(HomeActivity.this, FeedbackActivity.class));
                    break;
                case R.id.shareApp:
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND).setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "https://github.com/kailas-mantri/MovieTimes.git");
                    startActivity(new Intent(Intent.createChooser(shareIntent, "Share using")));
                    break;
                case R.id.signOut:
                    mAuth.signOut();
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    Toast.makeText(this, "logout successful", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                default:
                    binding.appBar.toolbar.setTitle(item.getTitle());
            }

            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return loadFragment(fragment);
        });

        binding.appBar.contentMain.bottomNavView.setOnItemSelectedListener(item -> {
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
                    fragment = new SearchFragment();
                    break;
                default:
                    Toast.makeText(this, "Please check your INTERNET connection", Toast.LENGTH_SHORT).show();
            }
            return loadFragment(fragment);
        });

        if (savedInstanceState!= null) {
            int selectedNavItem = savedInstanceState.getInt("selectedNavItem");
            binding.appBar.contentMain.bottomNavView.setSelectedItemId(selectedNavItem);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedNavItem", binding.appBar.contentMain.bottomNavView.getSelectedItemId());
    }

    private boolean loadFragment(Fragment fragment) {

        /* FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(binding.bottomNavFrame.getId(), fragment);
        transaction.add(binding.bottomNavFrame.getId(), fragment);
        transaction.commit();*/

        if (fragment != null) {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(binding.appBar.contentMain.bottomNavFrame.getId(), fragment)
                    .commit();
            return true;
        }
        return false;
    }
}