package com.samples.phoneverification.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.BuildConfig;
import com.samples.phoneverification.R;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;
import com.samples.phoneverification.model.Episodes;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Episodes> episodeList;
    final OnRecyclerItemClickListener<Episodes> anInterface;

    public EpisodeAdapter(Context context, ArrayList<Episodes> episodeList, OnRecyclerItemClickListener<Episodes> anInterface) {
        this.context = context;
        this.episodeList = episodeList;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_episode_card, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String imageUrl = episodeList.get(position).getEpisode_still_path();
        if (imageUrl == null) {
            holder.episodeImage.setBackgroundResource(R.drawable.no_poster);
        } else {
            Glide.with(holder.episodeImage).load(BuildConfig.IMAGE_BASE_URL + episodeList.get(position).episode_still_path)
                    .into(holder.episodeImage);
        }
        holder.episodeName.setText(episodeList.get(position).getEpisode_name());
        holder.episodeOverview.setText(episodeList.get(position).getEpisode_overview());
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView episodeImage;
        TextView episodeName, episodeOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            episodeName = itemView.findViewById(R.id.episode_name);
            episodeImage = itemView.findViewById(R.id.mediaPoster);
            episodeOverview = itemView.findViewById(R.id.episode_overview);
        }
    }
}
