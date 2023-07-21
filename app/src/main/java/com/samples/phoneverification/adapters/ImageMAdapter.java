package com.samples.phoneverification.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.R;
import com.samples.phoneverification.model.MovieResults;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;
import com.samples.phoneverification.apimodel.URLs;

import java.util.ArrayList;

public class ImageMAdapter extends RecyclerView.Adapter<ImageMAdapter.ImageViewHolder> {

    private final Context context;
    private final ArrayList<MovieResults> movieResults;
    private final OnRecyclerItemClickListener<MovieResults> anInterface;
    int genrePosition;
    public ImageMAdapter(Context context, ArrayList<MovieResults> movieResults, OnRecyclerItemClickListener<MovieResults> anInterface, int genrePosition) {
        this.context = context;
        this.movieResults = movieResults;
        this.anInterface = anInterface;
        this.genrePosition = genrePosition;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.card_images_recycler_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String posterPath = movieResults.get(position).getPosterPath();

        if (posterPath == null) {
            holder.imageView.setImageResource(R.drawable.no_poster);
        } else {
            // Glide library to display ImageBaseURL link before image string
            Glide.with(holder.imageView).load(URLs.IMAGE_BASE_URL + movieResults.get(position).getPosterPath())
                    .into(holder.imageView);
        }

        // TODO: 1. Need to set next functionality of OnClick Item View.
        holder.imageView.setOnClickListener(view -> {
            if (anInterface != null) {
                anInterface.onItemClicked(movieResults.get(position), position, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }


    public Context getContext() {
        return context;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieOrSeriesImages);
        }
    }
}
