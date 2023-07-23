package com.samples.phoneverification.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samples.phoneverification.R;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;
import com.samples.phoneverification.model.Seasons;

import java.util.ArrayList;

public class SeasonTextAdapter extends RecyclerView.Adapter<SeasonTextAdapter.ViewHolder> {

    private final ArrayList<Seasons> seasonsList;
    private final OnRecyclerItemClickListener<Seasons> anInterface;
    private int selectedItem;

    public SeasonTextAdapter(Context context, ArrayList<Seasons> seasonsList, OnRecyclerItemClickListener<Seasons> anInterface) {
        this.seasonsList = seasonsList;
        this.anInterface = anInterface;
        selectedItem = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_season_title, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        int seasonNumber = seasonsList.get(position).getSeasonNumber();
        holder.seasonsText.setText("Season "+seasonsList.get(position).getSeasonNumber());
        if (selectedItem == position) {
            holder.seasonsText.setBackgroundResource(R.drawable.bg_line);
        } else {
            holder.seasonsText.setBackgroundResource(R.drawable.bg_without_line);
        }

        holder.seasonsText.setOnClickListener(view -> {
            int previousSelectedItem = selectedItem;
            selectedItem = position;
            notifyItemChanged(previousSelectedItem);
            notifyItemChanged(selectedItem);

            if (anInterface!= null) {
                anInterface.onItemClicked(seasonsList.get(position), position, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seasonsList.size();
    }

    public void setDefaultItem() {
        int defaultPosition = 0;
        if (defaultPosition < seasonsList.size()) {
            int previousSelectedItem = selectedItem;
            selectedItem = defaultPosition;
            notifyDataSetChanged();
            if (anInterface != null) {
                anInterface.onItemClicked(seasonsList.get(selectedItem), selectedItem, 0);
            }
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView seasonsText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seasonsText = itemView.findViewById(R.id.season_no);
        }
    }
}
