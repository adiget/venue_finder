package com.ags.annada.eventfinder.model.categoryapi

import com.ags.annada.eventfinder.model.Icon
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

class Category_ {
    @SerializedName("categories")
    @Expose
    var categories: List<Any>? = null
    @SerializedName("icon")
    @Expose
    var icon: Icon? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("pluralName")
    @Expose
    var pluralName: String? = null
    @SerializedName("shortName")
    @Expose
    var shortName: String? = null
}
