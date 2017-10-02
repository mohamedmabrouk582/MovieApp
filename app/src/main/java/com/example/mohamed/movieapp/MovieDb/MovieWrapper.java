package com.example.mohamed.movieapp.MovieDb;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.mohamed.movieapp.DBshema.DbShema;
import com.example.mohamed.movieapp.DBshema.DbShema.TableMOVIE;
import com.example.mohamed.movieapp.DBshema.DbShema.TableREVIEW;
import com.example.mohamed.movieapp.DBshema.DbShema.TableTrailer;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.model.Review;
import com.example.mohamed.movieapp.model.Trailer;


/**
 * Created by mohamed on 5/18/2017.
 */

public class MovieWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public MovieWrapper(Cursor cursor) {
        super(cursor);
    }

    public Movie getMovie(){

        Integer id=getInt(getColumnIndex(TableMOVIE.CLOS.ID));
        String title=getString(getColumnIndex(TableMOVIE.CLOS.TITLE));
        String poster=getString(getColumnIndex(TableMOVIE.CLOS.POSTER));
        Integer rate=getInt(getColumnIndex(TableMOVIE.CLOS.RATE));
        String release_date=getString(getColumnIndex(TableMOVIE.CLOS.RELEASE_DATE));
        Movie movie =new Movie(title,poster,id,rate,release_date);
       // movie.setRelease_date(release_date);
        return movie;
    }

    public Review getReview(){
          String author=getString(getColumnIndex(TableREVIEW.CLOS.AUTHOR));
          String content=getString(getColumnIndex(TableREVIEW.CLOS.CONTENT));
          String id=getString(getColumnIndex(TableREVIEW.CLOS.ID));
          Review review =new Review(author,content,id);

        return review;
    }
    public Trailer getTrailer(){
          String key=getString(getColumnIndex(TableTrailer.CLOS.KEY));
          String name=getString(getColumnIndex(TableTrailer.CLOS.NAME));
          String id=getString(getColumnIndex(TableTrailer.CLOS.ID));
        Trailer trailer =new Trailer(key,name,id);

        return trailer;
    }



}
