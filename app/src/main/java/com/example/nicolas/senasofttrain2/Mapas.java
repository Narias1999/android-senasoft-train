package com.example.nicolas.senasofttrain2;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.maps.android.PolyUtil;

public class Mapas extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Double fromLat = 4.545695136892776,
            fromLong = -75.67256734597161,
            toLat= 4.537481262607865,
            toLong = -75.66655919777826;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getInformation();
    }


    public  void getInformation() {
        String url = "https://maps.googleapis.com/maps/api/directions/json" +
                "?origin="+fromLat+","+fromLong +
                "&destination="+toLat+","+toLong +
                "&sensor=false&mode=driving&alternatives=true" +
                "&key=AIzaSyBHL_1DJMdMTtzzB5fzW5QLrZ3eEJX7Yqc";
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().create();

                JsonObject objeto = gson.fromJson(response, JsonElement.class).getAsJsonObject();
                String puntos = objeto.get("routes").getAsJsonArray().get(0).getAsJsonObject().get("overview_polyline").getAsJsonObject().get("points").getAsString();
                PolylineOptions options = new PolylineOptions().color(Color.BLUE).addAll(PolyUtil.decode(puntos));

                mMap.addPolyline(options);
                LatLng markerPosition = new LatLng(fromLat, fromLong);
                MarkerOptions mOptions = new MarkerOptions().position(markerPosition).title("We are here!");
                mMap.addMarker(mOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 14));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Mapas.this, "Error al hacer el request, asegurese de estar conectado a una red son restricciones", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQ = Volley.newRequestQueue(this);
        requestQ.add(sr);
    }
}
