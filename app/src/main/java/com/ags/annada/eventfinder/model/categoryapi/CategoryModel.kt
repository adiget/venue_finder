package com.ags.annada.eventfinder.model.categoryapi

import android.os.Parcel
import android.os.Parcelable
import com.ags.annada.eventfinder.model.Category
import com.ags.annada.eventfinder.model.Icon

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

data class CategoryModel(val categoryId: String, val categoryName: String, val icon: Icon) : Parcelable{
    val categoryIcon: String

    var color: Int = 0

    init {
        this.categoryIcon = buildIconUri(icon)
    }

    private fun buildIconUri(icon: Icon): String {
        return icon.prefix + venueIconSize + icon.suffix
    }

    /**
     * Companion object is used to defined static members to the class..
     * in our case the creator and the iconsize
     */
    companion object {
        private val venueIconSize = "64"
        var latitude: Double = 0.toDouble()
        var longitude: Double = 0.toDouble()

        /**
         * Parcelable creator.
         *
         * @JvmField used make the creator implementation visible as a field to Java.
         *
         */
        @JvmField
        val CREATOR = object : Parcelable.Creator<CategoryModel> {
            override fun createFromParcel(source: Parcel): CategoryModel? = CategoryModel(source)
            override fun newArray(size: Int): Array<out CategoryModel?> = arrayOfNulls(size)
        }
    }

    /**
     * Secondary constructor for the parcelable
     */
    protected constructor(parcelIn: Parcel) : this(parcelIn.readString(), parcelIn.readString(), parcelIn.readParcelable(Icon::class.java.classLoader))

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(categoryId)
        dest?.writeString(categoryName)
        dest?.writeParcelable(icon,0)
    }

    override fun describeContents() = 0
}
