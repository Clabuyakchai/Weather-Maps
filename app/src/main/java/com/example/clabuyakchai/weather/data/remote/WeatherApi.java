package com.example.clabuyakchai.weather.data.remote;

import com.example.clabuyakchai.weather.data.remote.model.weather.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/weather?&units=metric&APPID=ba726a4abd54925d2167ccd1e5995d1a")
    Call<City> getWeather(@Query("q") String city);
}
