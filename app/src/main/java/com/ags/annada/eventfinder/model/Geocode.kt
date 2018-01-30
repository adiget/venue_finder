package com.ags.annada.eventfinder.model

import com.ags.annada.eventfinder.model.Geometry
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Geocode {
    @SerializedName("what")
    @Expose
    var what: String? = null
    @SerializedName("where")
    @Expose
    var where: String? = null
    @SerializedName("center")
    @Expose
    var center: Center? = null
    @SerializedName("displayString")
    @Expose
    var displayString: String? = null
    @SerializedName("cc")
    @Expose
    var cc: String? = null
    @SerializedName("geometry")
    @Expose
    var geometry: Geometry? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("longId")
    @Expose
    var longId: String? = null
}
