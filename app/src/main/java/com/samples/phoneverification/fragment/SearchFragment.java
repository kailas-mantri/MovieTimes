package com.samples.phoneverification.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

import com.samples.phoneverification.activity.DemoActivity;
import com.samples.phoneverification.adapter.RecentSearchAdapter;
import com.samples.phoneverification.adapter.RecyclerSearchAdapter;
import com.samples.phoneverification.apimodel.APIInterface;
import com.samples.phoneverification.apimodel.SearchApiModel;
import com.samples.phoneverification.apimodel.SearchApiResults;
import com.samples.phoneverification.apimodel.URLs;
import com.samples.phoneverification.databinding.FragmentSearchBinding;
import com.samples.phoneverification.datamodel.SearchDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;
    ArrayList<SearchApiResults> searchResults = new ArrayList<>();
    private final LinkedList<String> recentSearch = new LinkedList<>();
    private final LinkedList<String> sHistoryRecordBook = new LinkedList<>();
    private RecentSearchAdapter recentSearchAdapter;
    private RecyclerSearchAdapter searchOutputAdapter;
    private SearchDBHelper searchDBHelper;
    private Retrofit retrofit;
    private APIInterface anInterface;
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)
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

        //TODO: searchView editor on query.
        binding.searchEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!sHistoryRecordBook.contains(query)) {
                    saveRecentSearch(query);
                }
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                updateSuggestions(newText);
                return true;
            }
        });

        binding.recentSearchLayout.clearAll.setOnClickListener(v -> clearRecentSearch());

        // Load recent search records from storage or database
        loadRecentSearchRecords();

        System.out.println(recentSearch);
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadRecentSearchRecords() {
        // TODO: Hide the search movies/series recyclerview.
        binding.recentSearchLayout.beforeSearchRecyclerView.setVisibility(View.VISIBLE);
        binding.afterSearchRecyclerView.setVisibility(View.GONE);

        // TODO: Set LayoutManger, Adapter
        binding.recentSearchLayout.beforeSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recentSearchAdapter = new RecentSearchAdapter(getContext(), recentSearch);
        binding.recentSearchLayout.beforeSearchRecyclerView.setAdapter(recentSearchAdapter);

        List<String> searchedQueries = searchDBHelper.getAllSearchQueries();
        recentSearch.clear();
        recentSearch.addAll(searchedQueries);
        sHistoryRecordBook.clear();
        sHistoryRecordBook.addAll(recentSearch);
        recentSearchAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clearRecentSearch() {
        // Clear Search record.
        recentSearch.clear();
        recentSearchAdapter.notifyDataSetChanged();
        searchDBHelper.clearSearchHistory();
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

//        List<String> suggestions = fetchSuggestionsFromDatabase(newText);
//        recentSearchAdapter.setData(suggestions);
//        recentSearchAdapter.notifyDataSetChanged();
    }

//    private List<String> fetchSuggestionsFromDatabase(String newText) {
//        List<String> suggestions = new ArrayList<>();
//        String query = "SELECT DISTINCT "+ searchDBHelper.COLUMN_QUERY +" FROM "+ SearchDBHelper.TABLE_NAME +" WHERE " +
//                searchDBHelper.COLUMN_QUERY +" LIKE '%" + newText + "%'";
//        SQLiteDatabase database = searchDBHelper.getReadableDatabase();
//        Cursor cursor = database.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                String suggestion = cursor.getString(cursor.getColumnIndex("COLUMN_QUERY"));
//                suggestions.add(suggestion);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return suggestions;
//    }

    private void performSearch(String query) {
        // Perform search operation.
        searchDBHelper.addSearchQuery(query);
        binding.recentSearchLayout.beforeSearchRecyclerView.setVisibility(View.GONE);
        binding.recentSearchLayout.recentSearchText.setVisibility(View.GONE);
        binding.recentSearchLayout.clearAll.setVisibility(View.GONE);
        binding.afterSearchRecyclerView.setVisibility(View.VISIBLE);

        // TODO: Set layout for recycler search view
        binding.afterSearchRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //TODO: set adapter for recycler output view
        searchResultAdapter();

        //TODO: search result API Call
        showSearchResult(query);
    }

    private void searchResultAdapter() {
        searchOutputAdapter = new RecyclerSearchAdapter(requireContext(), searchResults, position -> {
            startActivity(new Intent(getContext(), DemoActivity.class));
        });
        binding.afterSearchRecyclerView.setAdapter(searchOutputAdapter);
    }

    private void showSearchResult(String query) {
        HashMap<String, String> params = new HashMap<>();
        params.put("api_key", URLs.API_KEY);
        params.put("query", query);

        Call<SearchApiModel> modelCall = anInterface.SEARCH_MODEL_CALL(params);
        modelCall.enqueue(new Callback<SearchApiModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SearchApiModel> call, @NonNull Response<SearchApiModel> response) {
                if ((response.isSuccessful()) && (response.body() != null)) {
                    searchResults.clear();
                    searchResults = response.body().getSearchResults();
                    searchOutputAdapter.updateData(searchResults);
                    searchOutputAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchApiModel> call, @NonNull Throwable t) {
                Log.w(getTag(), "onFailure: " + t.getMessage());
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void saveRecentSearch(String query) {
        // check list has query, if it is update
        if (!recentSearch.contains(query)) {
            recentSearch.addLast(query);
            sHistoryRecordBook.addLast(recentSearch.pollLast());
        } else {
            recentSearch.remove(query);
        }
        recentSearchAdapter.notifyDataSetChanged();
    }
}