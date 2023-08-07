package com.samples.phoneverification.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.BuildConfig;
import com.samples.phoneverification.R;
import com.samples.phoneverification.activity.MovieDetailsActivity;
import com.samples.phoneverification.activity.SeriesDetailsActivity;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;
import com.samples.phoneverification.dbmodel.WishListItem;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    final Context context;
    private ArrayList<WishListItem> wishList;
    final OnRecyclerItemClickListener<WishListItem> anInterface;

    public WishListAdapter(Context context, ArrayList<WishListItem> wishList, OnRecyclerItemClickListener<WishListItem> anInterface) {
        this.context = context;
        this.wishList = wishList;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_wishlist_card, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String backdrop = wishList.get(position).getItem_backdropPath();
        if (backdrop == null) {
            holder.backdropPath.setBackgroundResource(R.drawable.no_poster);
        } else {
            Glide.with(holder.backdropPath).load(BuildConfig.IMAGE_BASE_URL + wishList.get(position).getItem_backdropPath())
                    .into(holder.backdropPath);
        }

        holder.title.setText(wishList.get(position).getItem_title());
        holder.releaseDate.setText(wishList.get(position).getItem_release_date());

        holder.card.setOnClickListener(view ->
            anInterface.onItemClicked(wishList.get(position), position, 0)
        );
        holder.card.setOnLongClickListener(view -> {
            anInterface.onItemClicked(wishList.get(position), position, 1);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        if (wishList != null) {
            return wishList.size();
        } else {
            return 0;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<WishListItem> wishList) {
        this.wishList = wishList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView backdropPath;
        final TextView title;
        final TextView releaseDate;
        final RelativeLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            backdropPath = itemView.findViewById(R.id.backdropItem);
            title = itemView.findViewById(R.id.titleItem);
            releaseDate = itemView.findViewById(R.id.releaseItemDate);
            card = itemView.findViewById(R.id.card);
        }
    }
}
