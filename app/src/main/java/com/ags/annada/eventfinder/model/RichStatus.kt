package com.ags.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class RichStatus {
    @SerializedName("entities")
    @Expose
    var entities: List<Any>? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
}
