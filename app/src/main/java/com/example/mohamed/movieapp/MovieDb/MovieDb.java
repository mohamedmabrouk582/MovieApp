package com.example.mohamed.movieapp.MovieDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mohamed.movieapp.DBshema.DbShema;
import com.example.mohamed.movieapp.DBshema.DbShema.TableFav;
import com.example.mohamed.movieapp.DBshema.DbShema.TableMOVIE;
import com.example.mohamed.movieapp.DBshema.DbShema.TableREVIEW;
import com.example.mohamed.movieapp.DBshema.DbShema.TableTrailer;

/**
 * Created by mohamed on 02/08/2017.
 */

public class MovieDb extends SQLiteOpenHelper {
    private static final String DBNAME="movieApp.db";
    private static final int VERSION = 1;

    public MovieDb(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /************ create Movie Table ************/
        db.execSQL("create table "+ TableMOVIE.NAME+ "(" +
                " _id integer primary key autoincrement, " +
                TableMOVIE.CLOS.ID + " UNIQUE, " +
                TableMOVIE.CLOS.POSTER + ", " +
                TableMOVIE.CLOS.RATE + ", " +
                TableMOVIE.CLOS.TITLE +
                ")");
        /************ create  fav Movie Table ************/

        db.execSQL("create table "+ TableFav.NAME+ "(" +
                " _id integer primary key autoincrement, " +
                TableFav.CLOS.ID + " UNIQUE, " +
                TableFav.CLOS.POSTER + ", " +
                TableFav.CLOS.RATE + ", " +
                TableFav.CLOS.TITLE +
                ")");
        /************ create review Movie Table ************/

        db.execSQL("create table "+ TableREVIEW.NAME+ "(" +
                " _id integer primary key autoincrement, " +
                TableREVIEW.CLOS.ID + ", " +
                TableREVIEW.CLOS.AUTHOR + " , " +
                TableREVIEW.CLOS.CONTENT +
                " UNIQUE)");

        /************ create review Movie Table ************/

        db.execSQL("create table "+ TableTrailer.NAME+ "(" +
                " _id integer primary key autoincrement, " +
                TableTrailer.CLOS.ID + ", " +
                TableTrailer.CLOS.KEY + ", " +
                TableTrailer.CLOS.NAME +
                "UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
