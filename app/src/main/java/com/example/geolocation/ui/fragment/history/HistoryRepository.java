package com.example.geolocation.ui.fragment.history;

import com.example.geolocation.App;
import com.example.geolocation.data.db.AppDatabase;
import com.example.geolocation.data.db.model.WeatherDbModel;
import com.example.geolocation.data.shared.SelectedElementItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryRepository {

    @Inject
    AppDatabase appDatabase;
    @Inject
    SelectedElementItem selectedElementItem;
    private HistoryPresenter historyPresenter;

    public HistoryRepository(HistoryPresenter historyPresenter) {
        this.historyPresenter = historyPresenter;
        App.getAppComponent().inject(this);
    }

    public void setSelectedElementId(long id){
        selectedElementItem.setCurrentElementId(id);
    }

    public void loadWeatherList(){
        appDatabase.weatherDao()
                .getModels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<WeatherDbModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<WeatherDbModel> weatherDbModels) {
                        if(historyPresenter != null){
                            historyPresenter.onLoadingResult(weatherDbModels);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(historyPresenter != null){
                            historyPresenter.onLoadingMessage("Fatal error downloading from db!");
                        }
                    }
                });
    }
}
