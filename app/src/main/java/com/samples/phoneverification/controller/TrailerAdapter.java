package com.samples.phoneverification.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.R;
import com.samples.phoneverification.model.MediaTypeArray;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;
import com.samples.phoneverification.apimodel.URLs;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    Context context;
    ArrayList<MediaTypeArray> mediaList;
    OnRecyclerItemClickListener<MediaTypeArray> anInterface;

    public TrailerAdapter(Context context, ArrayList<MediaTypeArray> mediaList, OnRecyclerItemClickListener<MediaTypeArray> anInterface) {
        this.context = context;
        this.mediaList = mediaList;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.trailer_vedios_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO: Set the media view to get the trailer poster or videoPlayer
        Glide.with(holder.mediaPoster).load(URLs.YOUTUBE_THUMBNAILS + mediaList.get(position).getMedia_key() + "/0.jpg").into(holder.mediaPoster);

        holder.mediaPoster.setOnClickListener(view -> {
            // TODO: Implicit Intent.
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URLs.YOUTUBE+mediaList.get(position).getMedia_key()));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<MediaTypeArray> trailerList) {
        this.mediaList = trailerList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView play, mediaPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mediaPoster = itemView.findViewById(R.id.mediaPoster);
            play = itemView.findViewById(R.id.play_icon);
        }
    }
}