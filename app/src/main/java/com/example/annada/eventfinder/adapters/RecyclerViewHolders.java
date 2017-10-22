package com.example.annada.eventfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.annada.eventfinder.R;
import com.example.annada.eventfinder.activities.MapsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    private static final String TAG = RecyclerViewHolders.class.getSimpleName();

    String id;
    double latitude;
    double longitude;

    @BindView(R.id.placePickerItemName) TextView name;
    @BindView(R.id.placePickerItemAddress) TextView address;
    @BindView(R.id.placePickerItemRating) TextView rating;
    @BindView(R.id.placePickerItemDistance) TextView distance;

    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Creates an intent to direct the user to a map view
        Context context = name.getContext();
        Intent i = new Intent(context, MapsActivity.class);

        // Passes the crucial venue details onto the map view
        i.putExtra("name", name.getText());
        i.putExtra("ID", id);
        i.putExtra("latitude", latitude);
        i.putExtra("longitude", longitude);

        // Transitions to the map view.
        context.startActivity(i);
    }
}