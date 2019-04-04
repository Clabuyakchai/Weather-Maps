package com.example.clabuyakchai.weather.di.module;

import com.example.clabuyakchai.weather.data.remote.WeatherApi;
import com.example.clabuyakchai.weather.di.annotation.WeatherRetrofit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WeatherModule {

    @Provides
    @Singleton
    public WeatherApi provideWeatherApi(@WeatherRetrofit Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    @Singleton
    @WeatherRetrofit
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}