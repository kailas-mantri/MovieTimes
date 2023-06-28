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
import com.samples.phoneverification.apimodel.MovieResults;
import com.samples.phoneverification.apimodel.RecyclerItemViewInterface;
import com.samples.phoneverification.apimodel.URLs;

import java.util.ArrayList;

public class TrendingMovieAdapter extends RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder> {

    Context context;
    RecyclerItemViewInterface anInterface;
    ArrayList<MovieResults> trendingMovies;

    public TrendingMovieAdapter(Context context, ArrayList<MovieResults> trendingMovies, RecyclerItemViewInterface anInterface) {
        this.context = context;
        this.trendingMovies = trendingMovies;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycler_image_card_layout,
                        parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String posterPath = trendingMovies.get(position).getPosterPath();

        if (posterPath == null) {
            holder.imageArray.setImageResource(R.drawable.no_poster);
        } else {
            // Using Glide library, display image. & Add a ImageBaseURL link before image string
            Glide.with(holder.itemView)
                    .load(URLs.IMAGE_BASE_URL + trendingMovies.get(position).getPosterPath()).into(holder.imageArray);
        }

        holder.imageArray.setOnClickListener(view -> anInterface.onItemClick(holder.getBindingAdapterPosition()) );
    }

    @Override
    public int getItemCount() {
        return trendingMovies.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovieResults(ArrayList<MovieResults> trendingMovies) {
        this.trendingMovies = trendingMovies;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageArray;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageArray = itemView.findViewById(R.id.movieOrSeriesImages);
        }
    }
}
