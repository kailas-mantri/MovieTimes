package com.samples.phoneverification.adapters;

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
import com.samples.phoneverification.BuildConfig;
import com.samples.phoneverification.R;
import com.samples.phoneverification.model.MediaList;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<MediaList> mediaList;
    final OnRecyclerItemClickListener<MediaList> anInterface;

    public TrailerAdapter(Context context, ArrayList<MediaList> mediaList, OnRecyclerItemClickListener<MediaList> anInterface) {
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
        Glide.with(holder.mediaPoster).load(BuildConfig.YOUTUBE_THUMBNAILS + mediaList.get(position).getMedia_key() + "/0.jpg").into(holder.mediaPoster);

        holder.mediaPoster.setOnClickListener(view -> {
            // TODO: Implicit Intent.
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.YOUTUBE+mediaList.get(position).getMedia_key()));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<MediaList> trailerList) {
        this.mediaList = trailerList;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView play;
        final ImageView mediaPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mediaPoster = itemView.findViewById(R.id.mediaPoster);
            play = itemView.findViewById(R.id.play_icon);
        }
    }
}
