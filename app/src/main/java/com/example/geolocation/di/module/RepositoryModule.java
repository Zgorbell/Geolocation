package com.example.geolocation.di.module;

import com.example.geolocation.ui.activity.element.ElementRepository;
import com.example.geolocation.ui.fragment.main.MainRepository;
import com.example.geolocation.ui.mediator.mainHistory.HistoryColleagueMainMediator;
import com.example.geolocation.ui.mediator.mainHistory.MainHistoryMediator;
import com.example.geolocation.ui.mediator.mainHistory.MainColleagueHistoryMediator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    MainColleagueHistoryMediator getMainSideMediator(MainHistoryMediator mainHistoryMediator){
        return mainHistoryMediator;
    }


    @Provides
    HistoryColleagueMainMediator getHistorySideMediator(MainHistoryMediator mainHistoryMediator){
        return mainHistoryMediator;
    }

    @Singleton
    @Provides
    MainHistoryMediator getMainHistoryMediator(){
        return new MainHistoryMediator();
    }

    @Singleton
    @Provides
    ElementRepository getElementRepository(){
        return new ElementRepository();
    }

    @Provides
    @Singleton
    MainRepository getMainRepository(){
        return new MainRepository();
    }
}
