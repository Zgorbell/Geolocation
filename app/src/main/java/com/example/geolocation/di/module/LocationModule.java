package com.example.geolocation.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

@Module
public class LocationModule {

    @Singleton
    @Provides
    ReactiveLocationProvider getReactiveLocationProvider(Context context){
        return new ReactiveLocationProvider(context);
    }
}
