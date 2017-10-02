package com.example.mohamed.movieapp.ContantProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.mohamed.movieapp.DBshema.DbShema;
import com.example.mohamed.movieapp.DBshema.DbShema.TableFav;
import com.example.mohamed.movieapp.MovieDb.MovieDb;

import java.util.HashMap;

/**
 * Created by mohamed on 02/10/2017.
 */

public class MoviesProvider extends ContentProvider {
    private MovieDb dbHelper;
    private static final int ALL_MOVIES= 1;
    private static final int SINGLE_MOVIE= 2;
    private static final String AUTHORITY = "com.example.mohamed.movieapp.ContantProvider.contentprovider";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/movies");
    private static SQLiteDatabase mDatabase;
    private static HashMap<String, String> values;
    static final String id = "id";
    static final String title = "title";

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "movies", ALL_MOVIES);
        uriMatcher.addURI(AUTHORITY, "movies/#", SINGLE_MOVIE);
    }
    @Override
    public boolean onCreate() {
        dbHelper = new MovieDb(getContext());
        mDatabase=dbHelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TableFav.NAME);
        switch (uriMatcher.match(uri)) {
            case ALL_MOVIES:
                queryBuilder.setProjectionMap(values);
                break;
            case SINGLE_MOVIE:
                String id = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(TableFav.CLOS.ID+ " =" + id);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        Cursor cursor = queryBuilder.query(mDatabase, projection, selection,
                selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ALL_MOVIES:
                return "vnd.android.cursor.dir/vnd.com.example.mohamed.movieapp.ContantProvider.contentprovider.movies";
            case SINGLE_MOVIE:
                return "vnd.android.cursor.item/vnd.com.example.mohamed.movieapp.ContantProvider.contentprovider.movies";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case ALL_MOVIES:
                //do nothing
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        long id = mDatabase.insert(TableFav.NAME, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(CONTENT_URI + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case ALL_MOVIES:
                //do nothing
                break;
            case SINGLE_MOVIE:
//                String id = uri.getPathSegments().get(1);
//                selection = TableFav.CLOS.ID + "=" + id
//                        + (!TextUtils.isEmpty(selection) ?
//                        " AND (" + selection + ')' : "");
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int deleteCount =mDatabase.delete(TableFav.NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return deleteCount;
       // int deleteCount = mDatabase.delete(TableFav.NAME,TableFav.CLOS.ID+" = "+id, selectionArgs);

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
