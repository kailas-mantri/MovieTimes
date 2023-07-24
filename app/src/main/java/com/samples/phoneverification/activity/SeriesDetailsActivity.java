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
import com.samples.phoneverification.R;
import com.samples.phoneverification.adapters.CastCrewAdapter;
import com.samples.phoneverification.adapters.EpisodeAdapter;
import com.samples.phoneverification.adapters.SeasonTextAdapter;
import com.samples.phoneverification.adapters.SeriesAdapter;
import com.samples.phoneverification.adapters.TrailerAdapter;
import com.samples.phoneverification.adapters.WatchPAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.databinding.ActivitySeriesDetailsBinding;
import com.samples.phoneverification.model.CastCrewList;
import com.samples.phoneverification.model.CastModel;
import com.samples.phoneverification.model.MediaGroup;
import com.samples.phoneverification.model.MediaList;
import com.samples.phoneverification.model.ProvidersRegionList;
import com.samples.phoneverification.model.SeasonNoDetails;
import com.samples.phoneverification.model.Seasons;
import com.samples.phoneverification.model.SeriesIdResults;
import com.samples.phoneverification.model.SeriesModel;
import com.samples.phoneverification.model.SeriesResults;
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

public class SeriesDetailsActivity extends BaseActivity {

    private ActivitySeriesDetailsBinding binding;
    private boolean isWishListed = false;
    private int series_id, seasonNo;
    SeasonTextAdapter textAdapter;
    EpisodeAdapter episodeAdapter;
    WatchPAdapter watchProviderAdapter;
    private SeasonNoDetails seasonResults;
    private TrailerAdapter trailerAdapter;
    private CastCrewAdapter castsCrewAdapter;
    private SeriesAdapter recommendationAdapter;
    Retrofit retrofit = new Retrofit.Builder().baseUrl(URLs.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build();
    ArrayList<CastCrewList> crewArray = new ArrayList<>();
    private final List<String> langList = new ArrayList<>();
    private ArrayList<Seasons> seasonsList = new ArrayList<>();
    private ArrayList<MediaList> mediaLists = new ArrayList<>();
    private ArrayList<CastCrewList> castArray = new ArrayList<>();
    private final String country = Locale.getDefault().getCountry();
    private final ArrayList<MediaList> filteredMedia = new ArrayList<>();
    private final ArrayList<SeriesResults> recommendedResults = new ArrayList<>();
    private final SimpleDateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private final SimpleDateFormat outputDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private final APIInterface anInterface = retrofit.create(APIInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeriesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarBack.setOnClickListener(v -> onBackPressed());

        Intent intent = getIntent();
        series_id = intent.getIntExtra("series_id", 0);

        // TODO: Series_id - API Call.
        Call<SeriesIdResults> call = anInterface.SERIES_ITEM_ID_RESULTS_CALL(series_id, URLs.API_KEY);
        call.enqueue(new Callback<SeriesIdResults>() {
            @Override
            public void onResponse(@NonNull Call<SeriesIdResults> call, @NonNull Response<SeriesIdResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SeriesIdResults idResults = response.body();
                    seasonsList = idResults.getSeasons();
                    displayUI(idResults);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesIdResults> call, @NonNull Throwable t) {
                Log.w("SeriesItemId API", "onFailure: ", t.fillInStackTrace());
            }
        });
    }

    private void displayUI(SeriesIdResults idResults) {
        appBarDetails(idResults);
        seriesDetails(idResults);
        seasonDetails(idResults);
    }

    private void appBarDetails(SeriesIdResults idResults) {
        // 1. Set Background - COLLAPSING Toolbar and title.
        String backDropPath = idResults.getBackdropPath();
        if (backDropPath == null) {
            binding.backdropPath.setBackgroundResource(R.drawable.no_poster);
        } else {
            Glide.with(binding.backdropPath).load(URLs.IMAGE_BASE_URL + idResults.getBackdropPath()).into(binding.backdropPath);
        }

        binding.collapsingToolbar.setTitle(idResults.getSeriesName());
        binding.seriesTitle.setText(idResults.getSeriesName());
        binding.ratingBar.setRating((float) (idResults.getVoteAverage() / 10) * 5);

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

    //TODO: ADAPTER - Default selected Season and Season - Episode API Call.
    private void seasonDetails(SeriesIdResults idResults) {
        init_SeasonTitle_Adapter(idResults);
        binding.recyclerSeasons.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
    }

    private void init_SeasonTitle_Adapter(SeriesIdResults itemResults) {
        textAdapter = new SeasonTextAdapter(getApplicationContext(), seasonsList, (item, position, action) -> {
            int series_id = itemResults.getSeriesId();
            seasonNo = item.getSeasonNumber();
            seasonCalls(series_id, seasonNo);
        });
        binding.recyclerSeasons.setAdapter(textAdapter);
        textAdapter.setDefaultItem();
    }

    private void seasonCalls(int series_id, int seasonNo) {
        Call<SeasonNoDetails> seasonCall = anInterface.SERIES_ITEM_SEASON_DETAILS_CALL(series_id, seasonNo, URLs.API_KEY);
        seasonCall.enqueue(new Callback<SeasonNoDetails>() {
            @Override
            public void onResponse(@NonNull Call<SeasonNoDetails> call, @NonNull Response<SeasonNoDetails> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    seasonResults = response.body();
                    seasonDataImpl(seasonResults);
                    initApiCalls();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeasonNoDetails> call, @NonNull Throwable t) {
                Log.w("SeasonItemDetailsCall", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void initApiCalls() {
        // TODO: 1. Trailer, Teaser Adapter, 2. castAdapter, 3. recommendedMoviesAdapters
        initTrailers();
        starCast();
        recommendedMoviesAdapters();
    }

    private void recommendedMoviesAdapters() {
        recommendationAdapter = new SeriesAdapter(getApplicationContext(), recommendedResults, (item, position, action) -> {
            Intent intent = new Intent(getApplicationContext(), SeriesDetailsActivity.class);
            intent.putExtra("series_id", item.getSeriesId());
            startActivity(intent);
        });
        binding.recommendationRecycler.setAdapter(recommendationAdapter);
    }

    private void starCast() {
        Call<CastModel> castPOJOCall = anInterface.SERIES_CAST_MODEL_CALL(series_id, seasonNo, URLs.API_KEY);
        castPOJOCall.enqueue(new Callback<CastModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<CastModel> call, @NonNull Response<CastModel> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    //TODO: updateCast Array.
                    castArray = response.body().getCastList();
                    castsCrewAdapter.updateData(castArray);

                    //TODO: updateCrew Array.
                    crewArray = response.body().getCrewArrays();

                    if (castArray.size() == 0) {
                        binding.starCastTitle.setVisibility(View.GONE);
                        binding.castRecycler.setVisibility(View.GONE);
                    } else {
                        binding.starCastTitle.setVisibility(View.VISIBLE);
                        binding.castRecycler.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.starCastTitle.setVisibility(View.GONE);
                    binding.castRecycler.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CastModel> call, @NonNull Throwable t) {
                Log.w("CastCall", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void initTrailers() {
        trailerAdapter = new TrailerAdapter(getApplicationContext(), mediaLists, (item, position, action) -> {
            System.out.println(mediaLists);
            Log.d("TrailerAdapter", "initTrailers: " + mediaLists);
        });
        binding.trailerRecycler.setAdapter(trailerAdapter);
    }

    private void seasonDataImpl(SeasonNoDetails sNumberResult) {
        // TODO: Input layout validations.
        if (sNumberResult.getSeason_poster_path() == null)
            binding.seasonIntro.seriesSeasonImages.setVisibility(View.GONE);
        else {
            Glide.with(binding.seasonIntro.seriesSeasonImages).load(URLs.IMAGE_BASE_URL + sNumberResult.getSeason_poster_path()).into(binding.seasonIntro.seriesSeasonImages);
        }
        if (sNumberResult.getSeason_name() == null)
            binding.seasonIntro.seriesSeasonTitle.setVisibility(View.GONE);
        else binding.seasonIntro.seriesSeasonTitle.setText(sNumberResult.getSeason_name());


        if (sNumberResult.getSeasons_overview() == null) {
            binding.description.setVisibility(View.GONE);
            binding.seasonIntro.seasonDescription.setVisibility(View.GONE);
        } else binding.seasonIntro.seasonDescription.setText(sNumberResult.getSeasons_overview());

        try {
            Date date = inputDate.parse(sNumberResult.getAir_date());
            if (date != null) binding.seasonIntro.seriesDate.setText(outputDate.format(date));
            else binding.seasonIntro.seriesDate.setVisibility(View.GONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //TODO: Set LayoutManager, Adapter - RecyclerView
        binding.watchProviderRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        binding.episodeRecyclerList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        episodeRecyclerAdapter();

        binding.trailerRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        binding.castRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
    }

    private void episodeRecyclerAdapter() {
        episodeAdapter = new EpisodeAdapter(getApplicationContext(), seasonResults.getEpisodeList(), (item, position, action) -> Log.d("episodeAdapter", "episodeRecyclerAdapter: " + seasonResults.getEpisodeList()));
        binding.episodeRecyclerList.setAdapter(episodeAdapter);
    }

    // 3.DATA - For Bottom side.
    @SuppressLint("SetTextI18n")
    private void seriesDetails(SeriesIdResults itemResults) {

//        1. Release Date
        try {
            Date date = inputDate.parse(itemResults.getFirstAirDate());
            if (date != null) {
                binding.releaseOn.setText(outputDate.format(date));
            } else {
                binding.releaseOn.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        2. Language Block.
        for (SpokenLanguages language : itemResults.getSpokenLanguages()) {
            langList.add(language.getLanguage_name());
        }
        String multiLang = TextUtils.join(", ", langList);
        binding.languageVersions.setText(multiLang);

//        3. Description Block.
        Log.d("Description", itemResults.getSeries_Overview());
        if (itemResults.getSeries_Overview() == null || itemResults.getSeries_Overview().isEmpty()) {
            binding.seriesDescription.setVisibility(View.GONE);

        } else {
            binding.seriesDescription.setText(itemResults.getSeries_Overview());
        }

        // TODO: Set the Layout Mangers before Setting the adapters for recycler view
        binding.recommendationRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        // TODO: 7. Adapter for Trailer/Teaser 9. Casts Adapter (Actor/Actress details), 10. Recommended Movies Adapter, API call
        initAdapters();

        // TODO: API Calls
        initApiCalls();
    }

    private void initAdapters() {
        watchProviderCall();
        castAdapter();
        mediaTrailers();
        recommendedSeries();
    }

    private void watchProviderCall() {
        Call<WatchProvider> call = anInterface.SERIES_WATCH_PROVIDER_CALL(series_id, URLs.API_KEY);
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
        Call<MediaGroup> mediaCall = anInterface.SERIES_MEDIA_GROUP_CALL(series_id, URLs.API_KEY);
        mediaCall.enqueue(new Callback<MediaGroup>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MediaGroup> call, @NonNull Response<MediaGroup> response) {
                if (response.body() != null && response.isSuccessful()) {
                    MediaGroup group = response.body();
                    mediaLists = group.getMediaList();
                    for (MediaList array : mediaLists) {
                        if (array.getMedia_type().equals("Trailer") || array.getMedia_type().equals("Teaser")) {
                            filteredMedia.add(array);
                        }
                    }
                    if (!filteredMedia.isEmpty()) {
                        trailerAdapter.updateData(filteredMedia);
                        trailerAdapter.notifyDataSetChanged();
                        Log.d("filteredMedia", "onResponse: " + filteredMedia.size());
                    } else {
                        binding.trailerHeading.setVisibility(View.GONE);
                    }
                } else {
                    binding.trailerRecycler.setVisibility(View.GONE);
                    binding.trailerHeading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MediaGroup> call, @NonNull Throwable t) {
                Log.w("TAG", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void recommendedSeries() {
        Call<SeriesModel> recommendation = anInterface.RECOMMENDED_SERIES_ITEM_RESULTS_CALL(series_id, URLs.API_KEY);
        recommendation.enqueue(new Callback<SeriesModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SeriesModel> call, @NonNull Response<SeriesModel> response) {
                if (response.body() != null && response.isSuccessful()) {
                    recommendedResults.clear();
                    recommendedResults.addAll(response.body().getSeriesResults());
                    if (recommendedResults.size() == 0) {
                        binding.recommendedHeading.setVisibility(View.INVISIBLE);
                    } else {
                        binding.recommendedHeading.setVisibility(View.VISIBLE);
                    }
                    recommendationAdapter.updateData(recommendedResults);
                    recommendationAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesModel> call, @NonNull Throwable t) {
                Log.w("Recommendation Call", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void castAdapter() {
        castsCrewAdapter = new CastCrewAdapter(getApplicationContext(), castArray, (item, position, action) -> {
            System.out.println(castArray);
            Log.d("castAdapter", "initCast: " + castArray.size());
        });
        binding.castRecycler.setAdapter(castsCrewAdapter);
    }

}