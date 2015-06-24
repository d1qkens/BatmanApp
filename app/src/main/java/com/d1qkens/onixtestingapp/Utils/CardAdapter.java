package com.d1qkens.onixtestingapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.d1qkens.onixtestingapp.FlipSignActivity;
import com.d1qkens.onixtestingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    public ArrayList<String> cars;
    public ArrayList<String> titles;
    public static Activity activity;

    public CardAdapter(ArrayList<String> cars, ArrayList<String> titles, Activity activity) {
        this.cars = cars;
        this.titles = titles;
        CardAdapter.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;
        public TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.imageBatmobile);
            mText = (TextView) itemView.findViewById(R.id.titleBatmobile);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, FlipSignActivity.class);
                    activity.startActivity(intent);
                }
            });
        }

        public void setImage(String imagePath, Context context) {
            Picasso.with(context).load(imagePath).error(R.drawable.middledrawerjoker).fit().into(mImage);
        }

    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setImage(cars.get(i), activity);
        viewHolder.mText.setText(titles.get(i));

    }

    @Override
    public int getItemCount() {
            return cars.size();
    }
}
