package com.example.clabuyakchai.weather.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.clabuyakchai.weather.WeatherApp;
import com.example.clabuyakchai.weather.data.model.belrosstrakh.OfficeInfo;
import com.example.clabuyakchai.weather.model.MapModel;
import com.example.clabuyakchai.weather.ui.fragment.IMap;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class MapPresenter extends MvpPresenter<IMap> {
    @Inject
    MapModel mapModel;
    List<OfficeInfo> officeInfoList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MapPresenter() {
        WeatherApp.mainActivityComponent.inject(this);
    }

    public void requestBrsApi() {
        Disposable disposable = mapModel.requestBrsApi()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(brs -> {
                    officeInfoList = brs;
                    getViewState().setMarker(brs);
                }, Throwable::printStackTrace);

        compositeDisposable.add(disposable);
    }

    public void onMapReady() {
        if (officeInfoList == null) {
            requestBrsApi();
        } else {
            getViewState().setMarker(officeInfoList);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
