package com.example.nicolas.senasofttrain2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import db.Usuarios;

public class RVusuarios extends RecyclerView.Adapter<RVusuarios.Rview> {

    List<Usuarios> Users;
    Context context;

    public RVusuarios (List Users, Context context) {
        this.Users = Users;
        this.context = context;
    }

    @NonNull
    @Override
    public RVusuarios.Rview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_users, parent,false);
        Rview recycler = new Rview(listItem);
        return recycler;
    }

    @Override
    public void onBindViewHolder(@NonNull RVusuarios.Rview holder, final int position) {
        final String name = Users.get(position).getUsu_nombre();
        final String username = Users.get(position).getUsu_nick();
        final String birthDate = Users.get(position).getUsu_fecha();
        final String email = Users.get(position).getUsu_email();
        final String imageUrl = "http://i.imgur.com/DvpvklR.png";

        Picasso.get().load(imageUrl)
                .error(R.drawable.berbeo)
                .into(holder.imageView);
        holder.name.setText(name);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UserDetail.class);
                i.putExtra("name", name);
                i.putExtra("username", username);
                i.putExtra("birthDate", birthDate);
                i.putExtra("email", email);
                i.putExtra("image", imageUrl);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    public class Rview extends RecyclerView.ViewHolder {
        TextView name;
        CardView card;
        ImageView imageView;
        public Rview(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.liName);
            card = itemView.findViewById(R.id.cardItem);
        }
    }
}
