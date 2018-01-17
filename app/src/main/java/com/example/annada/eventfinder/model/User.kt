package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class User {
    @Expose
    var id: String? = null
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null
    @SerializedName("lastName")
    @Expose
    var lastName: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo? = null
}
