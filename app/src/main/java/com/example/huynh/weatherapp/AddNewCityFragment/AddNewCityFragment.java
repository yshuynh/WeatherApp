package com.example.huynh.weatherapp.AddNewCityFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huynh.weatherapp.MainActivity;
import com.example.huynh.weatherapp.MyCityList;
import com.example.huynh.weatherapp.R;
import com.example.huynh.weatherapp.WeatherCity.WeatherCity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddNewCityFragment extends Fragment {
    public static AddNewCityFragment instance = null;

    public static AddNewCityFragment getInstance() {
        if (instance == null) {
            instance = new AddNewCityFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_new_city_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = view.findViewById(R.id.button);
        final EditText edt = view.findViewById(R.id.edittext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCityList.getInstance().AddNewCity(edt.getText().toString());
                MainActivity.getInstance().BackFragment(WeatherCity.getInstance());
                MainActivity.getInstance().GetToast("Succesfull");
            }
        });

        ArrayList<String> mList = new ArrayList<String>();
        mList.add("London");
        mList.add("Danang");
        mList.add("Thanh pho ho chi minh");
        mList.add("Saigon");
        mList.add("Hanoi");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), mList);
        recyclerView.setAdapter(adapter);

    }
}
