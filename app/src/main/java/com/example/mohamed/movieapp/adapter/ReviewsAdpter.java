package com.example.mohamed.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mohamed.movieapp.R;
import com.example.mohamed.movieapp.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 19/09/2017.
 */

public class ReviewsAdpter extends RecyclerView.Adapter<ReviewsAdpter.ReviewHolder>{
    private List<Review> mReviews=new ArrayList<>();
    private Context mContext;
    public ReviewsAdpter(Context context){
        mContext=context;
    }

    public void replacData(List<Review>  reviews){
        mReviews=reviews;
        notifyDataSetChanged();
    }
    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReviewHolder(LayoutInflater.from(mContext).inflate(R.layout.review_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
     holder.bind(mReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder{
       private TextView auther;
        private TextView content;
        public ReviewHolder(View itemView) {
            super(itemView);
            auther= (TextView) itemView.findViewById(R.id.movie_author);
            content= (TextView) itemView.findViewById(R.id.review_content);
        }

        public void bind(Review review){
            auther.setText(review.getAuthor());
            content.setText(review.getContent());
        }
    }
}
