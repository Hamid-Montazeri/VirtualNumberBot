package com.tradingpedia.api;

import com.tradingpedia.model.App;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface NumberLandApi {

    @GET("/v2.php")
    Call<List<App>> getBaseOnApps(@Query("apikey") String apiKey,
                                  @Query("method") String method);

}
