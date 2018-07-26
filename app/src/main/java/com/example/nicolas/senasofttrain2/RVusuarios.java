package com.example.nicolas.senasofttrain2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(@NonNull RVusuarios.Rview holder, int position) {
        holder.name.setText(Users.get(position).getUsu_nombre());
        System.out.println("Va echale con su madre java");
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    public class Rview extends RecyclerView.ViewHolder {
        TextView name;
        public Rview(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.liName);
        }
    }
}
