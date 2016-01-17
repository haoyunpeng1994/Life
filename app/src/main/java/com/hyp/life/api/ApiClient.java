package com.hyp.life.api;

import android.content.Context;
import com.hyp.life.Constants;
import com.hyp.life.model.Travel.Travel;
import com.hyp.life.model.weather.Weather;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by acer on 2015/11/15.
 */
public class ApiClient {
    private Api api = null;
    private String ROOT_URL = null;
    private static ApiClient instance;

    private ApiClient(Context context){
        ROOT_URL = "http://apis.baidu.com";
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ROOT_URL + "/").build();
        api = restAdapter.create(Api.class);
    }

    public static ApiClient getInstance(Context context){
        if(instance == null){
            instance = new ApiClient(context);
        }
        return instance;
    }

    public static void destroyInstance(){
        instance = null;
    }

    public String getROOTURL()
    {
        return ROOT_URL;
    }
    public void getTravle(int page, Callback<Travel> callback)
    {
        String key = Constants.ApiKey;
        api.getTravel(key,page,callback);
    }
    public void getWeather(String area,Callback<Weather> callback)
    {
        String key = Constants.ApiKey;
        api.getWeather(key,area,1,callback);
    }
}
