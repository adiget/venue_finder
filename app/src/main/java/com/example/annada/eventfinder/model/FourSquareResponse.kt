package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class FourSquareResponse {
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
    @SerializedName("response")
    @Expose
    var response: Response? = null
}
