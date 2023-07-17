package com.samples.phoneverification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.R;
import com.samples.phoneverification.apimodel.RecyclerItemInterface;
import com.samples.phoneverification.apimodel.SeriesResults;
import com.samples.phoneverification.apimodel.URLs;

import java.util.ArrayList;

public class ImageSAdapter extends RecyclerView.Adapter<ImageSAdapter.ImageViewHolder> {

    private int seriesId;
    private final Context context;
    private final ArrayList<SeriesResults> seriesResults;
    private final RecyclerItemInterface anInterface;

    public ImageSAdapter(Context context, ArrayList<SeriesResults> seriesResults, RecyclerItemInterface anInterface) {
        this.context = context;
        this.seriesResults = seriesResults;
        this.anInterface = anInterface;
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
            // Glide library to display ImageBaseURL link before image string
            Glide.with(holder.imageView).load(URLs.IMAGE_BASE_URL + seriesResults.get(position).getPosterPath())
                    .into(holder.imageView);
        }

        // TODO: 1. Need to set next functionality of OnClick Item View.
        holder.imageView.setOnClickListener(view -> {
            seriesId = seriesResults.get(position).getSeriesId();
            if (anInterface!=null) {
                anInterface.onItemClick(seriesId);
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
