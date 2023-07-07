package com.tradingpedia.api;

import com.tradingpedia.model.App;
import com.tradingpedia.model.Country;
import com.tradingpedia.model.Result;
import com.tradingpedia.util.NumberLandConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface NumberLandApi {

    @GET("/v2.php?apikey=" + NumberLandConstants.NUMBER_LAND_API_KEY + "&method=getservice")
    Call<List<App>> getAppsList();

    @GET("/v2.php?apikey=" + NumberLandConstants.NUMBER_LAND_API_KEY + "&method=getcountry")
    Call<List<Country>> getCountriesList();

    @GET("/v2.php?apikey=" + NumberLandConstants.NUMBER_LAND_API_KEY + "&method=getnum")
    Call<List<Result>> buyNumber(@Query("country") String country,
                                 @Query("operator") String operator,
                                 @Query("service") String service);
}
