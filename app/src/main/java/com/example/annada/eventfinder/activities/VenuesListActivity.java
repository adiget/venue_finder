package com.example.annada.eventfinder.activities;

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.annada.eventfinder.model.venueapi.FourSquareGroup;
import com.example.annada.eventfinder.model.venueapi.FourSquareJSON;
import com.example.annada.eventfinder.model.venueapi.FourSquareResponseOrig;
import com.example.annada.eventfinder.model.venueapi.FourSquareResults;
import com.example.annada.eventfinder.R;
import com.example.annada.eventfinder.adapters.RecyclerViewAdapter;
import com.example.annada.eventfinder.model.categoryapi.CategoryModel;
import com.example.annada.eventfinder.restapis.FourSquareService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenuesListActivity extends Activity{

    private String categoryName;
    private String categoryId;

    private RecyclerView placePicker;
    private LinearLayoutManager placePickerManager;
    private RecyclerView.Adapter placePickerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle category = getIntent().getExtras();
            categoryName = category.getString("name");
            categoryId = category.getString("categoryID");

        } else {
            categoryName = savedInstanceState.getString("mCategoryName");
            categoryId = savedInstanceState.getString("mCategoryId");
        }

        setTitle(categoryName + " Near Me");

        placePicker = (RecyclerView)findViewById(R.id.eventList);

        placePicker.setHasFixedSize(true);
        placePickerManager = new LinearLayoutManager(this);
        placePicker.setLayoutManager(placePickerManager);
        placePicker.addItemDecoration(new DividerItemDecoration(placePicker.getContext(), placePickerManager.getOrientation()));

        getVenuesNearMeByCategories();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO save your instance to outState
        outState.putString("mCategoryName", categoryName);
        outState.putString("mCategoryId", categoryId);
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    private void getVenuesNearMeByCategories(){
        String userLL = CategoryModel.getLatitude() + "," +  CategoryModel.getLongitude();

        Call<FourSquareJSON> eventCall = FourSquareService.getAPI().searchVenuesByCategoryID(userLL, categoryId);

        eventCall.enqueue(new Callback<FourSquareJSON>() {
            @Override
            public void onResponse(Call<FourSquareJSON> call, Response<FourSquareJSON> response) {
                FourSquareJSON fjson = response.body();
                FourSquareResponseOrig fr = fjson.response;
                FourSquareGroup fg = fr.group;
                List<FourSquareResults> frs = fg.results;

                placePickerAdapter = new RecyclerViewAdapter(getApplicationContext(), frs);
                placePicker.setAdapter(placePickerAdapter);
            }

            @Override
            public void onFailure(Call<FourSquareJSON> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "This App can't connect to Foursquare's servers!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
