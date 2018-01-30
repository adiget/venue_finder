package com.ags.annada.eventfinder.model

import com.ags.annada.eventfinder.model.Sw_
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class SuggestedBounds {
    @SerializedName("ne")
    @Expose
    var ne: Ne_? = null
    @SerializedName("sw")
    @Expose
    var sw: Sw_? = null
}
