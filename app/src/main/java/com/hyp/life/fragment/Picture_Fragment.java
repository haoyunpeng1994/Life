package com.hyp.life.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hyp.life.R;

import java.util.zip.Inflater;

/**
 * Created by acer on 2015/11/15.
 */
public class Picture_Fragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture,container,false);
        ImageView iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
        Glide.with(getActivity()).load("http://7xkizb.com1.z0.glb.clouddn.com/2015081_10010059.jpg")
                .into(iv_picture);
        return view;
    }
}
