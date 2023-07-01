package com.samples.phoneverification.adapter;

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

import java.util.LinkedList;
import java.util.List;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.RecentSearchHolder> {

    private Context context;
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
                        R.layout.item_recent_search, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecentSearchAdapter.RecentSearchHolder holder, int position) {
        String searchQuery = recentSearch.get(position);
        holder.recentSearch.setText(searchQuery);

        //TODO: Remove selected recent search query.
        holder.clearSearch.setOnClickListener(v -> deleteSearchQuery(position));
    }

    private void deleteSearchQuery(int position) {
        //TODO: delete that search query WRT position.
            recentSearch.remove(position);
            notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return recentSearch.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<String> recentSearchWholeRecord) {
        this.recentSearch = recentSearchWholeRecord;
        notifyDataSetChanged();
    }

    public static class RecentSearchHolder extends RecyclerView.ViewHolder {
        TextView recentSearch;
        ImageView clearSearch;
        public RecentSearchHolder(@NonNull View itemView) {
            super(itemView);

            recentSearch = itemView.findViewById(R.id.recent_search_item_text);
            clearSearch = itemView.findViewById(R.id.clearSearchItem);
        }
    }
}
