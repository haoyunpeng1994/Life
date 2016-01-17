package com.hyp.life.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyp.life.R;
import com.hyp.life.model.Travel.TravelBooks;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2015/11/18.
 */
public class Adapter_ListView_Travel extends BaseAdapter {
    private Context context;
    private List<TravelBooks> list;
    public Adapter_ListView_Travel(Context mActivity, ArrayList<TravelBooks> list) {
        this.context = mActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.adapter_listview_travel,null);
            viewHolder.iv_travel = (ImageView) convertView.findViewById(R.id.iv_travel);
            viewHolder.tv_travel_title = (TextView) convertView.findViewById(R.id.tv_travel_title);
            viewHolder.iv_travel_user = (ImageView) convertView.findViewById(R.id.iv_travel_user);
            viewHolder.tv_travel_username = (TextView) convertView.findViewById(R.id.tv_travel_username);
            viewHolder.tv_travel_time = (TextView) convertView.findViewById(R.id.tv_travel_time);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getHeadImage()).into(viewHolder.iv_travel);
        Glide.with(context).load(list.get(position).getUserHeadImg()).into(viewHolder.iv_travel_user);
        viewHolder.tv_travel_title.setText(list.get(position).getTitle());
        viewHolder.tv_travel_username.setText(list.get(position).getUserName());
        viewHolder.tv_travel_time.setText("旅行时间:"+list.get(position).getStartTime());
        return convertView;
    }
    class ViewHolder
    {
        ImageView iv_travel,iv_travel_user;
        TextView tv_travel_title,tv_travel_username,tv_travel_time;
    }
}
