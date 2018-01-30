package com.ags.annada.eventfinder.restapis

import com.ags.annada.eventfinder.model.venueapi.FourSquareJSON
import com.ags.annada.eventfinder.globals.Constants
import com.ags.annada.eventfinder.model.FourSquareResponse
import com.ags.annada.eventfinder.model.categoryapi.CategoryListResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

interface APIs {

    @GET(Constants.VENUE_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    fun venuesNearMe(@Query("ll") ll: String): Call<FourSquareResponse>

    @GET(Constants.VENUE_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    fun venuesNearLocation(@Query("near") location: String): Call<FourSquareResponse>


    @GET(Constants.VENUE_CATEGORIES_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    fun venuesCategories(@Query("ll") ll: String): Call<CategoryListResponse>

    @GET(Constants.VENUES_BY_CATEGORIES_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    fun searchVenuesByCategory(@Query("ll") ll: String,
                               @Query("intent") category: String): Call<FourSquareJSON>

    @GET(Constants.VENUES_BY_CATEGORIES_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    fun searchVenuesByCategoryID(@Query("ll") ll: String,
                                 @Query("categoryId") categoryId: String): Call<FourSquareJSON>

}
