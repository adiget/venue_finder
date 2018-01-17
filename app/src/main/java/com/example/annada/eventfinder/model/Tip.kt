package com.example.annada.eventfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

class Tip {
    @Expose
    var id: String? = null
    @SerializedName("createdAt")
    @Expose
    var createdAt: Int? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("canonicalUrl")
    @Expose
    var canonicalUrl: String? = null
    @SerializedName("likes")
    @Expose
    var likes: Likes? = null
    @SerializedName("logView")
    @Expose
    var logView: Boolean? = null
    @SerializedName("agreeCount")
    @Expose
    var agreeCount: Int? = null
    @SerializedName("disagreeCount")
    @Expose
    var disagreeCount: Int? = null
    @SerializedName("todo")
    @Expose
    var todo: Todo? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
}
