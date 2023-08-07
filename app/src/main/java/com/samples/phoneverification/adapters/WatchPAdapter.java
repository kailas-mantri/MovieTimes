package com.samples.phoneverification.adapters;

import android.annotation.SuppressLint;
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
import com.samples.phoneverification.model.Providers;

import java.util.ArrayList;

public class WatchPAdapter extends RecyclerView.Adapter<WatchPAdapter.ViewHolder> {

    final Context context;
    private ArrayList<Providers> buy;
    final OnRecyclerItemClickListener<Providers> anInterface;

    public WatchPAdapter(Context context, ArrayList<Providers> buy, OnRecyclerItemClickListener<Providers> anInterface) {
        this.context = context;
        this.buy = buy;
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
        if (position >= 0 && position <= buy.size()) {
            if (position < buy.size()) {
                Glide.with(holder.provideLogo).load(BuildConfig.IMAGE_BASE_URL + buy.get(position).getProvidersLogoPath()).into(holder.provideLogo);
            } else {
                holder.provideLogo.setVisibility(View.GONE);
            }
        }
        /*holder.provideLogo.setOnClickListener( v -> {
            String providerName = buy.get(position).getProviderName();
            if (isAppInstalled(providerName)) {
                Intent appIntent = context.getPackageManager().getLaunchIntentForPackage(providerName);
                if (appIntent != null) {
                    v.getContext().startActivity(appIntent);
                } else {
                    Log.d("App not found", "onBindViewHolder: Unable to open App"+ null);
                }
            } else {
                Uri playStoreUrl = Uri.parse("https://play.google.com/store/search?q=" + providerName);
                Intent playStore = new Intent(Intent.ACTION_VIEW, playStoreUrl);
                v.getContext().startActivity(playStore);
            }
        });
    }

    private boolean isAppInstalled(String providerName) {
        PackageManager manager = context.getPackageManager();
        @SuppressLint("QueryPermissionsNeeded")
        List<ApplicationInfo> installedApp = manager.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo info: installedApp) {
            if (providerName.equals(info.packageName)) {
                return true;
            }
        }
        return false;*/
    }


    @Override
    public int getItemCount() {
        if (buy != null) {
            return buy.size();
        } else {
            return 0;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<Providers> buy) {
        this.buy = buy;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView provideLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            provideLogo = itemView.findViewById(R.id.watchProviderIcons);
        }
    }
}
