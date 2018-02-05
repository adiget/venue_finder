package com.ags.annada.eventfinder.model.categoryapi

import com.ags.annada.eventfinder.model.Icon
import com.ags.annada.eventfinder.model.Item
import com.ags.annada.eventfinder.model.Meta
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

data class Category_(val categories: List<Any>?, val icon: Icon?, val id: String?, val name: String?,
                     val pluralName: String?, val shortName: String?)

data class CategoryApiCategory(val categories: List<Category_>?)

data class CategoryResponse(val categories: List<CategoryApiCategory>?)

data class Notification(val item: Item?, val type: String?)

data class CategoryListResponse(val meta: Meta?, val notifications: List<Notification>?, val response: CategoryResponse?)