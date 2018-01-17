package com.example.annada.eventfinder.model.categoryapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

class CategoryApiCategory {
    @SerializedName("categories")
    @Expose
    var categories: List<Category_>? = null
}
