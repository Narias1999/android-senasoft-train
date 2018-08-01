package com.example.nicolas.senasofttrain2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import db.Lugares;

public class RVPlaces extends RecyclerView.Adapter<RVPlaces.ViewHolder> {

    List<Lugares> places;
    Context context;

    public RVPlaces (List places, Context context) {
        this.places = places;
        System.out.println(places);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_lugares, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageName = places.get(position).getImage();
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.imageView.setBackgroundResource(resourceId);
        holder.name.setText(places.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.place_image);
            name = (TextView) itemView.findViewById(R.id.place_name);
        }
    }
}
