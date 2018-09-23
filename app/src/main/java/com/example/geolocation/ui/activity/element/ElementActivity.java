package com.example.geolocation.ui.activity.element;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.geolocation.R;
import com.example.geolocation.constant.Constants;
import com.example.geolocation.data.db.model.WeatherDbModel;
import com.example.geolocation.ui.fragment.main.MainPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ElementActivity extends MvpAppCompatActivity implements ElementMvpView {

    @InjectPresenter
    ElementPresenter elementPresenter;
    @BindView(R.id.textViewLon)
    TextView textViewLon;
    @BindView(R.id.textViewLat)
    TextView textViewLat;
    @BindView(R.id.textViewReverseGeocoding)
    TextView textViewReverseGeocoding;
    @BindView(R.id.textViewWeather)
    TextView textViewWeather;
    @BindView(R.id.textViewTemperature)
    TextView textViewTemperature;
    @BindView(R.id.textViewHumidity)
    TextView textViewHumidity;
    @BindView(R.id.textViewPressure)
    TextView textViewPressure;
    @BindView(R.id.textViewWindSpeed)
    TextView textViewWindSpeed;
    @BindView(R.id.textViewWindCourse)
    TextView textViewWindCourse;
    @BindView(R.id.textViewClouds)
    TextView textViewClouds;
    @BindView(R.id.buttonCheckWeather)
    Button buttonCheckWeather;
    @BindView(R.id.textViewTime)
    TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_element);
        ButterKnife.bind(this);
        buttonCheckWeather.setVisibility(View.GONE);

    }

    @Override
    public void setInfo(WeatherDbModel weather) {
        textViewWeather.setText(weather.getWeather());
        textViewTemperature.setText(weather.getTemperature());
        textViewHumidity.setText(weather.getHumidity());
        textViewPressure.setText(weather.getPressure());
        textViewWindSpeed.setText(weather.getWindSpeed());
        textViewWindCourse.setText(weather.getWindCourse());
        textViewClouds.setText(weather.getClouds());
        textViewLat.setText(weather.getLat());
        textViewLon.setText(weather.getLon());
        textViewReverseGeocoding.setText(weather.getRegion());
        textViewTime.setText(getDate(weather.getTimestamp()));
    }

    private String getDate(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        Date date = new Date(timeStamp);
        return simpleDateFormat.format(date);
    }

    private Snackbar snackbar;
    public void showMessage(String message){
        if(snackbar == null || !snackbar.isShown()) {
            snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

}
