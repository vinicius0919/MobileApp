package com.example.adondeapp

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() {
    private var searchView: SearchView? = null

    /*capitão poço LT LG -1.7443707,-47.0646394*/
    private val places = ArrayList<Place>()
    private val switcher = Switch(supportFragmentManager)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switcher.changeFragment(MapsFragment())
    }

    override fun onRestart() {
        super.onRestart()
        finish()
        switcher.changeFragment(MapsFragment())
    }

    fun AddPonteiro() {
        searchView = findViewById(R.id.searchView)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap: GoogleMap -> addMarker(googleMap) }

        // Adiciona um exemplo de local
        places.add(Place("Google", LatLng(-1.7443707, -47.0646394), "Capitão Poço", 4.8f))
    }

    private fun addMarker(googleMap: GoogleMap) {
        for (place in places) {
            googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.address)
                    .position(place.latLng)
            )
        }
    }
}

internal class Place(val name: String, val latLng: LatLng, val address: String, val rating: Float)
