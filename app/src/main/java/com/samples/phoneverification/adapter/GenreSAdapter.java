package com.samples.phoneverification.adapter;

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
import com.samples.phoneverification.apimodel.GenreResults;
import com.samples.phoneverification.apimodel.RecyclerItemInterface;

import java.util.ArrayList;

public class GenreSAdapter extends RecyclerView.Adapter<GenreSAdapter.GenreViewHolder> {

    private final Context context;
    private ArrayList<GenreResults> genreResults;
    private final RecyclerItemInterface anInterface;

    public GenreSAdapter(Context context, ArrayList<GenreResults> genreResults, RecyclerItemInterface anInterface) {
        this.context = context;
        this.genreResults = genreResults;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycler_card_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        // TODO 1: set Title of Movie/Series
        holder.genreTitle.setText(genreResults.get(position).getName());

        // TODO 2: set LayoutManager for Inner Recycler View.
        holder.itemWrtGenres.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.itemWrtGenres.setOnClickListener(view -> {
            int seriesId = genreResults.get(holder.getBindingAdapterPosition()).getSeriesList().get(position).getSeriesId();
            if (anInterface!=null) {
                anInterface.onItemClick(seriesId);
            }
        });

        // TODO 3: set Adapter for items With Respect To Genres in ImageRecycler
        holder.itemWrtGenres.setAdapter(new ImageSAdapter(context, genreResults.get(position).getSeriesList(), anInterface));
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
