package com.example.geolocation;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.support.v7.widget.AppCompatCheckedTextView;

import com.example.geolocation.di.component.AppComponent;
import com.example.geolocation.di.component.DaggerAppComponent;
import com.example.geolocation.di.module.AppModule;
import com.example.geolocation.di.module.RestModule;
import com.example.geolocation.di.module.StorageModule;

import javax.inject.Inject;

public class App extends Application {
    private static AppComponent appComponent;
    private static Context context;
    private final String databaseName = "database";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .restModule(new RestModule())
                .storageModule(new StorageModule(databaseName))
                .build();
    }

    public static Context getContext(){
        return context;
    }

    public static Resources getAppResources(){ return context.getResources();}

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
