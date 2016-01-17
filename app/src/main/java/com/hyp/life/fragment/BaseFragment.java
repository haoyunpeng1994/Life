package com.hyp.life.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by acer on 2015/11/15.
 */
public abstract class BaseFragment extends Fragment{
    protected Activity mActivity;
    protected LayoutInflater inflater;
    protected ViewGroup container;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        this.inflater = inflater;
        this.container = container;
        return view;
    }
    public abstract View initView();
}
