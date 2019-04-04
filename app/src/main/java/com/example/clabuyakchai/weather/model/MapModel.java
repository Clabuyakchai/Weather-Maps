package com.example.clabuyakchai.weather.model;

import com.example.clabuyakchai.weather.data.model.belrosstrakh.OfficeInfo;
import com.example.clabuyakchai.weather.data.remote.model.belrosstrakh.Belrosstrakh;
import com.example.clabuyakchai.weather.data.remote.BelrosstrakhApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class MapModel {
    @Inject
    BelrosstrakhApi service;

    @Inject
    public MapModel() {

    }

    public Single<List<OfficeInfo>> requestBrsApi() {
        return service.getOfficeInfo()
                .flatMap(brs -> Single.just(mapBelrosstrakhToOfficeInfo(brs)));
    }

    private List<OfficeInfo> mapBelrosstrakhToOfficeInfo(Belrosstrakh belrosstrakh){
        List<OfficeInfo> officeInfoList = new ArrayList<>();

        if(belrosstrakh != null){
            for (int i = 0; i < belrosstrakh.getItems().size(); i++) {
                officeInfoList.add(new OfficeInfo(belrosstrakh.getItems().get(i).getId(),
                        belrosstrakh.getItems().get(i).getAddress(),
                        belrosstrakh.getItems().get(i).getLatitude(),
                        belrosstrakh.getItems().get(i).getLongitude(),
                        belrosstrakh.getItems().get(i).getCity()));
            }
        }

        return officeInfoList;
    }
}
