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
    private static final int DB_version= 1;
    public static final String TABLE_NAME="search_query";
    private static final String COLUMN_ID="id";
    public static final String COLUMN_QUERY="query";

    public SearchDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUERY + "TEXT" + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
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
