package com.samples.phoneverification.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samples.phoneverification.R;

import java.util.List;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.RecentSearchHolder> {

    private final Context context;
    private List<String> recentSearch;
    public RecentSearchAdapter(Context context, List<String> recentSearch) {
        this.context = context;
        this.recentSearch = recentSearch;
    }

    @NonNull
    @Override
    public RecentSearchAdapter.RecentSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecentSearchHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_recent_keywords, parent, false
                )
        );
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull RecentSearchAdapter.RecentSearchHolder holder, int position) {
        String searchQuery = recentSearch.get(position);
        holder.recentSearch.setText(searchQuery);

        //TODO: Remove selected recent search query.
        holder.deleteSearch.setOnClickListener(v -> {
            deleteSearchedQuery(position);
            notifyDataSetChanged();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteSearchedQuery(int position) {
        //TODO: delete that search query WRT position.
            recentSearch.remove(position);
            notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recentSearch.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<String> recentSearchWholeRecord) {
        this.recentSearch = recentSearchWholeRecord;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public static class RecentSearchHolder extends RecyclerView.ViewHolder {
        TextView recentSearch;
        ImageView deleteSearch;
        public RecentSearchHolder(@NonNull View itemView) {
            super(itemView);
            recentSearch = itemView.findViewById(R.id.recent_search_item_text);
            deleteSearch = itemView.findViewById(R.id.clearSearchItem);
        }
    }
}
