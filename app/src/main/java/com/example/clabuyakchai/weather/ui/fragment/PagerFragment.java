package com.example.clabuyakchai.weather.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clabuyakchai.weather.R;
import com.example.clabuyakchai.weather.ui.adapter.PagerAdapter;

public class PagerFragment extends Fragment {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);

        pagerAdapter = new PagerAdapter(getFragmentManager());
        pager = view.findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(3);

        return view;
    }

    public static PagerFragment newInstance(){
        return new PagerFragment();
    }
}
