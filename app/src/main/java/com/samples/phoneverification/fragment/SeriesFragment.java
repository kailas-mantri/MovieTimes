package com.samples.phoneverification.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samples.phoneverification.R;
import com.samples.phoneverification.databinding.FragmentSeriesBinding;

public class SeriesFragment extends Fragment {

    FragmentSeriesBinding binding;
    // TODO: the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public SeriesFragment() {
        // Required empty public constructor
    }

    /* TODO: Rename and change types and number of parameters
    public static SeriesFragment newInstance(String param1, String param2) {
        SeriesFragment fragment = new SeriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
     } */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        } */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSeriesBinding.inflate(getLayoutInflater());


        return binding.getRoot();
    }
}