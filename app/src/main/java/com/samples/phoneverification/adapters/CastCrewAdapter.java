package com.samples.phoneverification.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.BuildConfig;
import com.samples.phoneverification.R;
import com.samples.phoneverification.model.CastCrewList;
import com.samples.phoneverification.apimodel.OnRecyclerItemClickListener;

import java.util.ArrayList;

public class CastCrewAdapter extends RecyclerView.Adapter<CastCrewAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<CastCrewList> castArrays;
    final OnRecyclerItemClickListener<CastCrewList> anInterface;

    public CastCrewAdapter(Context context, ArrayList<CastCrewList> castArrays, OnRecyclerItemClickListener<CastCrewList> anInterface) {
        this.context = context;
        this.castArrays = castArrays;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_cast_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CastCrewList cast = castArrays.get(position);
        String profile_path = cast.getPersonProfile_path();
        holder.realName.setText(cast.getOriginalRealName());
        holder.rollName.setText(cast.getRollName());

        if (profile_path == null) {
            holder.castImage.setImageResource(R.drawable.no_poster);
        } else {
            Glide.with(holder.castImage).load(BuildConfig.IMAGE_BASE_URL+cast.getPersonProfile_path())
                    .into(holder.castImage);
        }

        holder.castImage.setOnClickListener(v ->
            Log.d("castImageClick", "onBindViewHolder: "+castArrays.get(position).getOriginalRealName())
        );
    }

    @Override
    public int getItemCount() {
        return castArrays.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<CastCrewList> castArray) {
        this.castArrays = castArray;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView castImage;
        TextView realName, rollName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            castImage = itemView.findViewById(R.id.starCastImage);
            realName = itemView.findViewById(R.id.realName);
            rollName = itemView.findViewById(R.id.rollName);
        }
    }
}
