package com.samples.phoneverification.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.R;
import com.samples.phoneverification.adapters.CastCrewAdapter;
import com.samples.phoneverification.adapters.MovieAdapter;
import com.samples.phoneverification.adapters.TrailerAdapter;
import com.samples.phoneverification.adapters.WatchPAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.databinding.ActivityMovieDetailsBinding;
import com.samples.phoneverification.model.CastCrewList;
import com.samples.phoneverification.model.CastModel;
import com.samples.phoneverification.model.MediaGroup;
import com.samples.phoneverification.model.MediaList;
import com.samples.phoneverification.model.MovieIdDetails;
import com.samples.phoneverification.model.MovieModel;
import com.samples.phoneverification.model.MovieResults;
import com.samples.phoneverification.model.ProvidersRegionList;
import com.samples.phoneverification.model.SpokenLanguages;
import com.samples.phoneverification.model.WatchProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MovieDetailsActivity extends AppCompatActivity {

    private int movieId;
    private ActivityMovieDetailsBinding binding;
    WatchPAdapter watchProviderAdapter;
    private boolean isWishListed = false;
    private TrailerAdapter trailerAdapter;
    private CastCrewAdapter castsCrewAdapter;
    private MovieAdapter recommendationAdapter;
    String country = Locale.getDefault().getCountry();
    ArrayList<CastCrewList> crewArray = new ArrayList<>();
    private final List<String> langList = new ArrayList<>();
    private ArrayList<CastCrewList> castArray = new ArrayList<>();
    private ArrayList<MediaList> mediaListList = new ArrayList<>();
    Retrofit retrofit = new Retrofit.Builder().baseUrl(URLs.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build();

    private final ArrayList<MediaList> filteredMedia = new ArrayList<>();
    private ArrayList<MovieResults> recommendedResults = new ArrayList<>();
    private final APIInterface anInterface = retrofit.create(APIInterface.class);
    private final HashMap<String, ProvidersRegionList> regionList = new HashMap<>();
    private final SimpleDateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private final SimpleDateFormat outputDate = new SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: Get Movie Id from last fragment.
        Intent intent = getIntent();

        movieId = intent.getIntExtra("movie_id", 0);
        binding.toolbarBack.setOnClickListener(v -> onBackPressed());

        // TODO: Call API and set UI.
        Call<MovieIdDetails> details_call = anInterface.MOVIE_ID_DETAILS_CALL(movieId, URLs.API_KEY);
        details_call.enqueue(new Callback<MovieIdDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieIdDetails> call, @NonNull Response<MovieIdDetails> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    MovieIdDetails itemDetails = response.body();
                    addDataToUI(itemDetails);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieIdDetails> call, @NonNull Throwable t) {
                Log.w("API Failing", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void addDataToUI(MovieIdDetails itemDetails) {
        appBarLayer(itemDetails);

        // Todo: 5. Release Date, 6. Spoken Languages, 8. Movie Description
        try {
            Date date = inputDate.parse(itemDetails.getMovie_release_date());
            if (date != null) {
                binding.releaseOn.append(" " + outputDate.format(date));
            } else {
                binding.releaseOnTitle.setVisibility(View.GONE);
                binding.releaseOn.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // TODO: Set Multiple Languages
        for (SpokenLanguages language : itemDetails.getSpoken_language()) {
            langList.add(language.getLanguage_name());
        }
        String multiLang = TextUtils.join(", ", langList);

        binding.languageVersions.setText(multiLang);

        if (itemDetails.getMovieOverview() == null) {
            binding.description.setVisibility(View.GONE);
            binding.movieDescription.setVisibility(View.GONE);
        } else
            binding.movieDescription.setText(itemDetails.getMovieOverview());

        // TODO: Set the Layout Mangers before Setting the adapters for recycler view
        binding.trailerRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        binding.watchProviderRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        binding.castRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        binding.recommendationRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        // TODO: 7. All Adapters
        initAdapters();
        initApiCalls();
    }

    // TODO: 8. API Calls
    private void initApiCalls() {
        watchProviderCall();
        mediaTrailers();
        starCast();
        recommendedMovies();
    }

    private void watchProviderCall() {
        Call<WatchProvider> call = anInterface.MOVIE_WATCH_PROVIDER_CALL(movieId, URLs.API_KEY);
        call.enqueue(new Callback<WatchProvider>() {
            @Override
            public void onResponse(@NonNull Call<WatchProvider> call, @NonNull Response<WatchProvider> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WatchProvider watchPList = response.body();
                    Map<String, ProvidersRegionList> region = watchPList.getRegionList();
                    if (region.containsKey(country)) {
                        Map<String, ProvidersRegionList> provider = new HashMap<>(region);
                        watchProviderAdapter.updateData(provider);
                    } else {
                        binding.availableOn.setVisibility(View.GONE);
                        binding.watchProviderRecycler.setVisibility(View.GONE);
                    }
                } else {
                    binding.availableOn.setVisibility(View.GONE);
                    binding.watchProviderRecycler.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WatchProvider> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: Watch Provider" + call);
            }
        });
    }

    private void mediaTrailers() {
        Call<MediaGroup> mediaGroupCall = anInterface.MOVIE_MEDIA_GROUP_CALL(movieId, URLs.API_KEY);
        mediaGroupCall.enqueue(new Callback<MediaGroup>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MediaGroup> call, @NonNull Response<MediaGroup> response) {
                if (response.body() != null && response.isSuccessful()) {
                    //TODO: Response of API.
                    mediaListList = response.body().getMediaList();

                    //TODO: Filtered Response for Trailer and Teaser.
                    for (MediaList array : mediaListList) {
                        if (array.getMedia_type().equals("Trailer") || array.getMedia_type().equals("Teaser")) {
                            filteredMedia.add(array);
                        }
                    }
                    if (!filteredMedia.isEmpty()) {
                        trailerAdapter.updateData(filteredMedia);
                        trailerAdapter.notifyDataSetChanged();
                        Log.d("filteredMedia", "onResponse: " + filteredMedia.size());
                    }
                } else {
                    binding.trailerHeading.setVisibility(View.GONE);
                    binding.trailerRecycler.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MediaGroup> call, @NonNull Throwable t) {
                Log.w("TrailerResponse", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void starCast() {
        Call<CastModel> castPOJOCall = anInterface.MOVIE_CAST_MODEL_CALL(movieId, URLs.API_KEY);
        castPOJOCall.enqueue(new Callback<CastModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<CastModel> call, @NonNull Response<CastModel> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    // updateCast Array.
                    castArray = response.body().getCastList();
                    castsCrewAdapter.updateData(castArray);
                    castsCrewAdapter.notifyDataSetChanged();

                    // updateCrew Array.
                    crewArray = response.body().getCrewArrays();
                } else {
                    binding.starCastTitle.setVisibility(View.GONE);
                    binding.castRecycler.setVisibility(View.GONE);
                    Log.w("is Null", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CastModel> call, @NonNull Throwable t) {
                Log.w("CastCall", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void recommendedMovies() {
        Call<MovieModel> recommendation = anInterface.RECOMMENDED_MOVIES_CALL(movieId, URLs.API_KEY);
        recommendation.enqueue(new Callback<MovieModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                if (response.body() != null && response.isSuccessful()) {
                    recommendedResults = response.body().getMovieResults();
                    if (recommendedResults.size() == 0) {
                        binding.recommendedHeading.setVisibility(View.INVISIBLE);
                    } else {
                        binding.recommendedHeading.setVisibility(View.VISIBLE);
                    }
                    recommendationAdapter.updateData(recommendedResults);
                    recommendationAdapter.notifyDataSetChanged();
                } else {
                    binding.recommendedHeading.setVisibility(View.GONE);
                    binding.recommendationRecycler.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Log.w("Recommendation Call", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void initAdapters() {
        // TODO: 1. (Trailer, Teaser), 2. initCasts, 3. initRecommendedMovies.
        initTrailers();
        initWatchProvider();
        initCasts();
        initRecommendedMovies();
    }

    private void initWatchProvider() {
        watchProviderAdapter = new WatchPAdapter(getApplicationContext(), regionList, (item, position, action) ->
                Log.d("TAG", "WatchProvider: " + regionList.keySet().toArray()[position].toString()));
        binding.watchProviderRecycler.setAdapter(watchProviderAdapter);
    }

    private void initRecommendedMovies() {
        recommendationAdapter = new MovieAdapter(getApplicationContext(), recommendedResults, (item, position, action) -> {
            Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", item.getMovieId());
            startActivity(intent);
        });
        binding.recommendationRecycler.setAdapter(recommendationAdapter);
    }

    private void initCasts() {
        castsCrewAdapter = new CastCrewAdapter(getApplicationContext(), castArray, (item, position, action) ->
                Log.d("TrailerAdapterTag", "initTrailers: " + castArray.size()));
        binding.castRecycler.setAdapter(castsCrewAdapter);
    }

    private void initTrailers() {
        trailerAdapter = new TrailerAdapter(getApplicationContext(), mediaListList, (item, position, action) ->
                Log.d("TrailerAdapterTag", "initTrailers: " + mediaListList.size()));
        binding.trailerRecycler.setAdapter(trailerAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void appBarLayer(MovieIdDetails itemDetails) {

        binding.collapsingToolbar.setTitle(itemDetails.getStandardMovieTitle());

        // TODO: 1. BackPath setup, 2. Movie Title, 3. Ratings,  4. setEvent(onClick) on Add_to_wishlist
        Glide.with(binding.backdropPath).load(URLs.IMAGE_BASE_URL + itemDetails.getBackdrop_path())
                .into(binding.backdropPath);
        binding.movieTitle.setText(itemDetails.getStandardMovieTitle());

        // TODO. Rating Bar need to set color.
        binding.ratingBar.setRating((float) (itemDetails.getVoteAverage() / 10) * 5);

        binding.wishList.setOnClickListener(v -> {
            if (isWishListed) {
                isWishListed = false;
                binding.wishList.setText(R.string.wishList_add);
                binding.wishList.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.wishlist_add, 0, 0, 0);
            } else {
                isWishListed = true;
                binding.wishList.setText(R.string.wishlist_remove);
                binding.wishList.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.wishlist_remove, 0, 0, 0);
            }
        });
    }
}
