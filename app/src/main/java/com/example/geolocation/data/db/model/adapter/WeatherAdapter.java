package com.example.geolocation.data.db.model.adapter;

import com.example.geolocation.data.db.model.WeatherDbModel;
import com.example.geolocation.data.net.model.WeatherObject;

public class WeatherAdapter {
    static public WeatherDbModel toWeatherDbModel(WeatherObject object, String region){
        WeatherDbModel model = new WeatherDbModel();
        model.setClouds(object.getClouds().getAll());
        model.setHumidity(object.getMain().getHumidity());
        model.setLat(object.getCoord().getLat());
        model.setLon(object.getCoord().getLon());
        model.setPressure(object.getMain().getPressure());
        model.setRegion(region);
        model.setTemperature(object.getMain().getCelsiusTemp());
        model.setTimestamp(System.currentTimeMillis());
        model.setWeather(object.getTotalWeather());
        model.setWindCourse(object.getWind().getDeg());
        model.setWindSpeed(object.getWind().getSpeed());
        return model;
    }
}
