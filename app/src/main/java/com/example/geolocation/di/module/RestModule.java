package com.example.geolocation.di.module;

import android.content.res.Resources;

import com.example.geolocation.R;
import com.example.geolocation.data.net.WeatherApi;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestModule {
    @Singleton
    @Provides
    Retrofit provideRetrofit(Resources resources){
        return new Retrofit.Builder()
                .baseUrl(resources.getString(R.string.weather_base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build();
    }

    @Singleton
    @Provides
    WeatherApi provideApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
