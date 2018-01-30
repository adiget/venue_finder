package com.ags.annada.eventfinder;

import android.view.View;
import android.widget.Toast;

import com.example.annada.eventfinder.globals.Constants;
import com.example.annada.eventfinder.model.FourSquareResponse;
import com.example.annada.eventfinder.restapis.FourSquareService;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertTrue;

/**
 * Created by : annada
 * Date : 22/10/2017.
 */

public class APITest {
    @Test
    public void TestRestApis() {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] statuses = {false};

        Call<FourSquareResponse> venueCall = FourSquareService.INSTANCE.getAPI().venuesNearLocation(Constants.INSTANCE.getDEFAULT_LOCATION());

        venueCall.enqueue(new Callback<FourSquareResponse>() {
            @Override
            public void onResponse(Call<FourSquareResponse> call, Response<FourSquareResponse> response) {
                statuses[0] = true;
            }

            @Override
            public void onFailure(Call<FourSquareResponse> call, Throwable t) {

            }
        });

        try {
            signal.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (boolean status : statuses)
            assertTrue(status);
    }
}
