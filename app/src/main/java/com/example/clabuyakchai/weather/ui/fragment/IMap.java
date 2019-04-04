package com.example.clabuyakchai.weather.ui.fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.clabuyakchai.weather.data.model.belrosstrakh.OfficeInfo;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface IMap extends MvpView {
    void setMarker(List<OfficeInfo> officeInfoList);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String error);
}
