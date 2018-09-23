package com.example.geolocation.ui.fragment.history;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.geolocation.data.db.model.WeatherDbModel;

import java.util.List;

public interface HistoryMvpView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);

    @StateStrategyType(SkipStrategy.class)
    void setAdapter(List<WeatherDbModel> list);

    @StateStrategyType(SkipStrategy.class)
    void startActivity(Class activity);
}
