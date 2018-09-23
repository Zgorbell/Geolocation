package com.example.geolocation.data.net;

import com.example.geolocation.data.net.model.WeatherObject;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/data/2.5/weather")
    Single<WeatherObject> getWeather(@Query("lat") double lat,
                                     @Query("lon") double lon,
                                     @Query("appid") String appId);
}
