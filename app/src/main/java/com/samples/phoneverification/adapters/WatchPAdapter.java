package com.samples.phoneverification.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.samples.phoneverification.model.Providers;
import com.samples.phoneverification.model.ProvidersRegionList;
import com.samples.phoneverification.model.WatchProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class WatchPAdapter extends RecyclerView.Adapter<WatchPAdapter.ViewHolder> {

    final Context context;
    private Map<String, ProvidersRegionList> regionLists;
    private final OnRecyclerItemClickListener<WatchProvider> anInterface;

    public WatchPAdapter(Context context, Map<String, ProvidersRegionList> regionLists, OnRecyclerItemClickListener<WatchProvider> anInterface) {
        this.context = context;
        this.regionLists = regionLists;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_watch_provider_template, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position >= 0 && position <= regionLists.size()) {
            String regionCode = (String) regionLists.keySet().toArray()[position];
            System.out.println(regionCode);
            ProvidersRegionList regionArray = regionLists.get(regionCode);
            if (regionArray != null) {
                ArrayList<Providers> buy = regionArray.getBuyList();
                if (buy != null && position < buy.size()) {
                    Glide.with(holder.provideLogo).load(BuildConfig.IMAGE_BASE_URL + buy.get(position).getProvidersLogoPath()).into(holder.provideLogo);
                } else {
                    holder.provideLogo.setVisibility(View.GONE);
                }
            }

            if (regionArray != null) {
                WatchProvider watchProvider = new WatchProvider();
                watchProvider.setRegionList(regionLists);
                anInterface.onItemClicked(watchProvider, position, 0);
            }

            holder.provideLogo.setOnClickListener(v -> {
                String link = Objects.requireNonNull(regionLists.get(position)).getRegion_link();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                v.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return regionLists.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(Map<String, ProvidersRegionList> regionLists) {
        this.regionLists = regionLists;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView provideLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            provideLogo = itemView.findViewById(R.id.watchProviderIcons);
        }
    }
}
