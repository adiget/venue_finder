package com.ags.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Photos {
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("groups")
    @Expose
    var groups: List<Any>? = null
}
