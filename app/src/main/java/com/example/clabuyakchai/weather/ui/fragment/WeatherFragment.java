package com.example.clabuyakchai.weather.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.clabuyakchai.weather.R;
import com.example.clabuyakchai.weather.WeatherApp;
import com.example.clabuyakchai.weather.data.remote.model.weather.City;
import com.example.clabuyakchai.weather.presenter.WeatherPresenter;

public class WeatherFragment extends MvpAppCompatFragment implements IWeather {
    private static final String TAG = "WeatherFragment";
    public static final String TITLE = "Weather";

    private TextView cityTx;
    private TextView countryCodeTx;
    private TextView descriptionWeatherTx;
    private TextView iconWeather;
    private TextView tempTx;
    private ProgressBar progress;

    @InjectPresenter
    WeatherPresenter weatherPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        WeatherApp.mainActivityComponent.inject(WeatherFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        cityTx = view.findViewById(R.id.city);
        countryCodeTx = view.findViewById(R.id.code_country);
        descriptionWeatherTx = view.findViewById(R.id.weather_description);
        iconWeather = view.findViewById(R.id.icon_weather);
        tempTx = view.findViewById(R.id.temp);
        progress = view.findViewById(R.id.progress_weather);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        iconWeather.setTypeface(type);

        return view;
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                weatherPresenter.requestToServer(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void showWeather(City city) {
        if(city != null) {
            cityTx.setText(city.getName());
            countryCodeTx.setText(city.getSys().getCountry());
            descriptionWeatherTx.setText(city.getWeather().get(0).getDescription());
            tempTx.setText(String.valueOf(city.getMain().getTemp()) + " \u2103");
            setIconWeather(city.getWeather().get(0).getId(), city.getSys().getSunrise(), city.getSys().getSunset(), city.getDt());
        }
    }

    @Override
    public void showProgressBar(int status) {
        progress.setVisibility(status);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }

    private void setIconWeather(int id, int sunrise, int sunset, int dt){
        String Id;
        int icon;
        if(dt > sunrise && dt < sunset){
            Id = "wi_owm_day_" + id;
            icon = getResources().getIdentifier(Id, "string", getActivity().getPackageName());
        } else {
            Id = "wi_owm_night_" + id;
            icon = getResources().getIdentifier(Id, "string", getActivity().getPackageName());
        }
        iconWeather.setText(icon);
    }
}
