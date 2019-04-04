package com.example.clabuyakchai.weather.data.remote;

import com.example.clabuyakchai.weather.data.remote.model.belrosstrakh.Belrosstrakh;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface BelrosstrakhApi {
    @GET("ords/mobile_user/v1_3/GetOfficesInfo/")
    Single<Belrosstrakh> getOfficeInfo();
}
