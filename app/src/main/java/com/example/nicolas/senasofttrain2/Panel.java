package com.example.nicolas.senasofttrain2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Panel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
    }

    public void go(View view) {
        Intent nose = new Intent(Panel.this, Lienar.class);
        startActivity(nose);
    }
}
