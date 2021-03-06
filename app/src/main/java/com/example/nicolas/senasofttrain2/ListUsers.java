package com.example.nicolas.senasofttrain2;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import base.Base;
import db.DaoSession;
import db.Usuarios;
import db.UsuariosDao;

public class ListUsers extends Menus {
    public DaoSession db;
    public UsuariosDao usuarios;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frame = (FrameLayout) findViewById(R.id.fl);
        getLayoutInflater().inflate(R.layout.activity_list_users, frame);
        NavigationView drawer = (NavigationView) findViewById(R.id.nav_view);
        drawer.getMenu().getItem(0).setChecked(true);
        setTitle("Usuarios");
        initVariables();
    }

    public void initVariables() {
        db = ((Base) getApplication()).getDaoSession();
        usuarios = db.getUsuariosDao();


        List <Usuarios> userList = usuarios.queryBuilder().orderAsc(UsuariosDao.Properties.Usu_email).build().list();
        recyclerView = (RecyclerView) findViewById(R.id.rvUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        RVusuarios rVusuarios = new RVusuarios(userList, this);
        recyclerView.setAdapter(rVusuarios);
    }
}
