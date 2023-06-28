package com.samples.phoneverification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.R;
import com.samples.phoneverification.apimodel.RecyclerItemViewInterface;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.apimodel.MovieResults;

import java.util.ArrayList;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselHolder> {

    private Context context;
    ArrayList<MovieResults> arrayList;
    RecyclerItemViewInterface anInterface;

    public CarouselAdapter(Context context, ArrayList<MovieResults> upComingMovieArrayList, RecyclerItemViewInterface anInterface) {
        this.context = context;
        this.arrayList = upComingMovieArrayList;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public CarouselHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarouselHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.carousel_imageslider,
                        parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselHolder holder, int position) {

        String backdropPath = arrayList.get(position).getBackdropPath();

        if (backdropPath == null) {
            holder.imageView.setImageResource(R.drawable.no_poster);
        } else {
            Glide.with(holder.imageView).load(URLs.IMAGE_BASE_URL + arrayList.get(position).getBackdropPath()).into(holder.imageView);
        }
        holder.imageView.setOnClickListener(v -> { anInterface.onItemClick(position); });

        if (position == arrayList.size() - 2 ) {
            holder.itemView.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setMovieResults(ArrayList<MovieResults> upComingMovieArrayList) {
        this.arrayList = upComingMovieArrayList;
        notifyDataSetChanged();
    }

    class CarouselHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public CarouselHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.upcomingMovieImages);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            arrayList.addAll(arrayList);
            notifyDataSetChanged();
        }
    };

}
