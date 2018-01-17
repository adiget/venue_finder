package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Response {
    @SerializedName("geocode")
    @Expose
    var geocode: Geocode? = null
    @SerializedName("headerLocation")
    @Expose
    var headerLocation: String? = null
    @SerializedName("headerFullLocation")
    @Expose
    var headerFullLocation: String? = null
    @SerializedName("headerLocationGranularity")
    @Expose
    var headerLocationGranularity: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null
    @SerializedName("suggestedBounds")
    @Expose
    var suggestedBounds: SuggestedBounds? = null
    @SerializedName("groups")
    @Expose
    var groups: List<Group>? = null
}
