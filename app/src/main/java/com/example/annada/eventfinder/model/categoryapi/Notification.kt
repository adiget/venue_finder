package com.example.annada.eventfinder.model.categoryapi

import com.example.annada.eventfinder.model.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

class Notification {
    @SerializedName("item")
    @Expose
    var item: Item? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
}
