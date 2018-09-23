package com.example.geolocation.ui.fragment.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


import java.util.Map;

interface MainMvpView extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setCurrentLocation(String lon, String lat);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setCurrentReverseLocation(String location);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setWeather(Map<String, String> weatherInfo);

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);

    @StateStrategyType(SkipStrategy.class)
    void getReverseGeocode(double lat, double lon);
}
