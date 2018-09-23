package com.example.geolocation.ui.fragment.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.geolocation.App;
import com.example.geolocation.R;
import com.example.geolocation.constant.Constants;
import com.example.geolocation.ui.activity.main.MainActivity;
import com.google.android.gms.location.LocationRequest;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

public class MainFragment extends MvpAppCompatFragment implements MainMvpView {

    @InjectPresenter
    MainPresenter mainPresenter;
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
    @Inject
    ReactiveLocationProvider locationProvider;
    private MainActivity mainActivity;
    private Disposable locationDisposable;
    private Unbinder unbinder;
    private Observable<List<Address>> reverseGeocodeObservable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
        App.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        buttonCheckWeather.setOnClickListener(v -> mainPresenter.buttonCheckWeatherClicked());
        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mainActivity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    MainActivity.REQUEST_ACCESSES_FINE_LOCATION);
        }else{
            LocationRequest request = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(100);


            locationDisposable = locationProvider.getUpdatedLocation(request)
                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(l -> mainPresenter.onLocationChanged(l.getLatitude(), l.getLongitude()));
        }
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(locationDisposable != null){
            locationDisposable.dispose();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showMessage(String message) {
        mainActivity.showMessage(message);
    }

    @Override
    public void setCurrentLocation(String lon, String lat) {
        textViewLon.setText(lon);
        textViewLat.setText(lat);
    }

    @Override
    public void setCurrentReverseLocation(String location) {
        textViewReverseGeocoding.setText(location);
    }

    @Override
    public void setWeather(Map<String, String> weather) {
        textViewWeather.setText(weather.get(Constants.WEATHER));
        textViewTemperature.setText(weather.get(Constants.TEMPERATURE));
        textViewHumidity.setText(weather.get(Constants.HUMIDITY));
        textViewPressure.setText(weather.get(Constants.PRESSURE));
        textViewWindSpeed.setText(weather.get(Constants.WIND_SPEED));
        textViewWindCourse.setText(weather.get(Constants.WIND_COURSE));
        textViewClouds.setText(weather.get(Constants.CLOUDS));
    }

    public void getReverseGeocode(double lat, double lon) {
        reverseGeocodeObservable = locationProvider
                .getReverseGeocodeObservable(lat, lon, Integer.MAX_VALUE);

        reverseGeocodeObservable
//                .debounce(60000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Address>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Address> addresses) {
                        mainPresenter.reverseLocationChanged(addresses);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
