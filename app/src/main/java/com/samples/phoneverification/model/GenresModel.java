package com.samples.phoneverification.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GenresModel implements Serializable {

    @SerializedName("genres")
    public ArrayList<GenresList> genresArray;

    public GenresModel() {
    }

    public GenresModel(ArrayList<GenresList> genresArray) {
        this.genresArray = genresArray;
    }

    public ArrayList<GenresList> getGenresArray() {
        return genresArray;
    }

    public void setGenresArray(ArrayList<GenresList> genresArray) {
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
