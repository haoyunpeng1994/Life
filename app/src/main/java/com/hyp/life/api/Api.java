package com.hyp.life.api;

import com.hyp.life.model.Travel.Travel;
import com.hyp.life.model.weather.Weather;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by acer on 2015/11/15.
 */
public interface Api {
    @GET("/qunartravel/travellist/travellist")
     void getTravel(@Header("apikey") String apikey, @Query("page") int page, Callback<Travel> callback);
    @GET("/showapi_open_bus/weather_showapi/address")
     void getWeather(@Header("apikey") String apikey,@Query("area") String city,@Query("needIndex")int needIndex ,Callback<Weather>callback);
}
