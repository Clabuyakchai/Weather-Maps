package com.example.clabuyakchai.weather.presenter;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.clabuyakchai.weather.WeatherApp;
import com.example.clabuyakchai.weather.data.remote.model.weather.City;
import com.example.clabuyakchai.weather.model.WeatherModel;
import com.example.clabuyakchai.weather.ui.fragment.IWeather;

import javax.inject.Inject;

@InjectViewState
public class WeatherPresenter extends MvpPresenter<IWeather> implements Observer {
    @Inject
    WeatherModel model;

    public WeatherPresenter(){
        WeatherApp.mainActivityComponent.inject(this);
        model.registerObserver(this);
    }

    public void requestToServer(String city){
        getViewState().showProgressBar(View.VISIBLE);
        model.requestToServer(city);
    }

    @Override
    public void update(City city) {
        getViewState().showProgressBar(View.INVISIBLE);
        getViewState().showWeather(city);
    }

    @Override
    public void error(String error){
        getViewState().showProgressBar(View.INVISIBLE);
        getViewState().showError(error);
    }

    @Override
    public void onDestroy() {
        model.unregisterObserver();
        super.onDestroy();
    }
}
