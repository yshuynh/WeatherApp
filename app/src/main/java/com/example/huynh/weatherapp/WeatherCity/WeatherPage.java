package com.example.huynh.weatherapp.WeatherCity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huynh.weatherapp.MainActivity;
import com.example.huynh.weatherapp.R;
import com.example.huynh.weatherapp.Retrofit2.APIUtils;
import com.example.huynh.weatherapp.Retrofit2.DataClient;
import com.example.huynh.weatherapp.Weather5daysPOJO.Weather5daysPOJO;
import com.example.huynh.weatherapp.WeatherCurrentPOJO.WeatherCurrentPOJO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class WeatherPage extends Fragment{

    String cityName;
    TextView textView;
    WeatherCurrentPOJO detail = null;
    Weather5daysPOJO detail5day = null;

    @SuppressLint("ValidFragment")
    public WeatherPage(String cityName) {
        this.cityName = cityName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weather_page_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Custom View Here

        TextView add = view.findViewById(R.id.textview_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().OpenAddNewFragment();
            }
        });

        if (detail != null) {
            UpdateLayout(detail, view);
            UpdateLayout5day(detail5day, view);
            return;
        }

        DataClient dataClient = APIUtils.getData();
        Call<WeatherCurrentPOJO> callback = dataClient.GetWeather(APIUtils.API_KEY, cityName);
        callback.enqueue(new Callback<WeatherCurrentPOJO>() {
            @Override
            public void onResponse(Call<WeatherCurrentPOJO> call, Response<WeatherCurrentPOJO> response) {
                detail = response.body();
                UpdateLayout(response.body(), view);
            }

            @Override
            public void onFailure(Call<WeatherCurrentPOJO> call, Throwable t) {
                Log.d("BBB", "onFailure: " + t.getMessage());
            }
        });
        Call<Weather5daysPOJO> callback5day = dataClient.GetWeather5days(APIUtils.API_KEY, cityName);
        callback5day.enqueue(new Callback<Weather5daysPOJO>() {
            @Override
            public void onResponse(Call<Weather5daysPOJO> call, Response<Weather5daysPOJO> response) {
                detail5day = response.body();
                UpdateLayout5day(detail5day, view);
            }

            @Override
            public void onFailure(Call<Weather5daysPOJO> call, Throwable t) {
                Log.d("BBB", "onFailure:5day " + t.getMessage());
            }
        });
    }

    String GetLetterWeather(String text) {
        switch (text) {
            case "01d" : {
                return("A");
            }
            case "02d" : {
                return("C");
            }
            case "03d" : {
                return("G");
            }
            case "04d" : {
                return("P");
            }
            case "09d" : {
                return("S");
            }
            case "10d" : {
                return("F");
            }
            case "11d" : {
                return("V");
            }
            case "13d" : {
                return("O");
            }
            case "50d" : {
                return("N");
            }
            case "01n" : {
                return("I");
            }
            case "02n" : {
                return("V");
            }
            case "03n" : {
                return("G");
            }
            case "04n" : {
                return("P");
            }
            case "09n" : {
                return("S");
            }
            case "10n" : {
                return("K");
            }
            case "11n" : {
                return("V");
            }
            case "13n" : {
                return("O");
            }
            case "50n" : {
                return("N");
            }
            default:{
                return("A");
            }
        }
    }

    void UpdateLayout(WeatherCurrentPOJO detail, View view) {
        TextView textView = view.findViewById(R.id.textview_degree);
        double degree = detail.getMain().getTemp() - 273;
        String s = String.format("%.0f", degree);
        textView.setText(s + (char) 0x00B0);

        RelativeLayout re = view.findViewById(R.id.rela_layout);
        if (degree > 25) {
            re.setBackgroundResource(R.drawable.bg_hot);
        } else if (degree > 20) {
            re.setBackgroundResource(R.drawable.bg_clear);
        } else if (degree > 15){
            re.setBackgroundResource(R.drawable.bg_cloud);
        } else {
            re.setBackgroundResource(R.drawable.bg_freeze);
        }

        TextView city = view.findViewById(R.id.textview_city);
        city.setText(cityName + ", " + detail.getSys().getCountry());
        Log.d("BBB", "UpdateLayout: " + cityName);

//        ImageView img = view.findViewById(R.id.imageview_icon);
//        Log.d("AAA", "UpdateLayout: http://openweathermap.org/img/w/" + detail.getWeather().get(0).getIcon());
//        Picasso.get().load("http://openweathermap.org/img/w/" + detail.getWeather().get(0).getIcon() + ".png").into(img);

        textView = view.findViewById(R.id.textview_weather);
        textView.setText(GetLetterWeather(detail.getWeather().get(0).getIcon()));

        textView = view.findViewById(R.id.textview_condition);
        textView.setText(detail.getWeather().get(0).getDescription());

    }

    void UpdateLayout5day(Weather5daysPOJO detail5day, View view) {
        TextView dayIcon = view.findViewById(R.id.textview_icon1);
        dayIcon.setText(GetLetterWeather(detail5day.getList().get(8).getWeather().get(0).getIcon()));

        dayIcon = view.findViewById(R.id.textview_icon2);
        dayIcon.setText(GetLetterWeather(detail5day.getList().get(16).getWeather().get(0).getIcon()));

        dayIcon = view.findViewById(R.id.textview_icon3);
        dayIcon.setText(GetLetterWeather(detail5day.getList().get(24).getWeather().get(0).getIcon()));

        TextView dayName = view.findViewById(R.id.textview_nextday1);
        Log.d("TTT", "UpdateLayout5day: " + dayName);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Date newDate = new Date(date.getTime() + 24* 60 * 60 * 1000L);
        dayName.setText(new SimpleDateFormat("EE", Locale.ENGLISH).format(newDate.getTime()));

        dayName = view.findViewById(R.id.textview_nextday2);
        newDate = new Date(newDate.getTime() + 24* 60 * 60 * 1000L);
        dayName.setText(new SimpleDateFormat("EE", Locale.ENGLISH).format(newDate.getTime()));

        dayName = view.findViewById(R.id.textview_nextday3);
        newDate = new Date(newDate.getTime() + 24* 60 * 60 * 1000L);
        dayName.setText(new SimpleDateFormat("EE", Locale.ENGLISH).format(newDate.getTime()));

    }
}
