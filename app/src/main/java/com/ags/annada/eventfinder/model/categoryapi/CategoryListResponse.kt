package com.ags.annada.eventfinder.model.categoryapi


import com.ags.annada.eventfinder.model.Meta
import com.ags.annada.eventfinder.model.Response
import com.ags.annada.eventfinder.model.categoryapi.CategoryResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

class CategoryListResponse {
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
    @SerializedName("notifications")
    @Expose
    var notifications: List<Notification>? = null
    @SerializedName("response")
    @Expose
    var response: CategoryResponse? = null
}
