package com.samples.phoneverification.adapters;

import android.annotation.SuppressLint;
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

public class CarouselSAdapter extends RecyclerView.Adapter<CarouselSAdapter.SeriesViewHolder> {

    private final Context context;
    private ArrayList<SeriesResults> seriesResults;
    private final OnRecyclerItemClickListener<SeriesResults> anInterface;

    public CarouselSAdapter(Context context, ArrayList<SeriesResults> seriesResults, OnRecyclerItemClickListener<SeriesResults> anInterface) {
        this.context = context;
        this.seriesResults = seriesResults;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SeriesViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_carousel_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        String backdropPoster = seriesResults.get(position).getBackdropPath();

        if (backdropPoster == null) {
            holder.imageView.setImageResource(R.drawable.no_poster);
        } else {
            Glide.with(holder.imageView).load(BuildConfig.IMAGE_BASE_URL+seriesResults.get(position).getBackdropPath())
                    .into(holder.imageView);
        }

        // TODO:1. OnItemClick check
        holder.imageView.setOnClickListener(v -> anInterface.onItemClicked(seriesResults.get(position), position, 0));

        if (position == seriesResults.size() - 2) {
            holder.imageView.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return seriesResults.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<SeriesResults> seriesResults){
        this.seriesResults = seriesResults;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public static class SeriesViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;

        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.upcomingImages);
        }
    }

    private final Runnable runnable = new Runnable() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            seriesResults.addAll(seriesResults);
            notifyDataSetChanged();
        }
    };
}
