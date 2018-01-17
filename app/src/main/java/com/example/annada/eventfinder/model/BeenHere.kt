package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class BeenHere {
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("marked")
    @Expose
    var marked: Boolean? = null
    @SerializedName("lastCheckinExpiredAt")
    @Expose
    var lastCheckinExpiredAt: Int? = null
}
