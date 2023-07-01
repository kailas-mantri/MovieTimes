package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GenreModel implements Serializable {

    @SerializedName("genres")
    public ArrayList<GenreResults> genresArray;

    public GenreModel() {
    }

    public GenreModel(ArrayList<GenreResults> genresArray) {
        this.genresArray = genresArray;
    }

    public ArrayList<GenreResults> getGenresArray() {
        return genresArray;
    }

    public void setGenresArray(ArrayList<GenreResults> genresArray) {
        this.genresArray = genresArray;
    }

    @NonNull
    @Override
    public String toString() {
        return "GenreModel{" +
                "genresArray=" + genresArray +
                '}';
    }
}
