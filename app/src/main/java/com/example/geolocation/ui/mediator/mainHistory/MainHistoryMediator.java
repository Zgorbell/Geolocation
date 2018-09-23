package com.example.geolocation.ui.mediator.mainHistory;

import android.util.Log;

import com.example.geolocation.ui.fragment.history.HistoryPresenter;
import com.example.geolocation.ui.fragment.main.MainPresenter;

public class MainHistoryMediator implements MainColleagueHistoryMediator, HistoryColleagueMainMediator {

    private MainPresenter mainPresenter;
    private HistoryPresenter historyPresenter;

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void setHistoryPresenter(HistoryPresenter historyPresenter) {
        this.historyPresenter = historyPresenter;
    }

    @Override
    public void onDatabaseChanged() {
        if(historyPresenter != null) historyPresenter.onDatabaseChanged();
    }


}
