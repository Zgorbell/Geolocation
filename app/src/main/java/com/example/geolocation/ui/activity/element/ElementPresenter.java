package com.example.geolocation.ui.activity.element;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.geolocation.App;
import com.example.geolocation.data.db.model.WeatherDbModel;

import javax.inject.Inject;

@InjectViewState
public class ElementPresenter extends MvpPresenter<ElementMvpView> {

    @Inject
    ElementRepository elementRepository;
    private long elementId;

    {
        elementId = -1;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getAppComponent().inject(this);
        elementRepository.setElementPresenter(this);
        elementRepository.getSelectedElementId();
    }

    public void setWeather(WeatherDbModel weather){
        if(weather != null) getViewState().setInfo(weather);
        else getViewState().showMessage("Something wrong!");
    }

    public void showMessage(String message){
        getViewState().showMessage(message);
    }

    public void setHistoryElementId(long id) {
        elementId = id;
        elementRepository.getWeatherDbModel(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
