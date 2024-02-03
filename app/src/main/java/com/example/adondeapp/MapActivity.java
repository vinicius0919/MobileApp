package com.example.adondeapp;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapActivity extends AppCompatActivity {

    private SearchView searchView;
    /*capitão poço LT LG -1.7443707,-47.0646394*/
    private final ArrayList<Place> places = new ArrayList<>();

    private final Switch switcher = new Switch(getSupportFragmentManager());
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switcher.changeFragment(new MapsFragment());


    }

    public void AddPonteiro(){

        searchView = findViewById(R.id.searchView);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this::addMarker);
        }

        // Adiciona um exemplo de local
        places.add(new Place("Google", new LatLng(-1.7443707, -47.0646394), "Capitão Poço", 4.8f));


    }

    private void addMarker(GoogleMap googleMap) {
        for (Place place : places) {
        googleMap.addMarker(new MarkerOptions()
            .title(place.getName())
            .snippet(place.getAddress())
            .position(place.getLatLng()));
    }
    }

}

class Place {
    private final String name;
    private final LatLng latLng;
    private final String address;
    private final float rating;

    public Place(String name, LatLng latLng, String address, float rating) {
        this.name = name;
        this.latLng = latLng;
        this.address = address;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getAddress() {
        return address;
    }

    public float getRating() {
        return rating;
    }


}
