package com.example.mohamed.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mohamed.movieapp.R;
import com.example.mohamed.movieapp.model.Trailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 19/09/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder>{
    List<Trailer> list=new ArrayList<>();
    Context mContext;
    public TrailerAdapter(Context context){
        mContext=context;
    }
    public void replaceData(List<Trailer> mTrailers){
        list=mTrailers;
        notifyDataSetChanged();
    }
    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrailerHolder(LayoutInflater.from(mContext).inflate(R.layout.trailer_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(TrailerHolder holder, int position) {
      holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class TrailerHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private Trailer mTrailer;
        public TrailerHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.trailer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + mTrailer.getKey())));
                }
            });
        }

        public void bind(Trailer trailer){
            mTrailer=trailer;
            textView.setText(trailer.getName());
        }
    }


}
