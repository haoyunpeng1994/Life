package com.hyp.life.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.hyp.life.R;
import com.hyp.life.api.ApiClient;
import com.hyp.life.model.weather.Weather;
import com.hyp.life.util.T;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by acer on 2015/11/15.
 */
public class Weather_Fragment extends BaseFragment {
    private String cityName;
    private LocationClient locationClient;
    private MyLocationListener locationListener;
    private SwipeRefreshLayout refresh_layout;
    private TextView tv_Temperature,tv_city,tv_updatetime,tv_drsg_brf
            ,tv_drsg_txt,tv_sport_brf,tv_sport_txt,tv_trav_brf,tv_trav_txt;
    private ImageView iv_location;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_news_weather,null);
        refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        tv_Temperature = (TextView) view.findViewById(R.id.tv_Temperature);
        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_updatetime = (TextView) view.findViewById(R.id.tv_updatetime);
        tv_drsg_brf = (TextView) view.findViewById(R.id.tv_drsg_brf);
        tv_drsg_txt = (TextView) view.findViewById(R.id.tv_drsg_txt);
        tv_sport_brf = (TextView) view.findViewById(R.id.tv_sport_brf);
        tv_sport_txt = (TextView) view.findViewById(R.id.tv_sport_txt);
        tv_trav_brf = (TextView) view.findViewById(R.id.tv_trav_brf);
        tv_trav_txt = (TextView) view.findViewById(R.id.tv_trav_txt);
        iv_location = (ImageView) view.findViewById(R.id.iv_location);

        initAddress();
        initData();
        return view;
    }

    private void initData() {
        ApiClient.getInstance(mActivity).getWeather("北京", new Callback<Weather>() {
            @Override
            public void success(Weather weather, Response response) {
                T.showShort(mActivity, "已更新最新天气");
                tv_Temperature.setText(weather.getShowapi_res_body().getNow().getTemperature()+"℃");
                tv_updatetime.setText("更新时间:"+weather.getShowapi_res_body().getNow().getTemperature_time());
            }
            @Override
            public void failure(RetrofitError error) {
                T.showShort(mActivity,"更新天气失败了");
            }
        });
    }
    private void initAddress() {
        locationClient = new LocationClient(mActivity);
        locationListener = new MyLocationListener();
        locationClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(30000);
        locationClient.setLocOption(option);
    }
    class MyLocationListener implements BDLocationListener
    {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
             cityName = bdLocation.getCity();
            tv_city.setText(cityName);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        locationClient.start();
    }
    @Override
    public void onStop() {
        super.onStop();
        locationClient.stop();
    }
}
