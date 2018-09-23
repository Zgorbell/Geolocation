package com.example.geolocation.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.geolocation.data.db.model.WeatherDbModel;
import com.example.geolocation.data.db.room.WeatherDao;

@Database(entities = {WeatherDbModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
