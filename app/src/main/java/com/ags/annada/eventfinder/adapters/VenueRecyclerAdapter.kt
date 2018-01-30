package com.ags.annada.eventfinder.adapters

import android.content.Context
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ags.annada.eventfinder.R
import com.ags.annada.eventfinder.model.VenueModel
import com.ags.annada.eventfinder.views.PaletteTransformation
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.row_view.view.*

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class VenueRecyclerAdapter(private val mContext: Context, private val mVenues: List<VenueModel>) : RecyclerView.Adapter<VenueRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.row_view, parent, false)
        return ViewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return mVenues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val selectedVenue = mVenues[position]

        holder.itemView.venue_name.text = selectedVenue.venueName
        //holder.VenueRating.setText(selectedVenue.getVenueRating());
        Picasso.with(mContext)
                .load(selectedVenue.venueIcon)
                .transform(PaletteTransformation.instance())
                //attempt to load the image from cache
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .into(holder.itemView.venue_icon, object : PaletteTransformation.PaletteCallback(holder.itemView.venue_icon) {
                    public override fun onSuccess(palette: Palette) {
                        val color = palette.getLightVibrantColor(
                                mContext.resources.getColor(android.R.color.holo_orange_light))
                        holder.itemView.venue_icon.setBackgroundColor(color)
                        selectedVenue.color = color
                    }

                    override fun onError() {
                        //Try again online if cache failed
                        Picasso.with(mContext)
                                .load(selectedVenue.venueIcon)
                                .into(holder.itemView.venue_icon, object : Callback {
                                    override fun onSuccess() {}

                                    override fun onError() {}
                                })
                    }
                })

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
