package com.hyp.life.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.hyp.life.R;
import com.hyp.life.activity.WebView_Activity;
import com.hyp.life.adapter.Adapter_ListView_Travel;
import com.hyp.life.api.ApiClient;
import com.hyp.life.model.Travel.Travel;
import com.hyp.life.model.Travel.TravelBooks;
import com.hyp.life.util.MyAsyncTask;
import com.hyp.life.util.T;
import com.hyp.life.util.TimeUtils;
import com.hyp.life.view.XListView;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
/**
 * Created by acer on 2015/11/15.
 */
public class Travel_Fragment extends BaseFragment{
    private XListView lv_travel;
    private ArrayList<TravelBooks> list = new ArrayList<TravelBooks>();
    private Adapter_ListView_Travel adapter_listView_travel;
    private  RelativeLayout loading;
    private int page;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_news_travel,null);
        lv_travel = (XListView) view.findViewById(R.id.lv_travel);
        loading = (RelativeLayout) view.findViewById(R.id.loading);
        lv_travel.setPullLoadEnable(true);
        lv_travel.setPullRefreshEnable(true);
        lv_travel.setRefreshTime(TimeUtils.toTimeFormat(System.currentTimeMillis()));
        lv_travel.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                new MyAsyncTask(
                ) {
                    @Override
                    public void preTask() {
                        page = 1;
                    }

                    @Override
                    public void doInBack() {
                        initData();
                    }

                    @Override
                    public void postTask() {
                        lv_travel.stopRefresh();
                    }
                }.execute();
            }

            @Override
            public void onLoadMore() {
                new MyAsyncTask() {
                    @Override
                    public void preTask() {
                        page = page + 1;
                    }

                    @Override
                    public void doInBack() {
                        loadMore();
                    }

                    @Override
                    public void postTask() {
                        lv_travel.stopLoadMore();
                    }
                }.execute();
            }
        });
        adapter_listView_travel = new Adapter_ListView_Travel(mActivity,list);
        lv_travel.setAdapter(adapter_listView_travel);
        lv_travel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, WebView_Activity.class);
                intent.putExtra("url",list.get(position-1).getBookUrl());
                startActivity(intent);
            }
        });
        page =1;
        initData();
        return view;
    }

    private void initData() {
        ApiClient.getInstance(mActivity).getTravle(page, new Callback<Travel>() {
            @Override
            public void success(Travel travel, Response response) {
                if (travel != null) {
                    list.clear();
                    list.addAll(travel.getTravelData().getBooks());
                    adapter_listView_travel.notifyDataSetChanged();
                    loading.setVisibility(View.GONE);
                    lv_travel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                T.showShort(mActivity, "请求数据失败了");
                System.out.println(error.toString());
            }
        });
    }
    private void loadMore(){
        ApiClient.getInstance(mActivity).getTravle(page, new Callback<Travel>() {
            @Override
            public void success(Travel travel, Response response) {
                if (travel != null) {
                    list.addAll(travel.getTravelData().getBooks());
                    adapter_listView_travel.notifyDataSetChanged();
                }
            }
            @Override
            public void failure(RetrofitError error) {
                T.showShort(mActivity, "请求数据失败了");
                System.out.println(error.toString());
            }
        });
    }
}
