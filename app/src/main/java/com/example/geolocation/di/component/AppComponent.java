package com.example.geolocation.di.component;

import com.example.geolocation.di.module.AppModule;
import com.example.geolocation.di.module.LocationModule;
import com.example.geolocation.di.module.RepositoryModule;
import com.example.geolocation.di.module.RestModule;
import com.example.geolocation.di.module.StorageModule;
import com.example.geolocation.ui.activity.element.ElementPresenter;
import com.example.geolocation.ui.activity.element.ElementRepository;
import com.example.geolocation.ui.fragment.history.HistoryPresenter;
import com.example.geolocation.ui.fragment.history.HistoryRepository;
import com.example.geolocation.ui.fragment.main.MainFragment;
import com.example.geolocation.ui.fragment.main.MainPresenter;
import com.example.geolocation.ui.fragment.main.MainRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,
        RestModule.class,
        LocationModule.class,
        StorageModule.class,
        RepositoryModule.class})
public interface AppComponent {

    void inject(MainFragment presenter);
    void inject(MainPresenter presenter);
    void inject(MainRepository repository);

    void inject(HistoryPresenter presenter);
    void inject(HistoryRepository repository);

    void inject(ElementPresenter presenter);
    void inject(ElementRepository repository);
}
