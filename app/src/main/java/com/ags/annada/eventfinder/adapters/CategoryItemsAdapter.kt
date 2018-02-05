package com.ags.annada.eventfinder.adapters

/**
 * Author: annada
 * Created: 21/10/2017
 */
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ags.annada.eventfinder.model.venueapi.FourSquareResults
import com.ags.annada.eventfinder.R
import com.ags.annada.eventfinder.activities.MapsActivity
import com.ags.annada.eventfinder.extensions.inflate

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.support.v7.graphics.Palette
import com.ags.annada.eventfinder.views.PaletteTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item_place.view.*

class CategoryItemsAdapter(private val context: Context, private val results: List<FourSquareResults>) : RecyclerView.Adapter<CategoryItemsAdapter.CategoryItemsViewHolders>() {
    lateinit var itemClickListener: CategoryItemsAdapter.OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemsViewHolders {
        //Calling the extension functions of ViewGroup
        val rowView = parent.inflate(R.layout.row_item_place)
        return CategoryItemsViewHolders(rowView)
    }

    override fun onBindViewHolder(holder: CategoryItemsViewHolders, position: Int) {

        // Sets the proper rating colour, referenced from the Foursquare Brand Guide
        val ratingRaw = results[position].venue!!.rating


        Picasso.with(context)
                //.load(selectedCategory.categoryIcon)

                .load(results[position].venueImageUrl)
                .transform(PaletteTransformation.instance())
                //attempt to load the image from cache
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .into(holder.itemView.cardImageView, object : PaletteTransformation.PaletteCallback(holder.itemView.cardImageView!!) {
                    public override fun onSuccess(palette: Palette) {
                        val color = palette.getLightVibrantColor(
                                context.resources.getColor(R.color.light_sky_blue))
                        holder.itemView.itemPlaceCard.setBackgroundColor(color)
                        //selectedCategory.color = color
                    }

                    override fun onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(results[position].venueImageUrl)
                                //.load(mediaName)
                                .error(context.getResources().getDrawable(R.drawable.no_image))
                                .into(holder.itemView.cardImageView)
                    }
                })




        val drawable = holder.itemView.placePickerItemRating.getBackground() as GradientDrawable

        if (ratingRaw >= 9.0) {
            //holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.kale))
            drawable.setColor(ContextCompat.getColor(context, R.color.kale))
        } else if (ratingRaw >= 8.0) {
            //holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.guacamole))
            drawable.setColor(ContextCompat.getColor(context, R.color.guacamole))
        } else if (ratingRaw >= 7.0) {
            //holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.lime))
            drawable.setColor(ContextCompat.getColor(context, R.color.lime))
        } else if (ratingRaw >= 6.0) {
            //holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.banana))
            drawable.setColor(ContextCompat.getColor(context, R.color.banana))
        } else if (ratingRaw >= 5.0) {
            //holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
            drawable.setColor(ContextCompat.getColor(context, R.color.orange))
        } else if (ratingRaw >= 4.0) {
            //holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.macCheese))
            drawable.setColor(ContextCompat.getColor(context, R.color.macCheese))
        } else {
            //holder.itemView.placePickerItemRating.setBackgroundColor(ContextCompat.getColor(context, R.color.strawberry))
            drawable.setColor(ContextCompat.getColor(context, R.color.strawberry))
        }

        // Sets each view with the appropriate venue details
        holder.itemView.placePickerItemName.text = results[position].venue!!.name
        holder.itemView.placePickerItemAddress.text = results[position].venue!!.location!!.address
        holder.itemView.placePickerItemRating.text = java.lang.Double.toString(ratingRaw)

        val distanceInMeters: Int  = results[position].venue!!.location!!.distance
        val distanceInMiles = distanceInMeters / 1609.34f

        holder.itemView.placePickerItemDistance.text = String.format("%.2f", distanceInMiles) + " miles"

        // Stores additional venue details for the map view
        holder.id = results[position].venue!!.id
        holder.latitude = results[position].venue!!.location!!.lat
        holder.longitude = results[position].venue!!.location!!.lng
    }

    override fun getItemCount(): Int {
        return results.size
    }

    inner class CategoryItemsViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var id: String? = null
        internal var latitude: Double = 0.toDouble()
        internal var longitude: Double = 0.toDouble()

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            itemClickListener.onItemClick(itemView, adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(itemClickListener: CategoryItemsAdapter.OnItemClickListener){
        this.itemClickListener = itemClickListener
    }
}