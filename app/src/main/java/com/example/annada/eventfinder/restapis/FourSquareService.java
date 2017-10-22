package com.example.annada.eventfinder.restapis;



/**
 * Created by : annada
 * Date : 21/10/2017.
 */

import com.example.annada.eventfinder.globals.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FourSquareService {
    private static APIs mWebService;

    public static synchronized APIs getAPI(){
        if(mWebService == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mWebService = retrofit.create(APIs.class);
        }

        return mWebService;
    }
}