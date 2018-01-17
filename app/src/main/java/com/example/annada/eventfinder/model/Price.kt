package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Price {
    @SerializedName("tier")
    @Expose
    var tier: Int? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("currency")
    @Expose
    var currency: String? = null
}
