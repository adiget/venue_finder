package com.ags.annada.eventfinder.model.venueapi

import android.os.Parcel
import android.os.Parcelable
import com.ags.annada.eventfinder.model.Photo

/**
 * Author: annada
 * Created: 21/10/2017
 */
data class FourSquareResults(val venue: FourSquareVenue, val photo: Photo?): Parcelable{
    val venueImageUrl: String

    init {
        this.venueImageUrl = buildVenueImageUrl(photo)
    }

    private fun buildVenueImageUrl(photo: Photo?): String {
        return (photo?.prefix ?: "") + venueImageSize + (photo?.suffix ?: "")
    }



    /**
     * Companion object is used to defined static members to the class..
     * in our case the creator and the iconsize
     */
    companion object {
        private val venueImageSize = "original"

        /**
         * Parcelable creator.
         *
         * @JvmField used make the creator implementation visible as a field to Java.
         *
         */
        @JvmField
        val CREATOR = object : Parcelable.Creator<FourSquareResults> {
            override fun createFromParcel(source: Parcel): FourSquareResults? = FourSquareResults(source)
            override fun newArray(size: Int): Array<out FourSquareResults?> = arrayOfNulls(size)
        }
    }

    /**
     * Secondary constructor for the parcelable
     */
    protected constructor(parcelIn: Parcel) : this(parcelIn.readParcelable(FourSquareVenue::class.java.classLoader) as FourSquareVenue
            ,parcelIn.readParcelable(Photo::class.java.classLoader) as Photo?)

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(venue,0)
        dest?.writeParcelable(photo,0)

    }

    override fun describeContents() = 0
}
