package com.example.mohamed.movieapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.movieapp.MovieDb.DbOperations;
import com.example.mohamed.movieapp.R;
import com.example.mohamed.movieapp.adapter.ReviewsAdpter;
import com.example.mohamed.movieapp.adapter.TrailerAdapter;
import com.example.mohamed.movieapp.api.Requests;
import com.example.mohamed.movieapp.model.Movie;
import com.example.mohamed.movieapp.model.Review;
import com.example.mohamed.movieapp.model.Trailer;
import com.example.mohamed.movieapp.presenter.MovieDetailViewPresenter;
import com.example.mohamed.movieapp.view.DetailView;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mohamed on 20/09/2017.
 */

public class MovieDetailFragment extends Fragment implements DetailView {
    private Movie movie;
    private RecyclerView mTarilerRecyclerView;
    private RecyclerView mReviewsRecyclerView;
    private KenBurnsView moviePoster;
    private RatingBar movieRatingBar;
    private TextView release_date;
    private static String MOVIE="MOVIE";
    private Button mAddToFav;
    private String urlbase="http://image.tmdb.org/t/p/w185";
    private ReviewsAdpter reviewsAdpter;
    private TrailerAdapter trailerAdapter;
    private View view;
    private TextView title;
    private boolean flag=false;
    private MovieDetailViewPresenter movieDetailViewPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setRetainInstance(true);
    }



    public static MovieDetailFragment newFragment(Movie movie){
        Bundle bundle=new Bundle();
        bundle.putParcelable(MOVIE,movie);
        MovieDetailFragment fragment=new MovieDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        movie= (Movie) getArguments().getParcelable(MOVIE);
         flag= DbOperations.getmOperations(getActivity()).checkFavMovie(movie.getId());
            view=inflater.inflate(R.layout.movie_item_detail,container,false);
            initViews();
            initTrailerRecyler();
            initReviewRecyler();
            movieDetailViewPresenter=new MovieDetailViewPresenter(this,new Requests(getActivity()),movie);
            Picasso.with(getActivity()).load(Uri.parse(urlbase+movie.getPosterPath())).into(moviePoster);
            if (!flag) {
                mAddToFav.setText(getString(R.string.add_favourite)
                );
            } else {
                mAddToFav.setText("un Favourite");
            }
          action();
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        movieDetailViewPresenter.LoadMovieDetail();
    }



    @Override
    public void ShowMovieDetail(Movie movie) {

    }

    @Override
    public void ShowMovieReviews(List<Review> reviews) {
        reviewsAdpter.replacData(reviews);
    }

    @Override
    public void ShowMovieTrailers(List<Trailer> list) {
    trailerAdapter.replaceData(list);
    }

    @Override
    public void AddToFavourate(final Movie movie) {
    }


    private void action(){
        mAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (flag){
                 DbOperations.getmOperations(getActivity()).deleteFromFav(movie.getId());
                 mAddToFav.setText(getString(R.string.add_favourite));
                 flag=false;
             }else {
                 DbOperations.getmOperations(getActivity()).InsertFavMovie(movie);
                 mAddToFav.setText("Un Favourite");
                 flag=true;
             }
            }
        });
    }

    private void initViews(){
        moviePoster = (KenBurnsView) view.findViewById(R.id.movie_image_view);
        movieRatingBar = (RatingBar) view.findViewById(R.id.movie_rate);
        mAddToFav= (Button) view.findViewById(R.id.add_to_favourite);
        release_date= (TextView) view.findViewById(R.id.release_date);
        title= (TextView) view.findViewById(R.id.movie_title);
        release_date.setText(movie.getRelease_date());
        title.setText(movie.getTitle());
        movieRatingBar.setRating(movie.getVoteCount()/2);
    }
    private void initTrailerRecyler(){
        trailerAdapter=new TrailerAdapter(getActivity());
        mTarilerRecyclerView = (RecyclerView) view.findViewById(R.id.trailer_recycler_view);
        mTarilerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        mTarilerRecyclerView.setAdapter(trailerAdapter);
    }

    private void initReviewRecyler(){
        reviewsAdpter=new ReviewsAdpter(getActivity());
        mReviewsRecyclerView = (RecyclerView) view.findViewById(R.id.review_recycler_view);
        mReviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReviewsRecyclerView.setAdapter(reviewsAdpter);
    }
}
