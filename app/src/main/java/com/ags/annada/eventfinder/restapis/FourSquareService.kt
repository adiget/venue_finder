package com.ags.annada.eventfinder.restapis


/**
 * Created by : annada
 * Date : 21/10/2017.
 */

import com.ags.annada.eventfinder.globals.Constants

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FourSquareService {
    private var mWebService: APIs? = null

    val api: APIs
        @Synchronized get() {
            if (mWebService == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                mWebService = retrofit.create(APIs::class.java)
            }

            return this!!.mWebService!!
        }
}