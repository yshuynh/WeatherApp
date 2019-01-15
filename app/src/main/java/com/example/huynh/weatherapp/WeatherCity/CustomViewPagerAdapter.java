package com.example.huynh.weatherapp.WeatherCity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.huynh.weatherapp.MyCityList;

import java.util.ArrayList;

public class CustomViewPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments;

    public void ReloadPage() {
        fragments = new ArrayList<Fragment>();
        for (String s : MyCityList.getInstance().getmList()) {
            fragments.add(new WeatherPage(s));
        }
        notifyDataSetChanged();
    }

    CustomViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragments = new ArrayList<Fragment>();
        for (String s : MyCityList.getInstance().getmList()) {
            fragments.add(new WeatherPage(s));
        }
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        frag = fragments.get(position);
        return frag;
    }

    @Override
    public int getCount() {
        return MyCityList.getInstance().getmList().size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        return title;
    }
}