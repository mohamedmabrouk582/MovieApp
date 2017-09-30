package com.example.mohamed.movieapp.ContantProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.mohamed.movieapp.DBshema.DbShema;
import com.example.mohamed.movieapp.DBshema.DbShema.TableFav;
import com.example.mohamed.movieapp.DBshema.DbShema.TableMOVIE;
import com.example.mohamed.movieapp.MovieDb.MovieDb;

import java.util.HashMap;

/**
 * Created by mohamed on 30/09/2017.
 */

public class MovieProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.mohamed.movieapp.ContantProvider.MovieProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/cpcontacts";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    static final String id = "id";
    static final String name = "name";
    static final int uriCode = 1;

    private static HashMap<String, String> values;
    private SQLiteDatabase mDatabase;
    private MovieDb movieDb;
    static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cpcontacts", uriCode);
    }



    public void OpenDB(){
        try {
            mDatabase=movieDb.getWritableDatabase();
        }catch (Exception e){
            mDatabase=movieDb.getReadableDatabase();

        }
    }
    @Override
    public boolean onCreate() {
        movieDb=new MovieDb(getContext());
        OpenDB();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {


        SQLiteQueryBuilder queryBuilder=new SQLiteQueryBuilder();
        queryBuilder.setTables(TableFav.NAME);
    UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        switch (uriMatcher.match(uri)){
            case uriCode:
             queryBuilder.setProjectionMap(values);
                break;
         default:
             throw new IllegalArgumentException("unknown uri " + uri);

        }
        Cursor cursor = queryBuilder.query(mDatabase, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {

            case uriCode:
                return "vnd.android.cursor.dir/cpcontact";
            default:
                throw new IllegalArgumentException("un supporrt uri  " + uri);

        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID = mDatabase.insert(TableFav.NAME, null, values);

        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        } else {
            Toast.makeText(getContext(), "insert row failed", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowDeleted = 0;
        switch (uriMatcher.match(uri)) {

            case uriCode:
                rowDeleted = mDatabase.delete(TableFav.NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("un supporrt uri  " + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowDeleted;     }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
