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
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.model.CastCrewArray;
import com.samples.phoneverification.model.CastPOJOModel;
import com.samples.phoneverification.model.MediaTypeArray;
import com.samples.phoneverification.model.MovieItemDetails;
import com.samples.phoneverification.model.MediaGroup;
import com.samples.phoneverification.model.MovieModel;
import com.samples.phoneverification.model.MovieResults;
import com.samples.phoneverification.model.SpokenLanguages;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.databinding.ActivityMovieDetailsBinding;

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

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    SimpleDateFormat inputDate, outputDate;
    private int movieId;
    private boolean isWishListed = false;
    TrailerAdapter trailerAdapter;
    private CastCrewAdapter castsCrewAdapter;
    private MovieAdapter recommendationAdapter;
    private ArrayList<MediaTypeArray> mediaTypeArrayList = new ArrayList<>();
    private final ArrayList<MediaTypeArray> filteredMedia = new ArrayList<>();
    private ArrayList<CastCrewArray> castArray = new ArrayList<>();
    private ArrayList<MovieResults> recommendedResults = new ArrayList<>();
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
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: Get Movie Id from last fragment.
        Intent intent = getIntent();
        
        movieId = (int) intent.getSerializableExtra("movie_id");
        binding.toolbarBack.setOnClickListener(v -> onBackPressed() );

        // TODO: Call API and set UI.
        Call<MovieItemDetails> details_call = anInterface.MOVIE_ITEM_DETAILS_CALL(movieId, URLs.API_KEY);
        details_call.enqueue(new Callback<MovieItemDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieItemDetails> call, @NonNull Response<MovieItemDetails> response) {
                if (response.isSuccessful() && (response.body() != null)) {
                    MovieItemDetails itemDetails = response.body();
                    AddDataToUI(itemDetails);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieItemDetails> call, @NonNull Throwable t) {
                Log.w("API Failing", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    private void AddDataToUI(MovieItemDetails itemDetails) {

        // Todo: 5. Release Date, 6. Spoken Languages, 8. Movie Description

        AppBarBlock(itemDetails);

        inputDate = new SimpleDateFormat("yyyy-MM-dd");
        outputDate = new SimpleDateFormat("dd MMMM, yyyy");
        try {
            Date date = inputDate.parse(itemDetails.getMovie_release_date());
            if (date != null) {
                binding.releaseOn.append(" " + outputDate.format(date));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // TODO: Set Multiple Languages
        for (SpokenLanguages language : itemDetails.getSpoken_language()) {
            langList.add(language.getLanguage_name());
        }
        String multiLang = TextUtils.join(", ", langList);

        binding.languageVersions.setText(multiLang);
        binding.movieDescription.setText(itemDetails.getMovieOverview());

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
        MediaTrailers();
        StarCast();
        recommendedMovies();
    }

    private void MediaTrailers() {
        Call<MediaGroup> mediaGroupCall = anInterface.MOVIE_MEDIA_GROUP_CALL(movieId, URLs.API_KEY);
        mediaGroupCall.enqueue(new Callback<MediaGroup>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<MediaGroup> call, @NonNull Response<MediaGroup> response) {
                if (response.body() != null && response.isSuccessful()) {
                    //TODO: Whole Response of API.
                    mediaTypeArrayList = response.body().getMediaList();

                    //TODO: Filtered Response for Trailer and Teaser.
                    for (MediaTypeArray array : mediaTypeArrayList) {
                        if (array.getMedia_type().equals("Trailer") || array.getMedia_type().equals("Teaser")) {
                            filteredMedia.add(array);
                        }
                    }
                    if (!filteredMedia.isEmpty()) {
                        trailerAdapter.updateData(filteredMedia);
                        trailerAdapter.notifyDataSetChanged();
                        Log.d("filteredMedia", "onResponse: " + filteredMedia.size());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MediaGroup> call, @NonNull Throwable t) {
                Log.w("TrailerResponse", "onFailure: " + call, t.fillInStackTrace());
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
                }

            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Log.w("Recommendation Call", "onFailure: " + call, t.fillInStackTrace());
            }
        });
    }

    private void StarCast() {
        Call<CastPOJOModel> castPOJOCall = anInterface.MOVIE_CAST_POJO_MODEL_CALL(movieId, URLs.API_KEY);
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
        /* 1. Trailer, Teaser Adapter*/
        initTrailers();

        /* 2. castAdapter*/
        CastAdapter();

        /* 3. RecommendedMoviesAdapters*/
        RecommendedMoviesAdapters();
    }

    private void RecommendedMoviesAdapters() {
        recommendationAdapter = new MovieAdapter(getApplicationContext(), recommendedResults, (item, position, action) -> {
            Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            intent.putExtra("movie_id", item.getMovieId());
            startActivity(intent);
        });
        binding.recommendationRecycler.setAdapter(recommendationAdapter);
    }

    private void CastAdapter() {
        castsCrewAdapter = new CastCrewAdapter(getApplicationContext(), castArray, (item, position, action) ->
            Log.d("TrailerAdapterTag", "initTrailers: " + castArray.size()) );
        binding.castRecycler.setAdapter(castsCrewAdapter);
    }

    private void initTrailers() {
        trailerAdapter = new TrailerAdapter(getApplicationContext(), mediaTypeArrayList, (item, position, action) ->
            Log.d("TrailerAdapterTag", "initTrailers: " + mediaTypeArrayList.size()) );
        binding.trailerRecycler.setAdapter(trailerAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void AppBarBlock(MovieItemDetails itemDetails) {

        binding.collapsingToolbar.setTitle(itemDetails.getStandardMovieTitle());

        /* 1. BackPath setup,
         * 2. Movie Title,
         * 3. Ratings,
         * 4. setEvent(onClick) on Add_to_wishlist */
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
