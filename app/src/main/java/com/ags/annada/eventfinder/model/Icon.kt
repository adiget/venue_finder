package com.ags.annada.eventfinder.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

data class Icon(val prefix : String, val suffix : String): Parcelable {
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
        val CREATOR = object : Parcelable.Creator<Icon> {
            override fun createFromParcel(source: Parcel): Icon? = Icon(source)
            override fun newArray(size: Int): Array<out Icon?> = arrayOfNulls(size)
        }
    }

    /**
     * Secondary constructor for the parcelable
     */
    protected constructor(parcelIn: Parcel) : this(parcelIn.readString(), parcelIn.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(prefix)
        dest?.writeString(suffix)
    }

    override fun describeContents() = 0
}
