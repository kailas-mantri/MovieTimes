package com.samples.phoneverification.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samples.phoneverification.R;
import com.samples.phoneverification.databinding.FragmentMoviesBinding;

public class MoviesFragment extends Fragment {

    FragmentMoviesBinding binding;
    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(getLayoutInflater());


        return binding.getRoot();
    }
}