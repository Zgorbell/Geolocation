package com.example.geolocation.ui.activity.element;


import android.util.Log;

import com.example.geolocation.App;
import com.example.geolocation.data.db.AppDatabase;
import com.example.geolocation.data.db.model.WeatherDbModel;
import com.example.geolocation.data.shared.SelectedElementItem;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ElementRepository {

    @Inject
    AppDatabase database;
    @Inject
    SelectedElementItem selectedElementItem;
    private ElementPresenter elementPresenter;

    public void setElementPresenter(ElementPresenter elementPresenter){
        this.elementPresenter = elementPresenter;
        App.getAppComponent().inject(this);
    }

    public void getSelectedElementId(){
        if(elementPresenter != null){
            elementPresenter.setHistoryElementId(selectedElementItem.getSelectedItem());
        }
    }

    public void getWeatherDbModel(long id){
        database.weatherDao()
                .getModel(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<WeatherDbModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(WeatherDbModel weatherDbModel) {
                        if(elementPresenter != null){
                            elementPresenter.setWeather(weatherDbModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(this.getClass().getSimpleName(), "download fatal error " + e.getMessage());
                        if(elementPresenter != null){
                            elementPresenter.showMessage("Downloading fatal error!");
                        }
                    }
                });
    }
}
