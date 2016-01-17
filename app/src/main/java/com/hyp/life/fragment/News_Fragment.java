package com.hyp.life.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.hyp.life.R;

/**
 * Created by acer on 2015/11/15.
 */
public class News_Fragment extends BaseFragment implements View.OnClickListener {
    private Weather_Fragment weather_fragment;
    private Event_Fragment event_fragment;
    private Travel_Fragment travel_fragment;
    private Strory_Fragment strory_fragment;
    private LinearLayout ll_weather;
    private LinearLayout ll_event;
    private LinearLayout ll_travle;
    private LinearLayout ll_strory;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_news,null);
//        View view = inflater.inflate(R.layout.fragment_news,container,false);
        ll_weather = (LinearLayout) view.findViewById(R.id.ll_weather);
        ll_event = (LinearLayout) view.findViewById(R.id.ll_event);
        ll_travle = (LinearLayout) view.findViewById(R.id.ll_travle);
        ll_strory = (LinearLayout) view.findViewById(R.id.ll_strory);
        ll_weather.setOnClickListener(this);
        ll_event.setOnClickListener(this);
        ll_travle.setOnClickListener(this);
        ll_strory.setOnClickListener(this);

        if (weather_fragment == null) {
            weather_fragment = new Weather_Fragment();
            addFragment(weather_fragment);
            showFragment(weather_fragment);
        } else {
            showFragment(weather_fragment);
        }
        return view;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.ll_weather:
                ll_weather.setSelected(true);
                if (weather_fragment == null) {
                    weather_fragment = new Weather_Fragment();
                    addFragment(weather_fragment);
                    showFragment(weather_fragment);
                } else {
                    if (weather_fragment.isHidden()) {
                        showFragment(weather_fragment);
                    }
                }
                break;
            case R.id.ll_event:
                ll_event.setSelected(true);
                if (event_fragment == null) {
                    event_fragment = new Event_Fragment();
                    addFragment(event_fragment);
                    showFragment(event_fragment);
                } else {
                    if (event_fragment.isHidden()) {
                        showFragment(event_fragment);
                    }
                }
                break;
            case R.id.ll_travle:
                ll_travle.setSelected(true);
                if (travel_fragment == null) {
                    travel_fragment = new Travel_Fragment();
                    addFragment(travel_fragment);
                    showFragment(travel_fragment);
                } else {
                    if (travel_fragment.isHidden()) {
                        showFragment(travel_fragment);
                    }
                }
                break;
            case R.id.ll_strory:
                ll_strory.setSelected(true);
                if (strory_fragment == null) {
                    strory_fragment = new Strory_Fragment();
                    addFragment(strory_fragment);
                    showFragment(strory_fragment);
                } else {
                    if (strory_fragment.isHidden()) {
                        showFragment(strory_fragment);
                    }
                }
                break;
        }
    }

    public void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);
        if (weather_fragment != null) {
            fragmentTransaction.hide(weather_fragment);
        }
        if (event_fragment != null) {
            fragmentTransaction.hide(event_fragment);
        }
        if (travel_fragment != null) {
            fragmentTransaction.hide(travel_fragment);
        }
        if (strory_fragment != null) {
            fragmentTransaction.hide(strory_fragment);
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager()
                .beginTransaction();
        ft.add(R.id.fl_container, fragment);
        ft.commit();
    }

    public void removeFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager()
                .beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }
}
