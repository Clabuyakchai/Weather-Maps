package com.example.clabuyakchai.weather.ui.fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.clabuyakchai.weather.data.remote.model.weather.City;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IWeather extends MvpView {
    void showWeather(City city);
    void showProgressBar(int status);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String error);
}
