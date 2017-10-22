package com.example.annada.eventfinder.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;

import com.example.annada.eventfinder.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private String venueID;
    private String venueName;
    private double venueLatitude;
    private double venueLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle venue = getIntent().getExtras();
        venueID = venue.getString("ID");
        venueName = venue.getString("name");
        venueLatitude = venue.getDouble("latitude");
        venueLongitude = venue.getDouble("longitude");
        setTitle(venueName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Centers and zooms the map into the selected venue
        LatLng venue = new LatLng(venueLatitude, venueLongitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(venue, 16));

        // Creates and displays marker and info window for the venue
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(venue)
                .title(venueName)
                .snippet("View on Foursquare"));
        marker.showInfoWindow();
        mMap.setOnInfoWindowClickListener(this);

        // Checks for location permissions at runtime (required for API >= 23)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Shows the user's current location
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        // Opens the Foursquare venue page when a user clicks on the info window of the venue
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://foursquare.com/v/" + venueID));
        startActivity(browserIntent);
    }
}