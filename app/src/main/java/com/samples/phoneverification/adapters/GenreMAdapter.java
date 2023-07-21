package com.samples.phoneverification.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samples.phoneverification.R;
import com.samples.phoneverification.model.GenreResults;
import com.samples.phoneverification.model.MovieResults;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;

import java.util.ArrayList;

public class GenreMAdapter extends RecyclerView.Adapter<GenreMAdapter.GenreViewHolder> {

    private final Context context;
    private ArrayList<GenreResults> genreResults;
    private final OnRecyclerItemClickListener<MovieResults> anInterface;

    public GenreMAdapter(Context context, ArrayList<GenreResults> genreResults, OnRecyclerItemClickListener<MovieResults> anInterface) {
        this.context = context;
        this.genreResults = genreResults;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_genres_recycler_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        // TODO 1: set Title of Movie/Series
        holder.genreTitle.setText(genreResults.get(position).getName());

        // TODO 2: set LayoutManager for Inner Recycler View.
        holder.itemWrtGenres.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        // TODO 3: set Adapter for items With Respect To Genres in ImageRecycler
        holder.itemWrtGenres.setAdapter(new ImageMAdapter(context, genreResults.get(position).getMovieResults(), anInterface, position));
    }

    @Override
    public int getItemCount() {
        return genreResults.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<GenreResults> genreResults) {
        this.genreResults = genreResults;
        notifyDataSetChanged();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {
        final TextView genreTitle;
        final RecyclerView itemWrtGenres;
        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genreTitle = itemView.findViewById(R.id.genreText);
            itemWrtGenres = itemView.findViewById(R.id.genreImageRecyclerView);
        }
    }

}
