package com.ags.annada.eventfinder.model.venueapi

import android.os.Parcel
import android.os.Parcelable
import com.ags.annada.eventfinder.model.Icon

/**
 * Author: annada Prusty
 * Created: 21/10/2017
 */

data class FourSquareLocation(val address: String?, val lat: Double, val lng: Double, val distance: Int): Parcelable {
    /**
     * Companion object is used to defined static members to the class..
     * in our case the creator.
     */
    companion object {
        /**
         * Parcelable creator.
         *
         * @JvmField used make the creator implementation visible as a field to Java.
         *
         */
        @JvmField
        val CREATOR = object : Parcelable.Creator<FourSquareLocation> {
            override fun createFromParcel(source: Parcel): FourSquareLocation? = FourSquareLocation(source)
            override fun newArray(size: Int): Array<out FourSquareLocation?> = arrayOfNulls(size)
        }
    }

    /**
     * Secondary constructor for the parcelable
     */
    protected constructor(parcelIn: Parcel) : this(parcelIn.readString(), parcelIn.readDouble(), parcelIn.readDouble(),parcelIn.readInt())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(address)
        dest?.writeDouble(lat)
        dest?.writeDouble(lng)
        dest?.writeInt(distance)
    }

    override fun describeContents() = 0
}
