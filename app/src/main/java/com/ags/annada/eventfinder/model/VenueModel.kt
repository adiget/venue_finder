package com.ags.annada.eventfinder.model

import org.json.JSONArray
import org.json.JSONException

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class VenueModel(val venueName: String, val venueRating: String, venueCategories: List<Category>) {
    val venueIcon: String

    var color: Int = 0

    init {
        this.venueIcon = buildIconUri(venueCategories)
    }

    private fun buildIconUri(venueCategories: List<Category>): String {
        return venueCategories[0].icon!!.prefix + venueIconSize + venueCategories[0].icon!!.suffix
    }

    companion object {
        private val venueIconSize = "64"
    }

}
