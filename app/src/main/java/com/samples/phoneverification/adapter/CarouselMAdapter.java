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
import com.samples.phoneverification.apimodel.RecyclerItemInterface;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.apimodel.MovieResults;

import java.util.ArrayList;

public class CarouselMAdapter extends RecyclerView.Adapter<CarouselMAdapter.CarouselHolder> {
    private final Context context;
    private ArrayList<MovieResults> movieResults;
    private final RecyclerItemInterface anInterface;

    public CarouselMAdapter(Context context, ArrayList<MovieResults> movieResults, RecyclerItemInterface anInterface) {
        this.context = context;
        this.movieResults = movieResults;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public CarouselHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarouselHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.carousel_imageslider,
                        parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselHolder holder, int position) {

        String backdropPath = movieResults.get(position).getBackdropPath();

        if (backdropPath == null) {
            holder.imageView.setImageResource(R.drawable.no_poster);
        } else {
            Glide.with(holder.imageView).load(URLs.IMAGE_BASE_URL + movieResults.get(position).getBackdropPath()).into(holder.imageView);
        }
        holder.imageView.setOnClickListener(v -> anInterface.onItemClick(position));

        if (position == movieResults.size() - 2 ) {
            holder.itemView.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<MovieResults> upComingMovieArrayList) {
        this.movieResults = upComingMovieArrayList;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public static class CarouselHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;

        public CarouselHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.upcomingImages);
        }
    }

    private final Runnable runnable = new Runnable() {

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            movieResults.addAll(movieResults);
            notifyDataSetChanged();
        }
    };

}
