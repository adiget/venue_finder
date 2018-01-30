package com.ags.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Stats {
    @SerializedName("checkinsCount")
    @Expose
    var checkinsCount: Int? = null
    @SerializedName("usersCount")
    @Expose
    var usersCount: Int? = null
    @SerializedName("tipCount")
    @Expose
    var tipCount: Int? = null
}
