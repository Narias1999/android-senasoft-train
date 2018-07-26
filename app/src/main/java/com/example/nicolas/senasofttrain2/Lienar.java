package com.example.nicolas.senasofttrain2;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class Lienar extends Menus {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout fl = (FrameLayout) findViewById(R.id.fl);
        getLayoutInflater().inflate(R.layout.activity_lienar, fl);

        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        nv.getMenu().getItem(1).setChecked(true);

        this.setTitle("dsfsd");
    }

    public void go(View view) {
        Intent otra = new Intent(Lienar.this, Relative.class);
        startActivity(otra);
    }
}
