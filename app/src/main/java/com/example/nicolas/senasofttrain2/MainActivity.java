package com.example.nicolas.senasofttrain2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    int time = 1200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent view = new Intent(MainActivity.this, ListUsers.class);
                        startActivity(view);
                    }
                }, time);
    }
}
