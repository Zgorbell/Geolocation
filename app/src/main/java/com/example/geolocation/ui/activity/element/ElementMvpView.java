package com.example.geolocation.ui.activity.element;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.geolocation.data.db.model.WeatherDbModel;

interface ElementMvpView  extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setInfo(WeatherDbModel weatherDbModel);

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);
}
