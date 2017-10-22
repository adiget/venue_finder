package com.example.annada.eventfinder.activities;

/**
 * Author: annada
 * Created: 21/10/2017
 */

import android.Manifest;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annada.eventfinder.R;
import com.example.annada.eventfinder.adapters.CategoryAdapter;
import com.example.annada.eventfinder.adapters.VenueRecyclerAdapter;
import com.example.annada.eventfinder.globals.Constants;
import com.example.annada.eventfinder.model.FourSquareResponse;
import com.example.annada.eventfinder.model.Item;
import com.example.annada.eventfinder.model.Venue;
import com.example.annada.eventfinder.model.VenueModel;
import com.example.annada.eventfinder.model.categoryapi.CategoryListResponse;
import com.example.annada.eventfinder.model.categoryapi.CategoryModel;
import com.example.annada.eventfinder.model.categoryapi.Category_;
import com.example.annada.eventfinder.restapis.FourSquareService;
import com.example.annada.eventfinder.views.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements LocationListener {
    //The App specific constant when requesting the Location Permission
    private static final int PERMISSION_ACCESS_FINE_LOCATION = 1;
    private VenueRecyclerAdapter mAdapter;
    private CategoryAdapter mAdapterNw;
    public static ArrayList<VenueModel> mVenueList = new ArrayList<VenueModel>();
    public static ArrayList<String> mCategoriesList = new ArrayList<String>();
    public static ArrayList<CategoryModel> mCategoriesListNw = new ArrayList<CategoryModel>();
    ProgressBar  progressBar;
    private static int COLUMN_COUNT;
    private LocationManager locationManager;
    private Location location;
    private final int REQUEST_LOCATION = 200;

    @BindView(R.id.items_list) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.view_empty) View emptyView;
    @BindView(R.id.text) TextView emptyMsg;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initialiseUi();

        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }else{
            // make API call with longitude and latitude
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, this);

            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                CategoryModel.setLatitude(location.getLatitude());
                CategoryModel.setLongitude(location.getLongitude());

                getCategories();
            }else{
                getVenuesAtLocation(Constants.DEFAULT_LOCATION);
            }
        }
    }

    private void initialiseUi() {
        setSupportActionBar(mToolbar);

        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);

        new Handler(getMainLooper()).post(new Runnable() {
            public void run() {
             progressBar.setVisibility(View.VISIBLE);
            }
        });

        COLUMN_COUNT = getResources().getInteger(R.integer.grid_cols_count);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(COLUMN_COUNT, spacingInPixels, true));

        mCategoriesListNw =  new ArrayList<CategoryModel>();
        mAdapterNw = new CategoryAdapter(this, mCategoriesListNw);
        mRecyclerView.setAdapter(mAdapterNw);
    }

    private void getCategories(){
        String userLL = Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude());

        Call<CategoryListResponse> categoryCall = FourSquareService.getAPI().venuesCategories(userLL);

        categoryCall.enqueue(new Callback<CategoryListResponse>() {
            @Override
            public void onResponse(Call<CategoryListResponse> call, Response<CategoryListResponse> response) {
                makeCategoriesListNw(response.body());

                mAdapterNw.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CategoryListResponse> call, Throwable t) {
                mCategoriesList.clear();

                mAdapterNw.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Can't connect to Foursquare's servers!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getVenuesAtLocation(String location) {

        Call<FourSquareResponse> venueCall = FourSquareService.getAPI().venuesNearLocation(location);

        venueCall.enqueue(new Callback<FourSquareResponse>() {
            @Override
            public void onResponse(Call<FourSquareResponse> call, Response<FourSquareResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);

                makeVenueModel(response.body());

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FourSquareResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                mVenueList.clear();
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Can't connect to Foursquare's servers!", Toast.LENGTH_LONG).show();
            }
        });
    }

    void makeCategoriesListNw(CategoryListResponse apiResponse){
        mCategoriesListNw.clear();

        List<Category_> categories = apiResponse.getResponse().getCategories().get(0).getCategories();

        for (int i = 0; i < categories.size(); i++) {
            Category_ category = categories.get(i);

            mCategoriesListNw.add(new CategoryModel(category.getId(), category.getName(), category.getIcon()));
        }
    }

    void makeVenueModel(FourSquareResponse apiResponse) {
        mVenueList.clear();

        List<Item> items = apiResponse.getResponse().getGroups().get(0).getItems();

        for (int i = 0; i < items.size(); i++) {
            Venue venue = items.get(i).getVenue();
            mVenueList.add(new VenueModel(venue.getName(), venue.getRating(), venue.getCategories()));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //make api call
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, this);

                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        CategoryModel.setLatitude(location.getLatitude());
                        CategoryModel.setLongitude(location.getLongitude());

                        getCategories();

                    }else{
                        getVenuesAtLocation(Constants.DEFAULT_LOCATION);
                    }
                }
            }else{
                Toast.makeText(this, getString(R.string.permission_notice), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            showGPSDisabledAlertToUser();
        }
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
