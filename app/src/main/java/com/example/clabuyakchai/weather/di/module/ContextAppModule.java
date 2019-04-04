package com.example.clabuyakchai.weather.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextAppModule {
    private final Context appContext;

    public ContextAppModule(Context context) {
        this.appContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return appContext;
    }
}
