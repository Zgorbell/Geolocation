package com.example.geolocation.ui.fragment.history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.geolocation.App;
import com.example.geolocation.data.db.model.WeatherDbModel;
import com.example.geolocation.ui.activity.element.ElementActivity;
import com.example.geolocation.ui.mediator.mainHistory.MainColleagueHistoryMediator;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryMvpView> {

    @Inject
    MainColleagueHistoryMediator mainColleagueHistoryMediator;
    private HistoryRepository historyRepository;

    public HistoryPresenter() {
        App.getAppComponent().inject(this);
        mainColleagueHistoryMediator.setHistoryPresenter(this);
    }

    public void onCreated(){
        historyRepository = new HistoryRepository(this);
        historyRepository.loadWeatherList();
    }

    public void onLoadingMessage(String message){
        getViewState().showMessage(message);
    }

    public void onLoadingResult(List<WeatherDbModel> list){
        getViewState().setAdapter(list);
    }

    public void onDatabaseChanged(){
        historyRepository.loadWeatherList();
    }

    public void elementChecked(long id){
        getViewState().startActivity(ElementActivity.class);
        historyRepository.setSelectedElementId(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainColleagueHistoryMediator.setHistoryPresenter(null);
    }
}
