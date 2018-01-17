package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Hours {
    @SerializedName("isOpen")
    @Expose
    var isOpen: Boolean? = null
    @SerializedName("isLocalHoliday")
    @Expose
    var isLocalHoliday: Boolean? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("richStatus")
    @Expose
    var richStatus: RichStatus? = null
}
