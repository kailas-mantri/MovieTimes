package com.samples.phoneverification.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.samples.phoneverification.adapters.WishListAdapter;
import com.samples.phoneverification.databinding.ActivityWishListBinding;
import com.samples.phoneverification.dbmodel.WishListDBHelper;
import com.samples.phoneverification.dbmodel.WishListItem;
import com.samples.phoneverification.utilities.BaseActivity;

import java.util.ArrayList;

public class WishListActivity extends BaseActivity {

    ActivityWishListBinding binding;
    private Intent intent;
    private WishListAdapter adapter;
    private ArrayList<WishListItem> wishList = new ArrayList<>();
    private final WishListDBHelper dbHelper = new WishListDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWishListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(v -> onBackPressed());

        binding.wishlistRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WishListAdapter(getApplicationContext(), wishList, (item, position, action) -> {
            if (action == 0) {
                if (item.isMovie() == 1) {
                    intent = new Intent(this, MovieDetailsActivity.class);
                    intent.putExtra("movie_id", item.getItem_id());
                } else {
                    intent = new Intent(this, SeriesDetailsActivity.class);
                    intent.putExtra("series_id", item.getItem_id());
                }
                startActivity(intent);
            } else if (action == 1) {
                Toast.makeText(this, item.getItem_title(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.wishlistRecycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wishList = dbHelper.getAllWishListItems();
        adapter.updateData(wishList);
    }
}