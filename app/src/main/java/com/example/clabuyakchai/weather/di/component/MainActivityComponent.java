package com.example.clabuyakchai.weather.di.component;

import com.example.clabuyakchai.weather.di.annotation.MainActivityScope;
import com.example.clabuyakchai.weather.presenter.MapPresenter;
import com.example.clabuyakchai.weather.presenter.WeatherPresenter;
import com.example.clabuyakchai.weather.ui.activity.MainActivity;
import com.example.clabuyakchai.weather.ui.fragment.MapFragment;
import com.example.clabuyakchai.weather.ui.fragment.WeatherFragment;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@MainActivityScope
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(WeatherFragment weatherFragment);
    void inject(MapFragment mapFragment);
    void inject(WeatherPresenter weatherPresenter);
    void inject(MapPresenter mapPresenter);
}
