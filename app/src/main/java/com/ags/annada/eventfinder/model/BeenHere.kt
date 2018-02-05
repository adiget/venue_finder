package com.ags.annada.eventfinder.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

data class BeenHere(val count: Int, val marked: Boolean, val lastCheckinExpiredAt: Int)

data class Bounds(val ne: Ne, val sw: Sw)

data class Category(var id: String, var name: String, var pluralName: String, var shortName: String, var icon: Icon, var primary: Boolean)

data class Center(val lat: Double, val lng: Double)

data class Contact(val phone: String, val formattedPhone: String, val twitter: String)

data class FourSquareResponse(val meta: Meta, val response: Response)

data class Geocode(val what: String, val where: String, val center: Center, val displayString: String,
                   val cc: String, val geometry: Geometry, val slug: String, val longId: String)

data class Geometry(val bounds: Bounds)

data class Group(val type: String, val name: String, val items: List<Item>)

data class HereNow(val count: Int, val summary: String, val groups: List<Any>)

data class Hours(val isOpen: Boolean, val isLocalHoliday: Boolean, val status: String, val richStatus: RichStatus)

data class Item(val reasons: Reasons, val venue: Venue, val tips: List<Tip>, val referralId: String)

data class Item_(val summary: String, val type: String, val reasonName: String)

data class LabeledLatLng(val label: String, val lat: Double, val lng: Double)

data class Likes(val count: Int, val groups: List<Any>, val summary: String)

data class Location(val address: String, val lat: Double, val lng: Double,
                    val labeledLatLngs: List<LabeledLatLng>, val postalCode: String,
                    val cc: String, val city: String, val state: String, val country: String,
                    val formattedAddress: List<String>)

data class Meta(val code: Int, val requestId: String)

data class Ne(val lat: Double, val lng: Double)

data class Ne_(val lat: Double, val lng: Double)

//data class Photo(val prefix: String, val suffix: String)
data class Photo(val id: String?, val createdAt: String?, val prefix : String, val suffix : String): Parcelable {
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
        val CREATOR = object : Parcelable.Creator<Photo> {
            override fun createFromParcel(source: Parcel): Photo? = Photo(source)
            override fun newArray(size: Int): Array<out Photo?> = arrayOfNulls(size)
        }
    }

    /**
     * Secondary constructor for the parcelable
     */
    protected constructor(parcelIn: Parcel) : this(parcelIn.readString(), parcelIn.readString(), parcelIn.readString(), parcelIn.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(createdAt)
        dest?.writeString(prefix)
        dest?.writeString(suffix)
    }

    override fun describeContents() = 0
}

data class Photos(val count: Int, val groups: List<Any>)

data class Price(val tier: Int, val message: String, val currency: String)

data class Reasons(val count: Int, val items: List<Item_>)

data class Response(val geocode: Geocode, val headerLocation: String, val headerFullLocation: String,
                    val headerLocationGranularity: String, val totalResults: Int, val suggestedBounds: SuggestedBounds,
                    val groups: List<Group>)

data class RichStatus(val entities: List<Any>, val text: String)

data class Stats(val checkinsCount: Int, val usersCount: Int, val tipCount: Int)

data class SuggestedBounds(val ne: Ne_, val sw: Sw_)

data class Sw(val lat: Double, val lng: Double)

data class Sw_(val lat: Double, val lng: Double)

data class Tip(val id: String, val createdAt: Int, val text: String, val type: String, val canonicalUrl: String,
               val likes: Likes, val logView: Boolean, val agreeCount: Int, val disagreeCount: Int,
               val todo: Todo, val user: User)

data class Todo(val count: Int)

data class User(val id: String, val firstName: String, val lastName: String, val gender: String, val photo: Photo)

data class Venue(val id: String, val name: String, val contact: Contact, val location: Location, val categories: List<Category>,
                 val verified: Boolean,
                 val stats: Stats,
                 val rating: String,
                 val ratingSignals: Int,
                 val allowMenuUrlEdit: Boolean,
                 val beenHere: BeenHere,
                 val hours: Hours,
                 val photos: Photos,
                 val hereNow: HereNow,
                 val url: String,
                 val price: Price,
                 val storeId: String)

