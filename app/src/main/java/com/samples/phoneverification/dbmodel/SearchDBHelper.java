package com.samples.phoneverification.dbmodel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SearchDBHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DB_NAME="search_history.db";
    private static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "search_query";
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_QUERY = "searched_query";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_QUERY + " TEXT" + ")";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public SearchDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion)
            db.execSQL(DROP_TABLE_QUERY);

        onCreate(db);
    }

    public void addSearchQuery(String query) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUERY, query);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void clearSearchHistory() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public List<String> getAllSearchQueries() {
        List<String> suggestions = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_QUERY}, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                String query = cursor.getString(cursor.getColumnIndex(COLUMN_QUERY));
                suggestions.add(query);
            }
            cursor.close();
        }
        db.close();
        return suggestions;
    }

    public Context getContext() {
        return context;
    }
}
