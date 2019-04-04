package com.example.clabuyakchai.weather.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.clabuyakchai.weather.ui.fragment.MapFragment;
import com.example.clabuyakchai.weather.ui.fragment.WeatherFragment;


public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return WeatherFragment.newInstance();
            case 1:
                return MapFragment.newInstance();
            default:
                throw new IllegalArgumentException();

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return WeatherFragment.TITLE;
            case 1:
                return MapFragment.TITLE;
            default:
                throw new IllegalArgumentException();

        }
    }
}
