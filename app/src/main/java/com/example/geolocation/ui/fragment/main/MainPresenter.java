package com.example.geolocation.ui.fragment.main;

import android.location.Address;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.geolocation.App;
import com.example.geolocation.R;
import com.example.geolocation.constant.Constants;
import com.example.geolocation.data.db.model.adapter.WeatherAdapter;
import com.example.geolocation.data.net.WeatherApi;
import com.example.geolocation.data.net.model.WeatherObject;
import com.example.geolocation.ui.mediator.mainHistory.HistoryColleagueMainMediator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainMvpView> {
    private static final String TAG = MainPresenter.class.getSimpleName();
    private static final long NON_LOCATION = 1000;
    @Inject
    ReactiveLocationProvider locationProvider;
    @Inject
    HistoryColleagueMainMediator historyColleagueMainMediator;
    private double lat;
    private double lon;
    private long lastCheckReverseTime;
    private long lastCheckWeatherTime;
    private String region;
    @Inject
    MainRepository mainRepository;

    @Inject
    WeatherApi weatherApi;

    {
        lat = NON_LOCATION;
        lon = NON_LOCATION;
        region = "";
    }

    public MainPresenter() {
        super();
        App.getAppComponent().inject(this);
        historyColleagueMainMediator.setMainPresenter(this);
        mainRepository.setMainPresenter(this);
    }

    public void onLocationChanged(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        Log.e(TAG, String.valueOf(System.currentTimeMillis() - lastCheckReverseTime));
        if (System.currentTimeMillis() - lastCheckReverseTime > 60000) {
            Log.e(MainPresenter.class.getSimpleName(), "try reverse");
            getViewState().getReverseGeocode(lat, lon);
        }
        getViewState().setCurrentLocation(String.valueOf(lon), String.valueOf(lat));
    }

    public void buttonCheckWeatherClicked() {
        if (System.currentTimeMillis() - lastCheckWeatherTime > 2000) {
            if (lat != NON_LOCATION && lon != NON_LOCATION) {
                mainRepository.getWeather(lat, lon);
                lastCheckWeatherTime = System.currentTimeMillis();
            } else {
                getViewState().showMessage("Please wait for location");
            }
        } else {
            getViewState().showMessage("Not so fast cowboy");
        }
    }

    public void reverseLocationChanged(List<Address> addresses){
        Log.e(TAG, String.valueOf(addresses.size()));
        String s = "";
        for (Address address : addresses) {
            String tempS = "";
            if (address.getCountryName() != null) {
                tempS += address.getCountryName() + " ";
            }
            if (address.getAdminArea() != null) {
                tempS += address.getAdminArea() + " ";
            }
            if (address.getSubAdminArea() != null) {
                tempS += address.getSubAdminArea() + " ";
            }
            if (address.getLocality() != null) {
                tempS += address.getLocality() + " ";
            }
            if (address.getSubLocality() != null) {
                tempS += address.getSubLocality() + " ";
            }
            if (address.getThoroughfare() != null) {
                tempS += address.getThoroughfare() + " ";
            }
            if (address.getSubThoroughfare() != null) {
                tempS += address.getSubThoroughfare();
            }
            if (tempS.length() >= s.length()) {
                s = tempS;
            }
        }
        if(s.length() > region.length()) region = s;
        getViewState().setCurrentReverseLocation(region);
        lastCheckReverseTime = System.currentTimeMillis();
    }

    public void insertResult(String message) {
        getViewState().showMessage(message);
        historyColleagueMainMediator.onDatabaseChanged();
    }

    public void prepareWeather(WeatherObject weatherObject) {
        Map<String, String> weather = new HashMap<>();
        if (weatherObject.getWeather().length > 0) {
            weather.put(Constants.WEATHER, weatherObject.getWeather()[0].getDescription());
        }
        weather.put(Constants.TEMPERATURE, weatherObject.getMain().getCelsiusTemp());
        weather.put(Constants.HUMIDITY, weatherObject.getMain().getHumidity() + " %");
        weather.put(Constants.PRESSURE, weatherObject.getMain().getPressure() + " hPa");
        weather.put(Constants.WIND_SPEED, weatherObject.getWind().getSpeed() + " m/s");
        weather.put(Constants.WIND_COURSE, weatherObject.getWind().getDeg() + "\u00B0");
        weather.put(Constants.CLOUDS, weatherObject.getClouds().getAll() + " %");
        getViewState().setWeather(weather);
        mainRepository.saveWeather(WeatherAdapter.toWeatherDbModel(weatherObject, region));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainRepository.setMainPresenter(null);
        historyColleagueMainMediator.setMainPresenter(null);
    }
}
