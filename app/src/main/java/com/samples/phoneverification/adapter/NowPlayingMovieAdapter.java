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
import com.samples.phoneverification.apimodel.MovieResults;
import com.samples.phoneverification.apimodel.RecyclerItemViewInterface;
import com.samples.phoneverification.apimodel.URLs;

import java.util.ArrayList;

public class NowPlayingMovieAdapter extends RecyclerView.Adapter<NowPlayingMovieAdapter.ViewHolder> {
    Context context;
    RecyclerItemViewInterface anInterface;
    ArrayList<MovieResults> movieResults;

    public NowPlayingMovieAdapter(Context context, ArrayList<MovieResults> movieResults, RecyclerItemViewInterface anInterface) {
        this.context = context;
        this.movieResults = movieResults;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public NowPlayingMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycler_image_card_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingMovieAdapter.ViewHolder holder, int position) {
        String posterPath = movieResults.get(position).getPosterPath();

        if (posterPath == null) {
            holder.imageArray.setImageResource(R.drawable.no_poster);
        } else {
            // Using Glide setting images with ImageBasedURL
            Glide.with(holder.itemView).load(URLs.IMAGE_BASE_URL + movieResults.get(position).getPosterPath())
                    .into(holder.imageArray);
        }

        //TODO: 1. Need to set next functionality of OnClick Item View.
        holder.imageArray.setOnClickListener( view -> anInterface.onItemClick(holder.getBindingAdapterPosition()) );
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    public void setMovieResults(ArrayList<MovieResults> NowPlayingList) {
        this.movieResults = NowPlayingList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageArray;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageArray = itemView.findViewById(R.id.movieOrSeriesImages);
        }
    }
}