package com.ags.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Item_ {
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("reasonName")
    @Expose
    var reasonName: String? = null
}
