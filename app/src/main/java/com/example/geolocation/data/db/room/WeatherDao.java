package com.example.geolocation.data.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.geolocation.data.db.model.WeatherDbModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class WeatherDao {

    @Insert
    public abstract long insert(WeatherDbModel weatherDbModel);

    @Query("Select * from weatherdbmodel order by id desc")
    public abstract Single<List<WeatherDbModel>> getModels();

    @Query("Select * from weatherdbmodel where id = :id  ")
    public abstract Single<WeatherDbModel> getModel(long id);
}
