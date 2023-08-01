package com.samples.phoneverification.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.samples.phoneverification.BuildConfig;
import com.samples.phoneverification.activity.MovieDetailsActivity;
import com.samples.phoneverification.activity.SeriesDetailsActivity;
import com.samples.phoneverification.adapters.RecentSearchAdapter;
import com.samples.phoneverification.adapters.RecyclerSearchAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.databinding.FragmentSearchBinding;
import com.samples.phoneverification.dbmodel.SearchDBHelper;
import com.samples.phoneverification.model.SearchModel;
import com.samples.phoneverification.model.SearchResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SearchFragment extends Fragment {

    private Retrofit retrofit;
    private APIInterface anInterface;
    private FragmentSearchBinding binding;
    private SearchDBHelper searchDBHelper;
    private boolean isQuerySubmitted = false;
    private RecentSearchAdapter recentSearchAdapter;
    private RecyclerSearchAdapter searchOutputAdapter;
    private ArrayList<SearchResults> searchResults = new ArrayList<>();

    /*ArrayList<SearchResults> filteredMovieList = new ArrayList<>();
    ArrayList<SearchResults> FilteredSeriesList = new ArrayList<>();*/
    private final LinkedList<String> recentSearch = new LinkedList<>();
    private final LinkedList<String> sHistoryRecordBook = new LinkedList<>();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        anInterface = retrofit.create(APIInterface.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        searchDBHelper = new SearchDBHelper(getContext());

        //TODO: set Visibility for recyclerView.
        binding.recentSearchLayout.beforeSearchRecyclerView.setVisibility(View.VISIBLE);
        binding.afterSearchRecyclerView.setVisibility(View.GONE);


        // TODO: Set LayoutManger, Adapter
        binding.recentSearchLayout.beforeSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recentSearchAdapter = new RecentSearchAdapter(getContext(), recentSearch);
        binding.recentSearchLayout.beforeSearchRecyclerView.setAdapter(recentSearchAdapter);


        //TODO: searchView editor on query.
        binding.searchEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!sHistoryRecordBook.contains(query)) {
                    saveRecentSearch(query);
                }
                loadSearchResults(query);
                isQuerySubmitted = true;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!isQuerySubmitted && newText.isEmpty())
                    hideRecentSearchRecords();
                else
                    showRecentSearchRecords();
                return true;
            }
        });

        binding.searchEditText.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                loadRecentSearchRecords();
                return false;
            }
        });

        binding.recentSearchLayout.clearAll.setOnClickListener(v -> clearRecentSearch());

        loadRecentSearchRecords();
        System.out.println(recentSearch+ " ---- " + sHistoryRecordBook);
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadRecentSearchRecords() {
        // TODO: Hide the search movies/series recyclerview.
        binding.afterSearchRecyclerView.setVisibility(View.GONE);
        binding.recentSearchLayout.beforeSearchRecyclerView.setVisibility(View.VISIBLE);
        binding.recentSearchLayout.recentSearchText.setVisibility(View.VISIBLE);
        binding.recentSearchLayout.clearAll.setVisibility(View.VISIBLE);

        List<String> searchedQueries = searchDBHelper.getAllSearchQueries();
        Collections.reverse(searchedQueries);

        recentSearch.clear();
        sHistoryRecordBook.clear();
        recentSearch.addAll(searchedQueries);
        recentSearchAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clearRecentSearch() {
        // Clear Search record.
        searchDBHelper.clearSearchHistory();
        recentSearch.clear();
        sHistoryRecordBook.clear();
        if (recentSearchAdapter != null) {
            recentSearchAdapter.setRecentSearchData(new ArrayList<>());
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateSuggestions(String newText) {
        List<String> list = searchDBHelper.getAllSearchQueries();
        boolean containsMatch = false;

        for (String query : recentSearch) {
            if (query.contains(newText)) {
                containsMatch = true;
                break;
            }
        }
        if (containsMatch) {
            saveRecentSearch(newText);
        }
    }

  /*      List<String> suggestions = fetchSuggestionsFromDatabase(newText);
        recentSearchAdapter.setData(suggestions);
        recentSearchAdapter.notifyDataSetChanged();

    private List<String> fetchSuggestionsFromDatabase(String newText) {
        List<String> suggestions = new ArrayList<>();
        String query = "SELECT DISTINCT "+ searchDBHelper.COLUMN_QUERY +" FROM "+ SearchDBHelper.TABLE_NAME +" WHERE " +
                searchDBHelper.COLUMN_QUERY +" LIKE '%" + newText + "%'";
        SQLiteDatabase database = searchDBHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String suggestion = cursor.getString(cursor.getColumnIndex("COLUMN_QUERY"));
                suggestions.add(suggestion);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return suggestions;
    }*/

    private void loadSearchResults(String query) {
        // Perform search operation.
        searchDBHelper.addSearchQuery(query);
        binding.recentSearchLayout.beforeSearchRecyclerView.setVisibility(View.GONE);
        binding.recentSearchLayout.recentSearchText.setVisibility(View.GONE);
        binding.recentSearchLayout.clearAll.setVisibility(View.GONE);
        binding.afterSearchRecyclerView.setVisibility(View.VISIBLE);

        // TODO: set layoutManager, setAdapter, callback API - RecyclerSearchView.
        binding.afterSearchRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        searchResultAdapter();
        showSearchCallback(query);
    }

    private void searchResultAdapter() {
        searchOutputAdapter = new RecyclerSearchAdapter(requireContext(), searchResults, (item, position, action) -> {
            if (item.getMediaType().equalsIgnoreCase("movie")) {
                Intent i = new Intent(getContext(), MovieDetailsActivity.class);
                i.putExtra("movie_id", searchResults.get(position).getItemId());
                startActivity(i);
            } else if (item.getMediaType().equalsIgnoreCase("tv")) {
                Intent i = new Intent(getContext(), SeriesDetailsActivity.class);
                i.putExtra("series_id", searchResults.get(position).getItemId());
                startActivity(i);
            }
        });
        binding.afterSearchRecyclerView.setAdapter(searchOutputAdapter);
    }

    private void showSearchCallback(String query) {
        HashMap<String, String> params = new HashMap<>();
        params.put("api_key", BuildConfig.API_KEY);
        params.put("query", query);

        Call<SearchModel> modelCall = anInterface.SEARCH_MODEL_CALL(params);
        modelCall.enqueue(new Callback<SearchModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SearchModel> call, @NonNull Response<SearchModel> response) {
                if ((response.isSuccessful()) && (response.body() != null)) {
                    searchResults.clear();
                    searchResults.addAll(response.body().getSearchResults());
                    searchOutputAdapter.notifyDataSetChanged();
                    Log.d(getTag(), "onResponse: " + searchResults);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchModel> call, @NonNull Throwable t) {
                Log.w(getTag(), "onFailure: " + t.getMessage());
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void saveRecentSearch(String query) {
        // check list has query, if it is update
        if (recentSearch.contains(query) && sHistoryRecordBook.contains(query)) {
            recentSearch.remove(query);
            sHistoryRecordBook.remove(query);
        }
        recentSearch.addFirst(query);
        sHistoryRecordBook.addFirst(query);
        Log.d("TAG", "saveRecentSearch - recentSearch: "+ recentSearch);
        Log.w("TAG", "saveRecentSearch - sHistoryRecordBook: "+ sHistoryRecordBook);
        recentSearchAdapter.notifyDataSetChanged();
    }

    private void hideRecentSearchRecords() {
        binding.recentSearchLayout.beforeSearchRecyclerView.setVisibility(View.GONE);
        binding.recentSearchLayout.recentSearchText.setVisibility(View.GONE);
        binding.recentSearchLayout.clearAll.setVisibility(View.GONE);
        binding.afterSearchRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showRecentSearchRecords() {
        binding.recentSearchLayout.beforeSearchRecyclerView.setVisibility(View.VISIBLE);
        binding.recentSearchLayout.recentSearchText.setVisibility(View.VISIBLE);
        binding.recentSearchLayout.clearAll.setVisibility(View.VISIBLE);
        binding.afterSearchRecyclerView.setVisibility(View.GONE);
    }
}