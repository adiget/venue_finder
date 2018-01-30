package com.ags.annada.eventfinder.adapters

/**
 * Author: annada
 * Created: 21/10/2017
 */
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ags.annada.eventfinder.model.venueapi.FourSquareResults
import com.ags.annada.eventfinder.R
import com.ags.annada.eventfinder.adapters.RecyclerViewHolders
import kotlinx.android.synthetic.main.item_place_picker.view.*


class RecyclerViewAdapter(private val context: Context, private val results: List<FourSquareResults>) : RecyclerView.Adapter<RecyclerViewHolders>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolders {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_place_picker, parent, false)
        return RecyclerViewHolders(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolders, position: Int) {

        // Sets the proper rating colour, referenced from the Foursquare Brand Guide
        val ratingRaw = results[position].venue!!.rating
        if (ratingRaw >= 9.0) {
            holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.kale))
        } else if (ratingRaw >= 8.0) {
            holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.guacamole))
        } else if (ratingRaw >= 7.0) {
            holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.lime))
        } else if (ratingRaw >= 6.0) {
            holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.banana))
        } else if (ratingRaw >= 5.0) {
            holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
        } else if (ratingRaw >= 4.0) {
            holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.macCheese))
        } else {
            holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.strawberry))
        }

        // Sets each view with the appropriate venue details
        holder.itemView.placePickerItemName.text = results[position].venue!!.name
        holder.itemView.placePickerItemAddress.text = results[position].venue!!.location!!.address
        holder.itemView.placePickerItemRating.text = java.lang.Double.toString(ratingRaw)
        holder.itemView.placePickerItemDistance.text = Integer.toString(results[position].venue!!.location!!.distance) + "m"

        // Stores additional venue details for the map view
        holder.id = results[position].venue!!.id
        holder.latitude = results[position].venue!!.location!!.lat
        holder.longitude = results[position].venue!!.location!!.lng
    }

    override fun getItemCount(): Int {
        return results.size
    }
}
