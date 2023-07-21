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
import com.samples.phoneverification.adapters.EpisodeAdapter;
import com.samples.phoneverification.adapters.SeasonTextAdapter;
import com.samples.phoneverification.adapters.SeriesAdapter;
import com.samples.phoneverification.adapters.TrailerAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.databinding.ActivitySeriesDetailsBinding;
import com.samples.phoneverification.model.CastCrewArray;
import com.samples.phoneverification.model.CastPOJOModel;
import com.samples.phoneverification.model.Seasons;
import com.samples.phoneverification.model.SeriesItemResults;
import com.samples.phoneverification.model.SeriesItemSeasonDetails;
import com.samples.phoneverification.model.SeriesModel;
import com.samples.phoneverification.model.SeriesResults;
import com.samples.phoneverification.model.SpokenLanguages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private SeasonTextAdapter textAdapter;
    private EpisodeAdapter episodeAdapter;
    private CastCrewAdapter castsCrewAdapter;
    private SeriesAdapter recommendationAdapter;
    private TrailerAdapter trailerAdapter;
    private SeriesItemSeasonDetails seasonResults;
    SimpleDateFormat inputDate, outputDate;
    private ArrayList<Seasons> seasons = new ArrayList<>();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeriesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarBack.setOnClickListener(v -> onBackPressed() );

        Intent intent = getIntent();
        series_id = (int) intent.getSerializableExtra("series_id");

        Call<SeriesItemResults> call = anInterface.SERIES_ITEM_ID_RESULTS_CALL(series_id, URLs.API_KEY);
        call.enqueue(new Callback<SeriesItemResults>() {
            @Override
            public void onResponse(@NonNull Call<SeriesItemResults> call, @NonNull Response<SeriesItemResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SeriesItemResults itemResults = response.body();
                    seasons = itemResults.getSeasons();
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
        AppBarWithSeasonItems(itemResults);
        StaticSeasonItems(itemResults);
    }

    private void StaticSeasonItems(SeriesItemResults itemResults) {
        for (SpokenLanguages language : itemResults.getSpokenLanguages()) {
            langList.add(language.getLanguage_name());
        }
        String multiLang = TextUtils.join(", ", langList);

        binding.languageVersions.setText(multiLang);
        binding.seriesDescription.setText(itemResults.getSeriesOverview());

        // TODO: Set the Layout Mangers before Setting the adapters for recycler view
        binding.trailerRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        binding.castRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        binding.recommendationRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        // TODO: 7. Adapter for Trailer/Teaser 9. Casts Adapter (Actor/Actress details), 10. Recommended Movies Adapter, API call
        initAdapters();

        // TODO: API Calls
        initApiCalls();

    }


    private void initApiCalls() {
        StarCast();
        /*MediaTrailers();*/
        recommendedSeries();
    }

    private void recommendedSeries() {
        Call<SeriesModel> recommendation = anInterface.RECOMMENDED_SERIES_ITEM_RESULTS_CALL(series_id, URLs.API_KEY);
        recommendation.enqueue(new Callback<SeriesModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SeriesModel> call, @NonNull Response<SeriesModel> response) {
                if (response.body() != null && response.isSuccessful()) {
                    recommendedResults = response.body().getSeriesResults();
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
                    castsCrewAdapter.notifyDataSetChanged();

                    // updateCrew Array.
                    crewArray = response.body().getCrewArrays();
                } else {
                    Log.w("is Null", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CastPOJOModel> call, @NonNull Throwable t) {
                Log.w("CastCall", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void initAdapters() {
//         1. Trailer, Teaser Adapter
        initTrailers();

//         2. castAdapter
        CastAdapter();

//         3. RecommendedMoviesAdapters
        RecommendedMoviesAdapters();
    }

    private void RecommendedMoviesAdapters() {
        recommendationAdapter = new SeriesAdapter(getApplicationContext(), recommendedResults, (item, position, action) -> {
            Intent intent = new Intent(getApplicationContext(), SeriesDetailsActivity.class);
            intent.putExtra("series_id", item.getSeriesId());
            startActivity(intent);
        });
        binding.recommendationRecycler.setAdapter(recommendationAdapter);
    }

    private void initTrailers() {
        /*trailerAdapter = new TrailerAdapter(getApplicationContext(), mediaTypeArrayList, (item, position, action) ->
        binding.trailerRecycler.setAdapter(trailerAdapter);*/
    }

    private void CastAdapter() {
        castsCrewAdapter = new CastCrewAdapter(getApplicationContext(), castArray, (item, position, action) ->
                Log.d("TrailerAdapterTag", "initTrailers: " + castArray.size()) );
        binding.castRecycler.setAdapter(castsCrewAdapter);
    }

    private void AppBarWithSeasonItems(SeriesItemResults itemResults) {
        // 1. Set Background - COLLAPSING Toolbar and title.
        initSeasonRecyclerAdapter(itemResults);
        binding.recyclerSeasons.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        String backDropPath = itemResults.getBackdropPath();
        if (backDropPath == null) {
            binding.backdropPath.setBackgroundResource(R.drawable.no_poster);
        } else {
            Glide.with(binding.backdropPath).load(URLs.IMAGE_BASE_URL + itemResults.getBackdropPath())
                    .into(binding.backdropPath);
        }

        binding.collapsingToolbar.setTitle(itemResults.getSeriesTitle());
        binding.seriesTitle.setText(itemResults.getSeriesTitle());
        binding.ratingBar.setRating((float)(itemResults.getVoteAverage() / 10)*5);

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

    private void initSeasonRecyclerAdapter(SeriesItemResults itemResults) {
        textAdapter = new SeasonTextAdapter(getApplicationContext(), seasons, (item, position, action) ->  {
            int series_id = itemResults.getSeriesId();
            seasonNo = item.getSeasonNumber();
            seasonCalls(series_id, seasonNo);
        });
        binding.recyclerSeasons.setAdapter(textAdapter);
    }

    private void seasonCalls(int series_id, int seasonNo) {
        Call<SeriesItemSeasonDetails> seasonCall = anInterface.SERIES_ITEM_SEASON_DETAILS_CALL(series_id, seasonNo, URLs.API_KEY);
        seasonCall.enqueue(new Callback<SeriesItemSeasonDetails>() {
            @Override
            public void onResponse(@NonNull Call<SeriesItemSeasonDetails> call, @NonNull Response<SeriesItemSeasonDetails> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    seasonResults = response.body();
                    SeasonDataImpl(seasonResults);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesItemSeasonDetails> call, @NonNull Throwable t) {
                Log.w("SeasonItemDetailsCall", "onFailure: "+call, t.fillInStackTrace());
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    private void SeasonDataImpl(SeriesItemSeasonDetails seasonResults) {
        if (seasonResults.getSeason_poster_path() == null){
            binding.seasonShortNote.seriesImages.setBackgroundResource(R.drawable.no_poster);
        } else {
            Glide.with(binding.seasonShortNote.seriesImages).load(URLs.IMAGE_BASE_URL + seasonResults.getSeason_poster_path())
                    .into(binding.seasonShortNote.seriesImages);
        }

        binding.seasonShortNote.seriesTitle.setText(seasonResults.getSeries_season_name());
        binding.seasonShortNote.seriesDescription.setText(seasonResults.getSeries_season_overview());

        inputDate = new SimpleDateFormat("yyyy-MM-dd");
        outputDate = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = inputDate.parse(seasonResults.getAir_date());
            if (date != null){
                binding.seasonShortNote.seriesDate.append(" "+ outputDate.format(date));
            }
        } catch (ParseException e) {
            throw new RuntimeException();
        }

        //Set LayoutManager, Adapter - RecyclerView
        binding.episodeRecyclerList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        episodeRecyclerAdapter();
    }

    private void episodeRecyclerAdapter() {
        episodeAdapter = new EpisodeAdapter(getApplicationContext(), seasonResults.getEpisodes(), (item, position, action) -> {
            Log.d("episodeAdapter", "episodeRecyclerAdapter: "+item);
        });
        binding.episodeRecyclerList.setAdapter(episodeAdapter);
    }
}