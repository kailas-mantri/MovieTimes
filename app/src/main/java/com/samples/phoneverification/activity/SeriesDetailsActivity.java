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
import com.samples.phoneverification.databinding.ActivitySeriesDetailsBinding;
import com.samples.phoneverification.adapters.CastCrewAdapter;
import com.samples.phoneverification.adapters.EpisodeAdapter;
import com.samples.phoneverification.adapters.SeasonTextAdapter;
import com.samples.phoneverification.adapters.SeriesAdapter;
import com.samples.phoneverification.adapters.TrailerAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.model.CastCrewArray;
import com.samples.phoneverification.model.CastPOJOModel;
import com.samples.phoneverification.model.MediaGroup;
import com.samples.phoneverification.model.MediaTypeArray;
import com.samples.phoneverification.model.Seasons;
import com.samples.phoneverification.model.SeriesItemResults;
import com.samples.phoneverification.model.SeasonNumberDetails;
import com.samples.phoneverification.model.SeriesModel;
import com.samples.phoneverification.model.SeriesResults;
import com.samples.phoneverification.model.SpokenLanguages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SeriesDetailsActivity extends AppCompatActivity {

    ActivitySeriesDetailsBinding binding;
    private int series_id, seasonNo;
    private boolean isWishListed = false;
    SeasonTextAdapter textAdapter;
    EpisodeAdapter episodeAdapter;
    private CastCrewAdapter castsCrewAdapter;
    private SeriesAdapter recommendationAdapter;
    private TrailerAdapter trailerAdapter;
    private SeasonNumberDetails seasonResults;
    private ArrayList<Seasons> seasonsList = new ArrayList<>();
    private ArrayList<MediaTypeArray> mediaTypeArrays = new ArrayList<>();
    private final ArrayList<MediaTypeArray> filteredMedia = new ArrayList<>();
    private ArrayList<CastCrewArray> castArray = new ArrayList<>();
    private ArrayList<SeriesResults> recommendedResults = new ArrayList<>();
    ArrayList<CastCrewArray> crewArray = new ArrayList<>();
    private final List<String> langList = new ArrayList<>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URLs.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIInterface anInterface = retrofit.create(APIInterface.class);

    private final SimpleDateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private final SimpleDateFormat outputDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeriesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarBack.setOnClickListener(v -> onBackPressed());

        Intent intent = getIntent();
        series_id = intent.getIntExtra("series_id",0);

        // TODO: Series_id - API Call.
        Call<SeriesItemResults> call = anInterface.SERIES_ITEM_ID_RESULTS_CALL(series_id, URLs.API_KEY);
        call.enqueue(new Callback<SeriesItemResults>() {
            @Override
            public void onResponse(@NonNull Call<SeriesItemResults> call, @NonNull Response<SeriesItemResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SeriesItemResults itemResults = response.body();
                    seasonsList = itemResults.getSeasons();
                    displayUI(itemResults);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesItemResults> call, @NonNull Throwable t) {
                Log.w("SeriesItemId API", "onFailure: ", t.fillInStackTrace());
            }
        });
    }

    private void displayUI(SeriesItemResults itemResults) {
        AppBarDetails(itemResults);
        SeriesDetails(itemResults);
        SeasonDetails(itemResults);
    }

    private void AppBarDetails(SeriesItemResults itemResults) {
        // 1. Set Background - COLLAPSING Toolbar and title.
        String backDropPath = itemResults.getBackdropPath();
        if (backDropPath == null) {
            binding.backdropPath.setBackgroundResource(R.drawable.no_poster);
        } else {
            Glide.with(binding.backdropPath).load(URLs.IMAGE_BASE_URL + itemResults.getBackdropPath())
                    .into(binding.backdropPath);
        }

        binding.collapsingToolbar.setTitle(itemResults.getSeriesName());
        binding.seriesTitle.setText(itemResults.getSeriesName());
        binding.ratingBar.setRating((float) (itemResults.getVoteAverage() / 10) * 5);

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
    private void SeasonDetails(SeriesItemResults itemResults) {
        init_SeasonTitle_Adapter(itemResults);
        binding.recyclerSeasons.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
    }

    private void init_SeasonTitle_Adapter(SeriesItemResults itemResults) {
        textAdapter = new SeasonTextAdapter(getApplicationContext(), seasonsList, (item, position, action) -> {
            int series_id = itemResults.getSeriesId();
            seasonNo = item.getSeasonNumber();
            seasonCalls(series_id, seasonNo);
        });
        binding.recyclerSeasons.setAdapter(textAdapter);
        textAdapter.setDefaultItem();
    }

    private void seasonCalls(int series_id, int seasonNo) {
        Call<SeasonNumberDetails> seasonCall = anInterface.SERIES_ITEM_SEASON_DETAILS_CALL(series_id, seasonNo, URLs.API_KEY);
        seasonCall.enqueue(new Callback<SeasonNumberDetails>() {
            @Override
            public void onResponse(@NonNull Call<SeasonNumberDetails> call, @NonNull Response<SeasonNumberDetails> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    seasonResults = response.body();
                    SeasonDataImpl(seasonResults);
                    initApiCalls();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeasonNumberDetails> call, @NonNull Throwable t) {
                Log.w("SeasonItemDetailsCall", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void SeasonDataImpl(SeasonNumberDetails sNumberDetails) {
        // TODO: Input layout validations.
        if (sNumberDetails.getSeason_poster_path() == null)
            binding.seasonShortNote.seriesSeasonImages.setBackgroundResource(R.drawable.no_poster);
        else {
            Glide.with(binding.seasonShortNote.seriesSeasonImages).load(URLs.IMAGE_BASE_URL + sNumberDetails.getSeason_poster_path())
                    .into(binding.seasonShortNote.seriesSeasonImages);
        }

        binding.seasonShortNote.seriesSeasonTitle.setText(sNumberDetails.getSeason_name());

        if (sNumberDetails.getSeasons_overview() == null) {
            binding.description.setVisibility(View.GONE);
            binding.seasonShortNote.seasonDescription.setVisibility(View.GONE);
        } else
            binding.seasonShortNote.seasonDescription.setText(sNumberDetails.getSeasons_overview());

        try {
            Date date = inputDate.parse(sNumberDetails.getAir_date());
            if (date != null)
                binding.seasonShortNote.seriesDate.setText(outputDate.format(date));
            else
                binding.seasonShortNote.seriesDate.setVisibility(View.GONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Set LayoutManager, Adapter - RecyclerView
        binding.episodeRecyclerList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        episodeRecyclerAdapter();

        binding.trailerRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        binding.castRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
    }

    private void episodeRecyclerAdapter() {
        episodeAdapter = new EpisodeAdapter(getApplicationContext(), seasonResults.getEpisodeList(), (item, position, action) ->
            Log.d("episodeAdapter", "episodeRecyclerAdapter: " + seasonResults.getEpisodeList()));
        binding.episodeRecyclerList.setAdapter(episodeAdapter);
    }

    // 3.DATA - For Bottom side.
    @SuppressLint("SetTextI18n")
    private void SeriesDetails(SeriesItemResults itemResults) {

//        1. Release Date
        try {
            Date date = inputDate.parse(itemResults.getFirstAirDate());
            if (date != null) {
                binding.releaseOn.setText(outputDate.format(date));
            }  else {
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

    private void initApiCalls() {
//         1. Trailer, Teaser Adapter, 2. castAdapter, 3. RecommendedMoviesAdapters
        initTrailers();
        StarCast();
        RecommendedMoviesAdapters();
    }

    private void initAdapters() {
        CastAdapter();
        MediaTrailers();
        recommendedSeries();
    }

    private void MediaTrailers() {
        Call<MediaGroup> mediaCall = anInterface.SERIES_MEDIA_GROUP_CALL(series_id, URLs.API_KEY);
        mediaCall.enqueue(new Callback<MediaGroup>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MediaGroup> call, @NonNull Response<MediaGroup> response) {
                if (response.body() != null && response.isSuccessful()) {
                    MediaGroup group = response.body();
                    mediaTypeArrays = group.getMediaList();
                    for (MediaTypeArray array : mediaTypeArrays) {
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

    private void RecommendedMoviesAdapters() {
        recommendationAdapter = new SeriesAdapter(getApplicationContext(), recommendedResults, (item, position, action) -> {
            Intent intent = new Intent(getApplicationContext(), SeriesDetailsActivity.class);
            intent.putExtra("series_id", item.getSeriesId());
            startActivity(intent);
        });
        binding.recommendationRecycler.setAdapter(recommendationAdapter);
    }

    private void StarCast() {
        Call<CastPOJOModel> castPOJOCall = anInterface.SERIES_CAST_POJO_MODEL_CALL(series_id, seasonNo, URLs.API_KEY);
        castPOJOCall.enqueue(new Callback<CastPOJOModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<CastPOJOModel> call, @NonNull Response<CastPOJOModel> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    // updateCast Array.
                    castArray = response.body().getCastList();
                    castsCrewAdapter.updateData(castArray);

                    // updateCrew Array.
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
                    Log.w("is Null", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CastPOJOModel> call, @NonNull Throwable t) {
                Log.w("CastCall", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void CastAdapter() {
        castsCrewAdapter = new CastCrewAdapter(getApplicationContext(), castArray, (item, position, action) ->{
            System.out.println(castArray);
            Log.d("CastAdapter", "initCast: " + castArray.size());
        });
        binding.castRecycler.setAdapter(castsCrewAdapter);
    }

    private void initTrailers() {
        trailerAdapter = new TrailerAdapter(getApplicationContext(), mediaTypeArrays, (item, position, action) -> {
            System.out.println(mediaTypeArrays);
            Log.d("TrailerAdapter", "initTrailers: "+mediaTypeArrays);
        });
        binding.trailerRecycler.setAdapter(trailerAdapter);
    }
}