package com.example.annada.eventfinder.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.annada.eventfinder.activities.MapsActivity
import kotlinx.android.synthetic.main.item_place_picker.view.*

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class RecyclerViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    internal var id: String? = null
    internal var latitude: Double = 0.toDouble()
    internal var longitude: Double = 0.toDouble()

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        // Creates an intent to direct the user to a map view
        val context = v!!.context
        val i = Intent(context, MapsActivity::class.java)

        // Passes the crucial venue details onto the map view
        i.putExtra("name", v.placePickerItemName!!.text)
        i.putExtra("ID", id)
        i.putExtra("latitude", latitude)
        i.putExtra("longitude", longitude)

        // Transitions to the map view.
        context.startActivity(i)
    }

    companion object {
        private val TAG = RecyclerViewHolders::class.java.simpleName
    }
}