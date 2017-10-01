package com.example.mohamed.movieapp.MovieDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mohamed.movieapp.DBshema.DbShema;
import com.example.mohamed.movieapp.DBshema.DbShema.TableFav;
import com.example.mohamed.movieapp.DBshema.DbShema.TableMOVIE;
import com.example.mohamed.movieapp.DBshema.DbShema.TableREVIEW;
import com.example.mohamed.movieapp.DBshema.DbShema.TableTrailer;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.model.Review;
import com.example.mohamed.movieapp.model.Trailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 02/08/2017.
 */

public class DbOperations {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DbOperations mOperations;

    public static DbOperations getmOperations(Context context){
        if (mOperations==null){
            mOperations=new DbOperations(context);
        }
        return mOperations;
    }

    private DbOperations(Context context){
        mContext=context.getApplicationContext();
        try {
            mDatabase=new MovieDb(mContext).getWritableDatabase();

        }catch (Exception  e){
            mDatabase=new MovieDb(mContext).getReadableDatabase();

        }
    }

    public String Delete(String name){
        int delete = mDatabase.delete(name, null, null);
        return delete>0?"Deleted ": "error in delete ";
    }

    public List<Movie> getFavMovies(){
        List<Movie> movies=new ArrayList<>();
        MovieWrapper wrapper=queryMovies(TableFav.NAME,null,null);

        try {
            wrapper.moveToFirst();
            while (!wrapper.isAfterLast()){
                movies.add(wrapper.getMovie());
                wrapper.moveToNext();
            }
        }finally {
            wrapper.close();
        }
        return movies;

    }
    public List<Movie> getMovies(){
      List<Movie> movies=new ArrayList<>();
        MovieWrapper wrapper=queryMovies(TableMOVIE.NAME,null,null);

        try {
            wrapper.moveToFirst();
            while (!wrapper.isAfterLast()){
                  movies.add(wrapper.getMovie());
                wrapper.moveToNext();
            }
        }finally {
            wrapper.close();
        }
        return movies;

    }

    public List<Review> getReview(String id){
        List<Review> reviews=new ArrayList<>();

        MovieWrapper wrapper=queryMovies(TableREVIEW.NAME, TableREVIEW.CLOS.ID+" =?",new String[]{id});

        try {
            wrapper.moveToFirst();
            while (!wrapper.isAfterLast()){
                reviews.add(wrapper.getReview());
                wrapper.moveToNext();
            }
        }finally {
            wrapper.close();
        }
        return reviews;

    }
    public List<Trailer> getTrailer(String id){
        List<Trailer> trailers=new ArrayList<>();
        MovieWrapper wrapper=queryMovies(TableTrailer.NAME, TableTrailer.CLOS.ID+" =?",new String[]{id});

        try {
            wrapper.moveToFirst();
            while (!wrapper.isAfterLast()){
                trailers.add(wrapper.getTrailer());
                wrapper.moveToNext();
            }
        }finally {
        wrapper.close();
        }
       return trailers;
    }

     public boolean deleteFromFav(int id){
         int delete = mDatabase.delete(TableFav.NAME, TableFav.CLOS.ID+" = "+ id, null);
         return delete>0?true:false;
     }

    public boolean checkFavMovie(int id){
        MovieWrapper wrapper=queryMovies(TableFav.NAME, TableFav.CLOS.ID+" = "+ id,null);
        try {
            if (wrapper.getCount()==0){
                return false;
            }
            wrapper.moveToFirst();
            return true;
        }finally {
            wrapper.close();
        }
    }

    public String InsertMovie(Movie movie){
        Long aLong=null;
        try {
            ContentValues values=getMovie(movie);
           aLong= mDatabase.insert(TableMOVIE.NAME,null,values);

        }catch (Exception e){
        }

        return  aLong>0?"insert done ":" error ";
    }


    public String InsertMovieReview(Review review,String id){
        ContentValues values=getMoviereview(review,id);
       Long aLong= mDatabase.insert(TableREVIEW.NAME,null,values);
        return  aLong>0?"insert done review ":" error ";
    }
        public String InsertMovieTrailer(Trailer trailer,String id){
        ContentValues values=getMovieTrailer(trailer,id);
       Long aLong= mDatabase.insert(TableTrailer.NAME,null,values);
        return  aLong>0?"insert done review ":" error ";
    }

    public boolean InsertFavMovie(Movie movie){
        ContentValues values=getMovieFav(movie);
        long insert = mDatabase.insert(TableFav.NAME, null, values);

     return insert>0?true:false;
    }

    private ContentValues getMovie(Movie movie){
        ContentValues values=new ContentValues();

        values.put(TableMOVIE.CLOS.ID,movie.getId());
        values.put(TableMOVIE.CLOS.POSTER,movie.getPosterPath());
        values.put(TableMOVIE.CLOS.RATE,movie.getVoteCount());
         values.put(TableMOVIE.CLOS.TITLE,movie.getTitle());
        values.put(TableMOVIE.CLOS.RELEASE_DATE,movie.getRelease_date());

        return values;
    }




       private ContentValues getMovieFav(Movie movie){
        ContentValues values=new ContentValues();

         values.put(TableFav.CLOS.ID,movie.getId());
         values.put(TableFav.CLOS.POSTER,movie.getPosterPath());
         values.put(TableFav.CLOS.RATE,movie.getVoteCount());
         values.put(TableFav.CLOS.TITLE,movie.getTitle());
           values.put(TableFav.CLOS.RELEASE_DATE,movie.getRelease_date());


           return values;
    }

    private MovieWrapper queryMovies(String NAME, String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new MovieWrapper(cursor);
    }
    private ContentValues getMoviereview( Review review,String movieId){
        ContentValues values=new ContentValues();
        values.put(TableREVIEW.CLOS.ID,movieId);
         values.put(TableREVIEW.CLOS.AUTHOR,review.getAuthor());
         values.put(TableREVIEW.CLOS.CONTENT,review.getContent());

        return values;
    }
    private ContentValues getMovieTrailer(Trailer trailer, String movieId){
        ContentValues values=new ContentValues();
        values.put(TableTrailer.CLOS.ID,movieId);
         values.put(TableTrailer.CLOS.KEY,trailer.getKey());
         values.put(TableTrailer.CLOS.NAME,trailer.getName());

        return values;
    }




}
