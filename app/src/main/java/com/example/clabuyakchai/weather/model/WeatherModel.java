package com.example.clabuyakchai.weather.model;

import android.util.Log;

import com.example.clabuyakchai.weather.data.remote.model.weather.City;
import com.example.clabuyakchai.weather.data.remote.WeatherApi;
import com.example.clabuyakchai.weather.presenter.Observer;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherModel implements Observable {
    private static final String TAG = "WeatherModel";
    private Observer observer;
    @Inject
    WeatherApi service;

    @Inject
    public WeatherModel() {
    }

    public void requestToServer(String city) {
        service.getWeather(city).enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                if (response.isSuccessful()) {
                    notifyObserver(response.body());
                    Log.d(TAG, response.body().getName());
                } else {
                    showError("Error");
                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                showError("onFailure");
            }
        });
    }

    @Override
    public void registerObserver(Observer ob) {
        if (observer == null) {
            this.observer = ob;
        }
    }

    @Override
    public void unregisterObserver() {
        this.observer = null;
    }

    @Override
    public void notifyObserver(City city) {
        observer.update(city);
    }

    @Override
    public void showError(String error) {
        observer.error(error);
    }
}
