package com.ags.annada.eventfinder.model

import com.ags.annada.eventfinder.model.Reasons
import com.ags.annada.eventfinder.model.Tip
import com.ags.annada.eventfinder.model.Venue
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Item {
    @SerializedName("reasons")
    @Expose
    var reasons: Reasons? = null
    @SerializedName("venue")
    @Expose
    var venue: Venue? = null
    @SerializedName("tips")
    @Expose
    var tips: List<Tip>? = null
    @SerializedName("referralId")
    @Expose
    var referralId: String? = null
}
