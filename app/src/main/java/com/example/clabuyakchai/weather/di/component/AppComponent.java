package com.example.clabuyakchai.weather.di.component;

import android.content.Context;

import com.example.clabuyakchai.weather.data.remote.BelrosstrakhApi;
import com.example.clabuyakchai.weather.data.remote.WeatherApi;
import com.example.clabuyakchai.weather.di.module.ContextAppModule;
import com.example.clabuyakchai.weather.di.module.BelrosstrakhModule;
import com.example.clabuyakchai.weather.di.module.WeatherModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {WeatherModule.class, BelrosstrakhModule.class, ContextAppModule.class})
@Singleton
public interface AppComponent {
    WeatherApi getWeatherService();
    BelrosstrakhApi getBelrosstrakhService();
    Context getContextApplication();
}