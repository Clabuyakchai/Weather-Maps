package com.example.clabuyakchai.weather.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.clabuyakchai.weather.R;
import com.example.clabuyakchai.weather.WeatherApp;
import com.example.clabuyakchai.weather.data.model.belrosstrakh.OfficeInfo;
import com.example.clabuyakchai.weather.presenter.MapPresenter;
import com.example.clabuyakchai.weather.ui.common.ClusterRenderer;
import com.example.clabuyakchai.weather.ui.common.OfficeItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

import javax.inject.Inject;

public class MapFragment extends MvpAppCompatFragment implements OnMapReadyCallback, IMap{
    private static final String TAG = "MapFragment";
    public static final String TITLE = "MAP";
    private GoogleMap map;
    private MapView mapView;
    private Bundle bundle;
    @Inject
    Context context;
    @InjectPresenter
    MapPresenter mapPresenter;
    private ClusterManager<OfficeItem> clusterManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        WeatherApp.mainActivityComponent.inject(this);
        bundle = savedInstanceState;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_map, container, false);

        MapsInitializer.initialize(context);

        mapView = view.findViewById(R.id.map);
        mapView.onCreate(bundle);
        mapView.getMapAsync(this);

        return view;
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        clusterManager = new ClusterManager<>(context, map);

        map.setOnCameraIdleListener(clusterManager);
        map.setOnInfoWindowClickListener(clusterManager);
        LatLng belarus = new LatLng(53.9, 27.5);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(belarus, 5.8f));
        ClusterRenderer clusterRenderer = new ClusterRenderer(context, map, clusterManager);
        clusterManager.setRenderer(clusterRenderer);

        mapPresenter.onMapReady();
    }

    @Override
    public void setMarker(List<OfficeInfo> officeItemList) {
        for (int i = 0; i < officeItemList.size(); i++) {
            clusterManager.addItem(
                    new OfficeItem(
                            officeItemList.get(i).getLatitude(), officeItemList.get(i).getLongitude(),
                            String.valueOf(officeItemList.get(i).getId()),
                            officeItemList.get(i).getCity() + ", " + officeItemList.get(i).getAddress()));
            clusterManager.cluster();
        }
    }

    @Override
    public void showError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
