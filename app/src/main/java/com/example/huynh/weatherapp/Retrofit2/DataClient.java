package com.example.huynh.weatherapp.Retrofit2;

import com.example.huynh.weatherapp.WeatherCurrentPOJO.WeatherCurrentPOJO;
import com.example.huynh.weatherapp.Weather5daysPOJO.Weather5daysPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataClient {
    @GET("weather")
    Call<WeatherCurrentPOJO> GetWeather(@Query("APPID") String api_key, @Query("q") String cityName);

    @GET("forecast")
    Call<Weather5daysPOJO> GetWeather5days(@Query("APPID") String api_key, @Query("q") String cityName);
}
