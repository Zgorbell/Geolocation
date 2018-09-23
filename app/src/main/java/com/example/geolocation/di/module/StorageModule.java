package com.example.geolocation.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.geolocation.data.db.AppDatabase;
import com.example.geolocation.data.shared.SelectedElementItem;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    private String databaseName;

    public StorageModule(String databaseName) {
        this.databaseName = databaseName;
    }

    @Singleton
    @Provides
    AppDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, databaseName).build();
    }

    @Singleton
    @Provides
    SelectedElementItem getSelectedElementItem(Context context){
        return new SelectedElementItem(context);
    }
}
