package com.samples.phoneverification.dbmodel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class WishListDBHelper extends SQLiteOpenHelper {

    final Context context;
    private static final String DB_NAME = "wish_list.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "wish_list";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ITEM_ID = "itemId";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_POSTER_URL = "posterUrl";
    private static final String COLUMN_OVERVIEW = "overView";
    private static final String COLUMN_BACKDROP_URL = "backdropUrl";
    private static final String COLUMN_RELEASE_DATE = "releaseDate";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ITEM_ID + " INTEGER NOT NULL, "+
            COLUMN_TITLE + " TEXT, " +
            COLUMN_OVERVIEW + " TEXT, " +
            COLUMN_POSTER_URL + " TEXT, " +
            COLUMN_BACKDROP_URL + " TEXT, " +
            COLUMN_RELEASE_DATE + " TEXT " + ");";

    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public WishListDBHelper(@NonNull Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    public void addToWishList(WishListItem item) {
//        Log.d("Wishlist", "Adding to wishlist: " + item.getItem_title());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BACKDROP_URL, item.getItem_backdropPath());
        values.put(COLUMN_ITEM_ID, item.getItem_id());
        values.put(COLUMN_TITLE, item.getItem_title());
        values.put(COLUMN_OVERVIEW, item.getItem_overview());
        values.put(COLUMN_POSTER_URL, item.getItem_posterPath());
        values.put(COLUMN_RELEASE_DATE, item.getItem_release_date());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeFromWishList(WishListItem item) {
//        Log.d("Wishlist", "Remove from wishlist: " + item.getItem_title());
        SQLiteDatabase db = this.getWritableDatabase();

        String WHERE_CLAUSE = COLUMN_TITLE + "=?";
        String[] whereArgs = {item.getItem_title()};

        db.delete(TABLE_NAME, WHERE_CLAUSE, whereArgs);
        db.close();
    }

    public boolean isItemWishListed(int itemId) {
//        Log.d("Wishlist", "is Item wishListed: " + itemId);
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.query(TABLE_NAME, new String[]{"itemId"}, "itemId=?", new String[]{String.valueOf(itemId)},
                null, null, null, null);
        boolean itemExists = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return itemExists;
    }

    public ArrayList<WishListItem> getAllWishListItems() {
        ArrayList<WishListItem> wishList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ITEM_ID, COLUMN_TITLE, COLUMN_OVERVIEW, COLUMN_POSTER_URL, COLUMN_BACKDROP_URL, COLUMN_RELEASE_DATE};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int itemId = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                @SuppressLint("Range") String overview = cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW));
                @SuppressLint("Range") String posterUrl = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_URL));
                @SuppressLint("Range") String  backdropUrl = cursor.getString(cursor.getColumnIndex(COLUMN_BACKDROP_URL));
                @SuppressLint("Range") String releaseDate = cursor.getString(cursor.getColumnIndex(COLUMN_RELEASE_DATE));
                WishListItem item = new WishListItem(itemId, title, overview, posterUrl, backdropUrl, releaseDate);
                wishList.add(item);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return wishList;
    }
}
