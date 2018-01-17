package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class HereNow {
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("groups")
    @Expose
    var groups: List<Any>? = null
}
