package com.example.clabuyakchai.weather.presenter;

import com.example.clabuyakchai.weather.data.remote.model.weather.City;

public interface Observer {
    void update(City city);
    void error(String error);
}
