package com.example.annada.eventfinder.restapis;

import com.example.annada.eventfinder.model.venueapi.FourSquareJSON;
import com.example.annada.eventfinder.globals.Constants;
import com.example.annada.eventfinder.model.FourSquareResponse;
import com.example.annada.eventfinder.model.categoryapi.CategoryListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.annada.eventfinder.globals.Constants.VENUES_BY_CATEGORIES_ENDPOINT;

/**
 * Created by : annada
 * Date : 21/10/2017.
 */

public interface APIs {

    @GET(Constants.VENUE_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    Call<FourSquareResponse> venuesNearMe(@Query("ll") String ll);

    @GET(Constants.VENUE_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    Call<FourSquareResponse> venuesNearLocation(@Query("near") String location);


    @GET(Constants.VENUE_CATEGORIES_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    Call<CategoryListResponse> venuesCategories(@Query("ll") String ll);

    @GET(VENUES_BY_CATEGORIES_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    Call<FourSquareJSON> searchVenuesByCategory(@Query("ll") String ll,
                                     @Query("intent") String category);

    @GET(VENUES_BY_CATEGORIES_ENDPOINT + "v=20170101" + "&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRETE)
    Call<FourSquareJSON> searchVenuesByCategoryID(@Query("ll") String ll,
                                                  @Query("categoryId") String categoryId);

}
