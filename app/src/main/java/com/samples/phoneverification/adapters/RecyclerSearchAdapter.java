package com.samples.phoneverification.adapters;

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
import com.samples.phoneverification.model.SearchResults;

import java.util.ArrayList;

public class RecyclerSearchAdapter extends RecyclerView.Adapter<RecyclerSearchAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<SearchResults> apiResults;
    private final OnRecyclerItemClickListener<SearchResults> anInterface;

    public RecyclerSearchAdapter(Context context, ArrayList<SearchResults> apiResults, OnRecyclerItemClickListener<SearchResults> anInterface) {
        this.context = context;
        this.apiResults = apiResults;
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
        String posterPath = apiResults.get(position).getPosterPath();

        if (posterPath == null) {
            holder.imageView.setImageResource(R.drawable.no_poster);
        } else {
            Glide.with(holder.imageView).load(BuildConfig.IMAGE_BASE_URL+apiResults.get(position).getPosterPath())
                    .into(holder.imageView);
        }

        holder.imageView.setOnClickListener(v ->
           anInterface.onItemClicked(apiResults.get(position), position, 0)
        );
    }

    @Override
    public int getItemCount() {
        return apiResults.size();
    }

    public void updateData(ArrayList<SearchResults> searchResults) {
        this.apiResults = searchResults;
    }

    public Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieOrSeriesImages);
        }
    }
}
