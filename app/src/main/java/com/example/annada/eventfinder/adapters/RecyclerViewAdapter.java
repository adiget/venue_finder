package com.example.annada.eventfinder.adapters;

/**
 * Author: annada
 * Created: 21/10/2017
 */
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.annada.eventfinder.model.venueapi.FourSquareResults;
import com.example.annada.eventfinder.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private Context context;
    private List<FourSquareResults> results;

    public RecyclerViewAdapter(Context context, List<FourSquareResults> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_picker, parent, false);
        return new RecyclerViewHolders(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

        // Sets the proper rating colour, referenced from the Foursquare Brand Guide
        double ratingRaw = results.get(position).venue.rating;
        if (ratingRaw >= 9.0) {
            holder.rating.setBackgroundColor(ContextCompat.getColor(context, R.color.kale));
        } else if (ratingRaw >= 8.0) {
            holder.rating.setBackgroundColor(ContextCompat.getColor(context, R.color.guacamole));
        } else if (ratingRaw >= 7.0) {
            holder.rating.setBackgroundColor(ContextCompat.getColor(context, R.color.lime));
        } else if (ratingRaw >= 6.0) {
            holder.rating.setBackgroundColor(ContextCompat.getColor(context, R.color.banana));
        } else if (ratingRaw >= 5.0) {
            holder.rating.setBackgroundColor(ContextCompat.getColor(context, R.color.orange));
        } else if (ratingRaw >= 4.0) {
            holder.rating.setBackgroundColor(ContextCompat.getColor(context, R.color.macCheese));
        } else {
            holder.rating.setBackgroundColor(ContextCompat.getColor(context, R.color.strawberry));
        }

        // Sets each view with the appropriate venue details
        holder.name.setText(results.get(position).venue.name);
        holder.address.setText(results.get(position).venue.location.address);
        holder.rating.setText(Double.toString(ratingRaw));
        holder.distance.setText(Integer.toString(results.get(position).venue.location.distance) + "m");

        // Stores additional venue details for the map view
        holder.id = results.get(position).venue.id;
        holder.latitude = results.get(position).venue.location.lat;
        holder.longitude = results.get(position).venue.location.lng;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
