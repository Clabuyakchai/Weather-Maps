package com.example.clabuyakchai.weather.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.clabuyakchai.weather.R;
import com.example.clabuyakchai.weather.WeatherApp;
import com.example.clabuyakchai.weather.ui.fragment.PagerFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherApp.mainActivityComponent.inject(this);

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = PagerFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
