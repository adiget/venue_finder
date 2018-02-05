package com.ags.annada.eventfinder.model.venueapi

import android.os.Parcel
import android.os.Parcelable
import com.ags.annada.eventfinder.model.Icon
import com.ags.annada.eventfinder.model.Photo
import com.ags.annada.eventfinder.model.categoryapi.CategoryModel

/**
 * Author: annada
 * Created: 21/10/2017
 */
data class FourSquareVenue(val id: String, val name: String, val rating: Double, val location: FourSquareLocation) : Parcelable {

    /**
     * Companion object is used to defined static members to the class..
     * in our case the creator and the iconsize
     */
    companion object {


        /**
         * Parcelable creator.
         *
         * @JvmField used make the creator implementation visible as a field to Java.
         *
         */
        @JvmField
        val CREATOR = object : Parcelable.Creator<FourSquareVenue> {
            override fun createFromParcel(source: Parcel): FourSquareVenue? = FourSquareVenue(source)
            override fun newArray(size: Int): Array<out FourSquareVenue?> = arrayOfNulls(size)
        }
    }

    /**
     * Secondary constructor for the parcelable
     */
    protected constructor(parcelIn: Parcel) : this(parcelIn.readString(), parcelIn.readString(),parcelIn.readDouble(), parcelIn.readParcelable(FourSquareLocation::class.java.classLoader))


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(name)
        dest?.writeDouble(rating)
        dest?.writeParcelable(location,0)

    }

    override fun describeContents() = 0
}
