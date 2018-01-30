package com.ags.annada.eventfinder.model.categoryapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

class CategoryResponse {
    @SerializedName("categories")
    @Expose
    var categories: List<CategoryApiCategory>? = null
}
