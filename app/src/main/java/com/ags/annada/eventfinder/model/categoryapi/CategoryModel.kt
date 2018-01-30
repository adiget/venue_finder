package com.ags.annada.eventfinder.model.categoryapi

import com.ags.annada.eventfinder.model.Category
import com.ags.annada.eventfinder.model.Icon

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

data class CategoryModel(val categoryId: String, val categoryName: String, val icon: Icon) {
    val categoryIcon: String

    var color: Int = 0

    init {
        this.categoryIcon = buildIconUri(icon)
    }

    private fun buildIconUri(icon: Icon): String {
        return icon.prefix + venueIconSize + icon.suffix
    }

    companion object {
        private val venueIconSize = "64"
        var latitude: Double = 0.toDouble()
        var longitude: Double = 0.toDouble()
    }
}
