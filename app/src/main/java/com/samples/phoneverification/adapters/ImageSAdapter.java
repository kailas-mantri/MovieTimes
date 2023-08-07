package com.samples.phoneverification.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.BuildConfig;
import com.samples.phoneverification.R;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;
import com.samples.phoneverification.model.SeriesResults;

import java.util.ArrayList;

public class ImageSAdapter extends RecyclerView.Adapter<ImageSAdapter.ImageViewHolder> {

    int seriesId;
    private final Context context;
    private final ArrayList<SeriesResults> seriesResults;
    private final OnRecyclerItemClickListener<SeriesResults> anInterface;
    final int genrePosition;
    public ImageSAdapter(Context context, ArrayList<SeriesResults> seriesResults, OnRecyclerItemClickListener<SeriesResults> anInterface, int position) {
        this.context = context;
        this.seriesResults = seriesResults;
        this.anInterface = anInterface;
        this.genrePosition = position;
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
        String posterPath = seriesResults.get(position).getPosterPath();

        if (posterPath == null) {
            holder.imageView.setImageResource(R.drawable.no_poster);
        } else {
            // Glide - imageURL string
            Glide.with(holder.imageView).load(BuildConfig.IMAGE_BASE_URL + seriesResults.get(position).getPosterPath())
                    .into(holder.imageView);
        }

        // TODO: 1. Need to set next functionality of OnClick Item View.
        holder.imageView.setOnClickListener(view -> {
            seriesId = seriesResults.get(position).getSeriesId();
            if (anInterface!=null) {
                anInterface.onItemClicked(seriesResults.get(position), position, 0);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return seriesResults.size();
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
