package com.samples.phoneverification.dbmodel;

public class WishListItem {

    private int isMovie;
    private int item_id;
    private String item_title;
    private String item_overview;
    private String item_posterPath;
    private String item_backdropPath;
    private String item_release_date;

    public WishListItem(int isMovie, int item_id, String item_title, String item_overview, String item_posterPath, String item_backdropPath, String item_release_date) {
        this.isMovie= isMovie;
        this.item_id = item_id;
        this.item_title = item_title;
        this.item_overview = item_overview;
        this.item_posterPath = item_posterPath;
        this.item_backdropPath = item_backdropPath;
        this.item_release_date = item_release_date;
    }

    public int isMovie() {
        return isMovie;
    }

    public void setMovie(int movie) {
        isMovie = movie;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_overview() {
        return item_overview;
    }

    public void setItem_overview(String item_overview) {
        this.item_overview = item_overview;
    }

    public String getItem_posterPath() {
        return item_posterPath;
    }

    public void setItem_posterPath(String item_posterPath) {
        this.item_posterPath = item_posterPath;
    }

    public String getItem_backdropPath() {
        return item_backdropPath;
    }

    public void setItem_backdropPath(String item_backdropPath) {
        this.item_backdropPath = item_backdropPath;
    }

    public String getItem_release_date() {
        return item_release_date;
    }

    public void setItem_release_date(String item_release_date) {
        this.item_release_date = item_release_date;
    }
}
