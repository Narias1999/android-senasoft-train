package com.example.nicolas.senasofttrain2;

import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.List;

import base.Base;
import db.DaoSession;
import db.LugaresDao;
import db.Lugares;

public class places extends Menus {
    RecyclerView recycler;
    DaoSession sesion;
    LugaresDao lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sitios");
        FrameLayout frame = (FrameLayout) findViewById(R.id.fl);
        getLayoutInflater().inflate(R.layout.activity_places, frame);
        NavigationView drawer = (NavigationView) findViewById(R.id.nav_view);
        drawer.getMenu().getItem(1).setChecked(true);
        initVariables();
    }

    public void initVariables() {
        sesion = ((Base) getApplication()).getDaoSession();
        lugares = sesion.getLugaresDao();

        List<Lugares> places = lugares.queryBuilder().orderAsc(LugaresDao.Properties.Name).build().list();
        recycler = (RecyclerView) findViewById(R.id.rv_places);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(new RVPlaces(places, this));
    }
}
