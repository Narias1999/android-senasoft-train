package com.example.nicolas.senasofttrain2;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class places extends Menus {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sitios");
        FrameLayout frame = (FrameLayout) findViewById(R.id.fl);
        getLayoutInflater().inflate(R.layout.activity_places, frame);
        NavigationView drawer = (NavigationView) findViewById(R.id.nav_view);
        drawer.getMenu().getItem(1).setChecked(true);
    }
}
