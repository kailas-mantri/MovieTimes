package com.samples.phoneverification.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.samples.phoneverification.adapters.WishListAdapter;
import com.samples.phoneverification.databinding.ActivityWishListBinding;
import com.samples.phoneverification.dbmodel.WishListDBHelper;
import com.samples.phoneverification.dbmodel.WishListItem;

import java.util.ArrayList;

public class WishListActivity extends AppCompatActivity {

    ActivityWishListBinding binding;
    WishListAdapter adapter;
    private ArrayList<WishListItem> wishList = new ArrayList<>();
    private final WishListDBHelper dbHelper = new WishListDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWishListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(v -> onBackPressed());

        binding.wishlistRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WishListAdapter(getApplicationContext(), wishList, (item, position, action) ->
            Log.d("TAG", "onCreate: "+wishList)
        );
        binding.wishlistRecycler.setAdapter(adapter);

        wishList = dbHelper.getAllWishListItems();
        adapter.updateData(wishList);
    }
}