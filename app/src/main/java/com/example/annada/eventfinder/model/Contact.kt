package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Contact {
    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("formattedPhone")
    @Expose
    var formattedPhone: String? = null
    @SerializedName("twitter")
    @Expose
    var twitter: String? = null
}
