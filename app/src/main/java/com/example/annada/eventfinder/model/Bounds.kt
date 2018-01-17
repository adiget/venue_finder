package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Bounds {
    @SerializedName("ne")
    @Expose
    var ne: Ne? = null
    @SerializedName("sw")
    @Expose
    var sw: Sw? = null
}
