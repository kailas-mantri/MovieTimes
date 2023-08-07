package com.samples.phoneverification.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.samples.phoneverification.BuildConfig;
import com.samples.phoneverification.R;
import com.samples.phoneverification.adapters.CastCrewAdapter;
import com.samples.phoneverification.adapters.MovieAdapter;
import com.samples.phoneverification.adapters.TrailerAdapter;
import com.samples.phoneverification.adapters.WatchPAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.databinding.ActivityMovieDetailsBinding;
import com.samples.phoneverification.dbmodel.WishListDBHelper;
import com.samples.phoneverification.dbmodel.WishListItem;
import com.samples.phoneverification.model.CastCrewList;
import com.samples.phoneverification.model.CastModel;
import com.samples.phoneverification.model.MediaGroup;
import com.samples.phoneverification.model.MediaList;
import com.samples.phoneverification.model.MovieIdDetails;
import com.samples.phoneverification.model.MovieModel;
import com.samples.phoneverification.model.MovieResults;
import com.samples.phoneverification.model.Providers;
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
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MovieDetailsActivity extends BaseActivity {

    private int movieId;
    private WishListItem currentItem;
    private ActivityMovieDetailsBinding binding;
    WatchPAdapter watchProviderAdapter;
    private boolean isWishListed = false;
    private static final int isMovie = 1;
    private TrailerAdapter trailerAdapter;
    private CastCrewAdapter castsCrewAdapter;
    private MovieAdapter recommendationAdapter;
    private WishListDBHelper wishListDBHelper;
    final String country = Locale.getDefault().getCountry();
    private ArrayList<Providers> buy = new ArrayList<>();
    private ArrayList<Providers> rent = new ArrayList<>();
    ArrayList<CastCrewList> crewArray = new ArrayList<>();
    private final List<String> langList = new ArrayList<>();
    private ArrayList<CastCrewList> castArray = new ArrayList<>();
    private ArrayList<MediaList> mediaListList = new ArrayList<>();
    final Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build();

    private final ArrayList<MediaList> filteredMedia = new ArrayList<>();
    private ArrayList<MovieResults> recommendedResults = new ArrayList<>();
    private final APIInterface anInterface = retrofit.create(APIInterface.class);
    final HashMap<String, ProvidersRegionList> regionList = new HashMap<>();
    private final SimpleDateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private final SimpleDateFormat outputDate = new SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: Get MovieId from last fragment.
        Intent intent = getIntent();
        movieId = intent.getIntExtra("movie_id", 0);

        wishListDBHelper = new WishListDBHelper(getApplicationContext());
        isWishListed = wishListDBHelper.isItemWishListed(movieId);

        updateWishlist();
        binding.toolbarBack.setOnClickListener(v -> onBackPressed());

        // TODO: Call API and set UI.
        Call<MovieIdDetails> details_call = anInterface.MOVIE_ID_DETAILS_CALL(movieId, BuildConfig.API_KEY);
        details_call.enqueue(new Callback<MovieIdDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieIdDetails> call, @NonNull Response<MovieIdDetails> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    MovieIdDetails itemDetails = response.body();
                    //TODO: Check Wishlist?
//                    Log.d("Movie ID", "Movie ID: " + itemDetails.getMovie_id());
                    currentItem = new WishListItem(isMovie, itemDetails.getMovie_id(), itemDetails.getStandardMovieTitle(), itemDetails.getMovieOverview(),
                            itemDetails.getPosterPath(), itemDetails.getBackdrop_path(), itemDetails.getMovie_release_date());
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
        if (!itemDetails.getMovie_release_date().isEmpty() && itemDetails.getMovie_release_date() != null) {
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
        } else {
            binding.releaseOn.setVisibility(View.GONE);
            binding.releaseOnTitle.setVisibility(View.GONE);
        }

        // TODO: Set Multiple Languages
        if (!itemDetails.getSpoken_language().isEmpty() && itemDetails.getSpoken_language() != null) {
            for (SpokenLanguages language : itemDetails.getSpoken_language()) {
                langList.add(language.getLanguage_name());
            }
            String multiLang = TextUtils.join(", ", langList);
            binding.languageVersions.setText(multiLang);
        } else {
            binding.languageHeading.setVisibility(View.GONE);
            binding.languageVersions.setVisibility(View.GONE);
        }

        if (!itemDetails.getMovieOverview().isEmpty() && itemDetails.getMovieOverview() != null)
            binding.movieDescription.setText(itemDetails.getMovieOverview());
        else {
            binding.description.setVisibility(View.GONE);
            binding.movieDescription.setVisibility(View.GONE);
        }

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
        watchProviderCallback();
        mediaTrailersCallback();
        starCastCallback();
        recommendedMoviesCallback();
    }

    private void watchProviderCallback() {
        Call<WatchProvider> call = anInterface.MOVIE_WATCH_PROVIDER_CALL(movieId, BuildConfig.API_KEY);
        call.enqueue(new Callback<WatchProvider>() {
            @Override
            public void onResponse(@NonNull Call<WatchProvider> call, @NonNull Response<WatchProvider> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WatchProvider watchPList = response.body();
                    Map<String, ProvidersRegionList> region = watchPList.getRegionList();
                    if (watchProviderAdapter != null) {
                        if (region.containsKey(country)) {
                            if (region.get(country) != null) {
                                rent = Objects.requireNonNull(region.get(country)).getRentList();
                                buy = Objects.requireNonNull(region.get(country)).getBuyList();
                                if (buy != null) {
                                    watchProviderAdapter.updateData(buy);
                                } else {
                                    if (rent != null)
                                        watchProviderAdapter.updateData(rent);
                                    else
                                        watchProviderVisibility();
                                }
                            }
                        } else
                            watchProviderVisibility();
                    } else
                        watchProviderVisibility();
                } else
                    watchProviderVisibility();
            }

            @Override
            public void onFailure(@NonNull Call<WatchProvider> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: Watch Provider" + call);
            }
        });
    }

    private void watchProviderVisibility() {
        binding.availableOn.setVisibility(View.GONE);
        binding.watchProviderRecycler.setVisibility(View.GONE);
    }

    private void mediaTrailersCallback() {
        Call<MediaGroup> mediaGroupCall = anInterface.MOVIE_MEDIA_GROUP_CALL(movieId, BuildConfig.API_KEY);
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
                    }
                } else
                    trailerVisibility();
            }

            @Override
            public void onFailure(@NonNull Call<MediaGroup> call, @NonNull Throwable t) {
                Log.w("TrailerResponse", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void trailerVisibility() {
        binding.trailerHeading.setVisibility(View.GONE);
        binding.trailerRecycler.setVisibility(View.GONE);
    }

    private void starCastCallback() {
        Call<CastModel> castPOJOCall = anInterface.MOVIE_CAST_MODEL_CALL(movieId, BuildConfig.API_KEY);
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
                    castGone();
                    Log.i("is Null", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CastModel> call, @NonNull Throwable t) {
                Log.w("CastCall", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void castGone() {
        binding.starCastTitle.setVisibility(View.GONE);
        binding.castRecycler.setVisibility(View.GONE);
    }

    private void recommendedMoviesCallback() {
        Call<MovieModel> recommendation = anInterface.RECOMMENDED_MOVIES_CALL(movieId, BuildConfig.API_KEY);
        recommendation.enqueue(new Callback<MovieModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                if (response.body() != null && response.isSuccessful()) {
                    recommendedResults = response.body().getMovieResults();
                    if (recommendedResults.size() == 0)
                        binding.recommendedHeading.setVisibility(View.INVISIBLE);
                    else
                        binding.recommendedHeading.setVisibility(View.VISIBLE);

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
        // TODO: 1. (Trailer, Teaser), 2. Casts, 3. RecommendedMovies.
        init_Trailers();
        init_WatchProvider();
        init_Casts();
        init_RecommendedMovies();
    }

    private void init_WatchProvider() {
        ArrayList<Providers> list = new ArrayList<>();
        if (buy != null) {
            list = buy;
        } else if (rent != null) {
            list = rent;
        } else {
            list.size();
        }
        watchProviderAdapter = new WatchPAdapter(getApplicationContext(), list, (item, position, action) ->
                Log.d("TAG", "WatchProvider: " + regionList.keySet().toArray()[position].toString()));
        binding.watchProviderRecycler.setAdapter(watchProviderAdapter);
    }

    private void init_RecommendedMovies() {
        recommendationAdapter = new MovieAdapter(getApplicationContext(), recommendedResults, (item, position, action) -> {
            Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", item.getMovieId());
            startActivity(intent);
        });
        binding.recommendationRecycler.setAdapter(recommendationAdapter);
    }

    private void init_Casts() {
        castsCrewAdapter = new CastCrewAdapter(getApplicationContext(), castArray, (item, position, action) ->
                Log.d("TrailerAdapterTag", "init_Trailers: " + castArray.size()));
        binding.castRecycler.setAdapter(castsCrewAdapter);
    }

    private void init_Trailers() {
        trailerAdapter = new TrailerAdapter(getApplicationContext(), mediaListList, (item, position, action) ->
                Log.d("TrailerAdapterTag", "init_Trailers: " + mediaListList.size()));
        binding.trailerRecycler.setAdapter(trailerAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void appBarLayer(MovieIdDetails itemDetails) {
        binding.collapsingToolbar.setTitle(itemDetails.getStandardMovieTitle());

        // TODO: 1. BackPath setup, 2. Movie Title, 3. Ratings,  4. setEvent(onClick) on Add_to_wishlist
        Glide.with(binding.backdropPath).load(BuildConfig.IMAGE_BASE_URL + itemDetails.getBackdrop_path())
                .into(binding.backdropPath);
        binding.movieTitle.setText(itemDetails.getStandardMovieTitle());

        // TODO. Rating Bar need to set color.
        binding.ratingBar.setRating((float) (itemDetails.getVoteAverage() / 10) * 5);

        binding.wishList.setOnClickListener(v -> {
            if (isWishListed) {
                isWishListed = false;
                binding.wishList.setText(R.string.wishList_add);
                binding.wishList.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.wishlist_add, 0, 0, 0);
                wishListDBHelper.removeFromWishList(currentItem);
            } else {
                isWishListed = true;
                binding.wishList.setText(R.string.wishlist_remove);
                binding.wishList.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.wishlist_remove, 0, 0, 0);
                wishListDBHelper.addToWishList(currentItem);
            }
        });
    }

    private void updateWishlist() {
        if (isWishListed) {
            binding.wishList.setText(R.string.wishlist_remove);
            binding.wishList.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.wishlist_remove, 0, 0, 0);
        } else {
            binding.wishList.setText(R.string.wishList_add);
            binding.wishList.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.wishlist_add, 0, 0, 0);
        }
    }
}
