package com.example.annada.eventfinder.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.content.ContextCompat
import android.view.MenuItem

import com.example.annada.eventfinder.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private var mMap: GoogleMap? = null
    private var venueID: String? = null
    private var venueName: String? = null
    private var venueLatitude: Double = 0.toDouble()
    private var venueLongitude: Double = 0.toDouble()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        actionBar!!.setHomeButtonEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val venue = intent.extras
        venueID = venue!!.getString("ID")
        venueName = venue.getString("name")
        venueLatitude = venue.getDouble("latitude")
        venueLongitude = venue.getDouble("longitude")
        title = venueName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Centers and zooms the map into the selected venue
        val venue = LatLng(venueLatitude, venueLongitude)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(venue, 16f))

        // Creates and displays marker and info window for the venue
        val marker = mMap!!.addMarker(MarkerOptions()
                .position(venue)
                .title(venueName)
                .snippet("View on Foursquare"))
        marker.showInfoWindow()
        mMap!!.setOnInfoWindowClickListener(this)

        // Checks for location permissions at runtime (required for API >= 23)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Shows the user's current location
            mMap!!.isMyLocationEnabled = true
        }
    }

    override fun onInfoWindowClick(marker: Marker) {

        // Opens the Foursquare venue page when a user clicks on the info window of the venue
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://foursquare.com/v/" + venueID!!))
        startActivity(browserIntent)
    }
}
