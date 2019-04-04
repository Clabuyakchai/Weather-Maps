package com.example.clabuyakchai.weather.model;

import com.example.clabuyakchai.weather.data.remote.model.weather.City;
import com.example.clabuyakchai.weather.presenter.Observer;

public interface Observable {
    void registerObserver(Observer observer);
    void unregisterObserver();
    void notifyObserver(City city);
    void showError(String error);
}
