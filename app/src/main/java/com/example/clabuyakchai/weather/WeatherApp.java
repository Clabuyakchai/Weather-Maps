package com.example.clabuyakchai.weather;

import android.app.Application;

import com.example.clabuyakchai.weather.di.component.AppComponent;
import com.example.clabuyakchai.weather.di.component.DaggerAppComponent;
import com.example.clabuyakchai.weather.di.component.DaggerMainActivityComponent;
import com.example.clabuyakchai.weather.di.component.MainActivityComponent;
import com.example.clabuyakchai.weather.di.module.ContextAppModule;

public class WeatherApp extends Application {
    public static MainActivityComponent mainActivityComponent;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .contextAppModule(new ContextAppModule(this))
                .build();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .build();
    }
}
