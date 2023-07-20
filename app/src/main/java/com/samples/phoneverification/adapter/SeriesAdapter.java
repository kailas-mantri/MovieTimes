package com.samples.phoneverification.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.R;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;
import com.samples.phoneverification.apimodel.SeriesResults;
import com.samples.phoneverification.apimodel.URLs;

import java.util.ArrayList;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    final Context context;
    ArrayList<SeriesResults> seriesResults;
    final OnRecyclerItemClickListener<SeriesResults> anInterface;

    public SeriesAdapter(Context context, ArrayList<SeriesResults> seriesResults, OnRecyclerItemClickListener<SeriesResults> anInterface) {
        this.context = context;
        this.seriesResults = seriesResults;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.card_images_recycler_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String posterPath = seriesResults.get(position).getPosterPath();
        if (posterPath == null) {
            holder.imageView.setImageResource(R.drawable.no_poster);
        } else {
            Glide.with(holder.imageView).load(URLs.IMAGE_BASE_URL+seriesResults.get(position).getPosterPath())
                    .into(holder.imageView);
        }

        // TODO: 1. Need to set next functionality of OnClick Item View.
        holder.imageView.setOnClickListener(view ->
            anInterface.onItemClicked(seriesResults.get(position), position, 0)
        );
    }

    @Override
    public int getItemCount() {
        return seriesResults.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<SeriesResults> seriesResults) {
        this.seriesResults = seriesResults;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieOrSeriesImages);
        }
    }
}
