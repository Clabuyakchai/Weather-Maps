package com.example.clabuyakchai.weather.di.module;

import com.example.clabuyakchai.weather.data.remote.BelrosstrakhApi;
import com.example.clabuyakchai.weather.di.annotation.BelrosstrakhRetrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BelrosstrakhModule {

    @Provides
    @Singleton
    public BelrosstrakhApi provideBelgosstrakhApi(@BelrosstrakhRetrofit Retrofit retrofit){
        return retrofit.create(BelrosstrakhApi.class);
    }

    @Provides
    @Singleton
    @BelrosstrakhRetrofit
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl("http://ais.brs.by:38516/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build();
    }
}
