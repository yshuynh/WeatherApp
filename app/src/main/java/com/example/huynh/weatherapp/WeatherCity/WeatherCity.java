package com.example.huynh.weatherapp.WeatherCity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.huynh.weatherapp.MyCityList;
import com.example.huynh.weatherapp.Observer.Observer;
import com.example.huynh.weatherapp.R;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class WeatherCity extends Fragment implements Observer {

    public static WeatherCity instance = null;
    ViewPager mViewPager;
    CustomViewPagerAdapter customViewPagerAdapter;
    String cityName;

    public static WeatherCity getInstance() {
        if (instance == null) {
            instance = new WeatherCity();
        }
        return instance;
    }

    @SuppressLint("ValidFragment")
    public WeatherCity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weather_city_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager = view.findViewById(R.id.viewpager);
        customViewPagerAdapter = new CustomViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(customViewPagerAdapter);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void update() {
        Log.d("AAA", "update: ");
        if (customViewPagerAdapter != null) {
            customViewPagerAdapter.ReloadPage();
            mViewPager.setAdapter(customViewPagerAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("AAA", "onResume: ");
    }
}
