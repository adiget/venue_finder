package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Venue {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("contact")
    @Expose
    var contact: Contact? = null
    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
    @SerializedName("verified")
    @Expose
    var verified: Boolean? = null
    @SerializedName("stats")
    @Expose
    var stats: Stats? = null
    @SerializedName("rating")
    @Expose
    var rating: String? = null
    @SerializedName("ratingSignals")
    @Expose
    var ratingSignals: Int? = null
    @SerializedName("allowMenuUrlEdit")
    @Expose
    var allowMenuUrlEdit: Boolean? = null
    @SerializedName("beenHere")
    @Expose
    var beenHere: BeenHere? = null
    @SerializedName("hours")
    @Expose
    var hours: Hours? = null
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null
    @SerializedName("hereNow")
    @Expose
    var hereNow: HereNow? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("price")
    @Expose
    var price: Price? = null
    @SerializedName("storeId")
    @Expose
    var storeId: String? = null
}
