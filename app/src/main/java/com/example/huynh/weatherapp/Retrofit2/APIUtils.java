package com.example.huynh.weatherapp.Retrofit2;

public class APIUtils {
    public static final String API_KEY = "ccfb8ec4f76fc5420ba1f23e2d3e92ea";
    public static final String Base_Url = "https://api.openweathermap.org/data/2.5/";
    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}