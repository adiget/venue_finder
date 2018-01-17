package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Photo {
    @SerializedName("prefix")
    @Expose
    var prefix: String? = null
    @SerializedName("suffix")
    @Expose
    var suffix: String? = null
}
