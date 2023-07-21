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

    private final Context context;
    private ArrayList<Seasons> seasons;
    private OnRecyclerItemClickListener<Seasons> anInterface;
    private int defaultSelectedPosition = -1;

    public SeasonTextAdapter(Context context, ArrayList<Seasons> seasons, OnRecyclerItemClickListener<Seasons> anInterface) {
        this.context = context;
        this.seasons = seasons;
        this.anInterface = anInterface;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDefaultSelectedPosition(int position) {
        for (int i = 0; i < seasons.size(); i++) {
            if (seasons.get(i).getSeasonNumber() == position) {
                defaultSelectedPosition = i;
                notifyDataSetChanged();
                break;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_season_title, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.seasonsText.append( " " + Integer.toString(seasons.get(position).getSeasonNumber()));
        holder.seasonsText.setOnClickListener(view -> {
            if (anInterface!= null) {
                anInterface.onItemClicked(seasons.get(position), position, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView seasonsText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            seasonsText = itemView.findViewById(R.id.season_no);
        }
    }
}
