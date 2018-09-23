package com.example.geolocation.ui.fragment.main;

import android.util.Log;

import com.example.geolocation.App;
import com.example.geolocation.R;
import com.example.geolocation.data.db.AppDatabase;
import com.example.geolocation.data.db.model.WeatherDbModel;
import com.example.geolocation.data.net.WeatherApi;
import com.example.geolocation.data.net.model.WeatherObject;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private MainPresenter mainPresenter;
    @Inject
    AppDatabase database;
    @Inject
    WeatherApi weatherApi;

    public MainRepository() {
        App.getAppComponent().inject(this);
    }

    public void setMainPresenter(MainPresenter mainPresenter){
        this.mainPresenter = mainPresenter;
    }

    public void getWeather(double lat, double lon) {
        weatherApi.getWeather(lat, lon, App.getAppResources().getString(R.string.weather_api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<WeatherObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(this.getClass().getSimpleName(), "on subscribe");
                    }

                    @Override
                    public void onSuccess(WeatherObject weatherObject) {
                        if(mainPresenter != null){
                            mainPresenter.prepareWeather(weatherObject);
                        }else Log.e(this.getClass().getSimpleName(), "on success");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(this.getClass().getSimpleName(), e.getMessage());
                    }
                });
    }

    public void saveWeather(WeatherDbModel weatherDbModel){
        Single.fromCallable(() -> database.weatherDao().insert(weatherDbModel))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        if(mainPresenter != null){
                            if(aLong > 0){
                                mainPresenter.insertResult("Saved to database.");
                            }else mainPresenter.insertResult("Not saved! Some problems.");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mainPresenter != null){
                            mainPresenter.insertResult("Fatal error saving!");
                        }
                    }
                });

    }


}
